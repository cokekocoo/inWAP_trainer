package tokyo.kenshuhori_in;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import tokyo.kenshuhori_in.file.FileTransfer;

public class App {
    public static void main( String[] args ) throws IOException {

    	Path cLogsDir = Paths.get("C:\\pleiadesCU\\workspace\\updater_1\\test_resources\\logs");
    	Path eLogsDir = Paths.get("C:\\COMPANY_UPDATER\\executor\\logs");
    	Path dbDir = Paths.get("C:\\pleiadesCU\\workspace\\updater_1\\test_resources\\updater.db");
    	FileTransfer ft = new FileTransfer();
    	ft.downloadLogs(cLogsDir, eLogsDir, dbDir);

    }
}
