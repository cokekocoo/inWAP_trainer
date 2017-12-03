package tokyo.kenshuhori_in.fileSpace;

import tokyo.kenshuhori_in.SubMainInterface;

public class FileSpaceMain implements SubMainInterface {
	
	@Override
	public void execute() {
		new FileSpaceChecker().checkSpace();
	}
}
