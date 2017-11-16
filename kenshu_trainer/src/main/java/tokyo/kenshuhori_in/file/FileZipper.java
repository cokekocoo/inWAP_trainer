package tokyo.kenshuhori_in.file;

import java.io.File;
import java.util.zip.ZipOutputStream;

public class FileZipper {

	public static void createZip(ZipOutputStream out, File targetFile) {

		File[] files = targetFile.listFiles();
		for (File file : files) {
			createZip(out, file);
		}
	}
}
