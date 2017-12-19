package tokyo.kenshuhori_in.fileSpace;

import tokyo.kenshuhori_in.SubMainInterface;

public class FileSpaceMain implements SubMainInterface {

	public static void main(String[] args) {
		new FileSpaceMain().execute();
	}

	//forWindows
	String driveC = "C:\\";
	String driveX = "X:\\個人用";
	//forLinux
	String linuxRoot = "/";

	@Override
	public void execute() {
		new FileSpaceChecker().checkSpace(driveX);
	}
}
