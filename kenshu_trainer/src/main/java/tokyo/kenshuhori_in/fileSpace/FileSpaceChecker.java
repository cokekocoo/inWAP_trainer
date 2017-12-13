package tokyo.kenshuhori_in.fileSpace;

import java.io.File;

public class FileSpaceChecker {

	public void checkSpace() {

		//forWindows
		String driveC = "C:\\";
		//forLinux
		String linuxRoot = "/";
		File file = new File(driveC);

		//総容量
		long total = file.getTotalSpace();
		System.out.println("total : " + total);

		//空き容量
		long free = file.getFreeSpace();
		System.out.println("free : " + free);

		//GB変換用(1024*1024*1024)
		double pow = Math.pow(1024, 3);
		System.out.println("pow : " + pow);

		//総容量GB表示
		System.out.println("総容量GB : " + Math.floor(total / pow) + "GB");

		//総容量GB表示
		System.out.println("空き容量GB : " + Math.floor(free / pow) + "GB");
	}
}
