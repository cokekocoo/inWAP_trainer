package tokyo.kenshuhori_in.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import tokyo.kenshuhori_in.SubMainInterface;

public class FileMain implements SubMainInterface {

	/**
	 *
	 * @Override
	 */
	public void execute() throws IOException {
		FileTransfer ft = new FileTransfer();

		ft.deleteDir(Paths.get("WebContent\\CUlogs.zip"));

		Path cLogsDir = Paths.get(".\\logs");
		ft.collectLogs(cLogsDir, FileTransfer.CONTROLLER);

//		Path eLogsDir = Paths.get("C:\\COMPANY_UPDATER\\executor\\logs");
//		ft.collectLogs(eLogsDir, FileTransfer.EXECUTOR);

		Path dbDir = Paths.get(".\\updater.db");
		ft.collectLogs(dbDir, FileTransfer.CONTROLLER);

		ft.createZip();
	}
}
