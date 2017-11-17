package tokyo.kenshuhori_in.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileTransfer {
	private Path fromDir = null;
	private Path toDir = null;
	private String sep = FileSystems.getDefault().getSeparator();
	public static final String CONTROLLER = "Controller";
	public static final String EXECUTOR = "Executor";
	private byte[] buffer = new byte[1024];
    private int len;

    public FileTransfer() {
    	toDir = Paths.get("." + sep + "tempDir");
    }

    /**
     * targetDir以下のファイルをフォルダへまとめます。
     * @param targetDir
     * @param serverType
     */
    public void collectLogs(Path targetDir, String... serverType) {
		setFromDir(targetDir);
    	getTargetFile(targetDir, serverType);
	}

    /**
     * logを集めたフォルダをzipにまとめます。
     * logを集めたフォルダは削除します。
     * @throws IOException
     */
    public void createZip() throws IOException {
    	startZip(toDir);
		deleteDir(toDir);
    }

//	private void showFileList() {
//		Stream<File> stream = Arrays.stream(fromDir.toFile().listFiles());
//		stream.forEach(file -> System.out.println(file));
//	}

    /**
     * pathのディレクトリ以下を全て削除します。
     * @param path
     */
	private void deleteDir(Path path) {
		File targetFile = path.toFile();
		if(targetFile.exists()) {
			if(targetFile.isFile()) {
				if(targetFile.delete()) {
					System.out.println("ファイル削除");
				}
			} else {
				File[] files = targetFile.listFiles();
				if(files == null) {
					System.out.println("配下にファイルが存在しない");
				}
				for (File file : files) {
					if(file.exists() == false) {
						continue;
					} else {
						deleteDir(file.toPath());
						file.delete();
						System.out.println("ファイル削除2");
					}
				}
			}
		} else {
			System.out.println("ファイル存在しない");
		}
		targetFile.delete();
	}

    /**
     * コピー元フォルダを登録します
     * @param fromDir ファイルOKフォルダOK
     */
	private void setFromDir(Path fromDir) {
		if (fromDir.toFile().isDirectory()) {
			this.fromDir = fromDir;
		} else {
			this.fromDir = fromDir.getParent().resolve("\\");
		}
	}

	/**
	 * path以下のディレクトリをnewDir配下へコピーします
	 * @param files
	 * @param serverType
	 */
	public void getTargetFile(Path path, String... serverType) {
		if (path.toFile().isDirectory()) {
			File[] files = path.toFile().listFiles();
			Arrays.stream(files).forEach(file -> copyAllToOutDir(file, serverType));
		} else {
			copyAllToOutDir(path.toFile(), serverType);
		}
	}

	private void copyAllToOutDir(File file, String... serverType) {
		try {
			if(file.isDirectory()) {
				File[] files = file.listFiles();
				if(files.length != 0) {
					getTargetFile(file.toPath(), serverType);
				}
				return;
			}
			Path outDir = convertToDir(file, serverType);
			Files.copy(file.toPath(), outDir, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("error has occured at copyAllToOutDir! " + e);
		}
	}

	private Path convertToDir(File file, String... serverType) throws IOException {
		String path = file.toPath().toString();
		Path convPath;
		if (serverType.length == 0) {
			convPath = toDir.resolve(path);
		} else {
			convPath = toDir.resolve(serverType[0]).resolve(path);
		}
		String[] str = convPath.toString().split("\\\\|\\/");
		Path basePath = Paths.get(str[0]);
		for(int i = 1; i < str.length; i++) {
			basePath = basePath.resolve(str[i]);
			Files.createDirectories(basePath);
		}
		return convPath;
	}

	/**
	 * pathをout.zipとしてzip圧縮します。
	 * @param files
	 * @throws IOException
	 */
	public void startZip(Path zipPath) throws IOException {
    	try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream("WebContent\\out.zip"));
			File[] files = zipPath.toFile().listFiles();
			createZip(files, out);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("error has occured at createZip! at FileXXputStream! " + e);
		}
    }

	private void createZip(File[] files, ZipOutputStream out) throws IOException {
		try {
			for(File file : files) {
				if(!file.exists()) {
					continue;
				}
				if(file.isDirectory()) {
					Path subFolder = file.toPath();
					if(subFolder.toFile().listFiles().length != 0) {
						createZip(subFolder.toFile().listFiles(), out);
					}
					continue;
				}
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
				out.putNextEntry(new ZipEntry(getRelative(toDir, file.toPath())));
				while ((len = in.read(buffer)) > 0) {
		            out.write(buffer, 0, len);
		        }
		        in.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("error has occured at createZip! at FileXXputStream! " + e);
		}
	}

	private String getRelative(Path par, Path child) {
		return par.relativize(child).toString();
	}
}
