package tokyo.kenshuhori_in;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import tokyo.kenshuhori_in.file.FileTransfer;

public class App {
    public static void main( String[] args ) throws IOException {

    	FileTransfer ft = new FileTransfer();

    	Path cLogsDir = Paths.get(".\\logs");
    	ft.collectLogs(cLogsDir, FileTransfer.CONTROLLER);

//    	Path eLogsDir = Paths.get("C:\\COMPANY_UPDATER\\executor\\logs");
//    	ft.collectLogs(eLogsDir, FileTransfer.EXECUTOR);

    	Path dbDir = Paths.get(".\\updater.db");
    	ft.collectLogs(dbDir);

    	ft.createZip();

    }
}
