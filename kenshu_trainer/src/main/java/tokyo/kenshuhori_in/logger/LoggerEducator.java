package tokyo.kenshuhori_in.logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class LoggerEducator {
	private static Logger logger;
	private static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private static StreamHandler streamHandler;

    public LoggerEducator() {

    }

	public void loggerMain() {
		setUp();
		outputLogger();
	}

	public void setUp() {
		logger = Logger.getLogger("LoggerEducator");
		try {
            FileHandler fileHandler = new FileHandler("C:\\COMPANY_UPDATER\\test\\loggerDcucation.log");
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            streamHandler = new StreamHandler(byteArrayOutputStream, new SimpleFormatter());
            streamHandler.setLevel(Level.SEVERE);
            logger.addHandler(streamHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fail init logger to file", e);
        }
	}

	private void outputLogger() {
		logger.log(Level.INFO, "message output succeed");
	}
}
