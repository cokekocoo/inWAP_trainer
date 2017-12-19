package tokyo.kenshuhori_in.smbEducate;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import tokyo.kenshuhori_in.SubMainInterface;

public class SmbEducatorMain implements SubMainInterface {

	String smbPath = "smb://pe:pe@nas177/share/個人用/hori_ke/";

	public static void main(String[] args) {
		new SmbEducatorMain().execute();
		Map<String, String> map = new HashMap<String, String>();
		map.put("EXP_SMB_USER", "pe");
		map.put("EXP_SMB_PASS", "pe");
		map.put("EXP_SMB_DIR", "pe");
		validDbBackupSmbDirectory(map);
	}

	@Override
	public void execute() {

		try {
			SmbFile smbFile = new SmbFile(smbPath);
			if (smbFile.exists()) {
				for (SmbFile oneFile : smbFile.listFiles()) {
					System.out.println("★" + oneFile.getPath());
				}
				checkSpace(smbFile);
			}
		} catch (MalformedURLException | SmbException e1) {
			e1.printStackTrace();
		}

		System.out.println("SMBテスト");
	}

	private void checkSpace(SmbFile targetFile) throws SmbException {
		double pow = Math.pow(1024, 3);
		long free = targetFile.getDiskFreeSpace();
		System.out.println("空き容量GB : " + Math.floor(free / pow) + "GB");
	}

	public static boolean validDbBackupSmbDirectory(Map<String, String> data) {
    	String expSmbUser = data.get("EXP_SMB_USER");
    	String expSmbPass = data.get("EXP_SMB_PASS");
    	String expSmbDir = data.get("EXP_SMB_DIR");
    	//頭の「\\」を「//」へ変換
    	System.out.println("①" + expSmbDir);
    	expSmbDir = expSmbDir.startsWith("\\\\") ? expSmbDir.replaceFirst("\\\\", "//") : expSmbDir;
    	System.out.println("①" + expSmbDir);
    	expSmbDir = expSmbDir.startsWith("//") ? expSmbDir : "//" + expSmbDir;
    	System.out.println("①" + expSmbDir);
    	expSmbDir = expSmbDir.replaceAll("\\", "/");
    	System.out.println("①" + expSmbDir);
    	//末尾に「/」を付与
    	String SEP = File.separator;
    	expSmbDir = expSmbDir.endsWith(SEP) ? expSmbDir : expSmbDir + "/";
    	System.out.println("①" + expSmbDir);

    	StringBuilder sb = new StringBuilder();
    	sb.append("smb://").append(expSmbUser).append(":").append(expSmbPass).append("@").append(expSmbDir);
    	String smbDir = sb.toString();
    	return SmbUtils.exists(smbDir);
	}
}
