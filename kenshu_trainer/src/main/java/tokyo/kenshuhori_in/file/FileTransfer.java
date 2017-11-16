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
	private static final String CONTROLLER = "Controller";
	private static final String EXECUTOR = "Executor";
	private byte[] buffer = new byte[1024];
    private int len;

    public void downloadLogs(Path cLogsDir, Path eLogsDir, Path dbDir) throws IOException {
    	Path newDir = Paths.get("." + sep + "tempDir");
		setDir(cLogsDir, newDir);
    	getTargetFile(cLogsDir, FileTransfer.CONTROLLER);
    	setDir(eLogsDir, newDir);
    	getTargetFile(eLogsDir, FileTransfer.EXECUTOR);
    	setDir(dbDir, newDir);
    	getTargetFile(dbDir);
    	startZip(toDir);
	}

//	private void showFileList() {
//		Stream<File> stream = Arrays.stream(fromDir.toFile().listFiles());
//		stream.forEach(file -> System.out.println(file));
//	}
//	private void deleteDir() {
//		try {
//			Files.deleteIfExists(toDir);
//		} catch (IOException e) {
//			System.out.println("error has occured at deleteDir! " + e);
//		}
//	}

    /**
     * コピー元フォルダとコピー先フォルダを登録します
     * @param fromDir ファイルOKフォルダOK
     * @param toDir ファイルNGフォルダOK
     */
	private void setDir(Path fromDir, Path toDir) {
		if (fromDir.toFile().isDirectory()) {
			this.fromDir = fromDir;
		} else {
			this.fromDir = fromDir.getParent();
		}
		this.toDir = toDir;
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
		String path = file.getAbsolutePath();
		String convertedPath;
		if(serverType.length == 0) {
			convertedPath = path.replace(fromDir.toString(), toDir.toString());
		} else {
			convertedPath = path.replace(fromDir.toString(), toDir.toString() + sep + serverType[0]);
		}
		String[] str = convertedPath.split("\\\\|\\/");
		Path basePath = Paths.get(str[0]);
		for(int i = 1; i < str.length - 1; i++) {
			basePath = basePath.resolve(str[i]);
			Files.createDirectories(basePath);
		}
		return Paths.get(convertedPath);
	}

	/**
	 * pathをout.zipとしてzip圧縮します。
	 * @param files
	 * @throws IOException
	 */
	public void startZip(Path zipPath) throws IOException {
    	try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream("out.zip"));
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
