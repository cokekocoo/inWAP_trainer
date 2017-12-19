package tokyo.kenshuhori_in.smbEducate;

import jcifs.smb.SmbFile;

public class SmbUtils {

	public static boolean exists(String targetPath) {
		try {
			SmbFile smbFile = new SmbFile(targetPath);
			if (smbFile.exists()) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}
}
