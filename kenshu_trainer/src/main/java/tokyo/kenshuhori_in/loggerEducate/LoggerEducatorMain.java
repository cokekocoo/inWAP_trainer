package tokyo.kenshuhori_in.loggerEducate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import tokyo.kenshuhori_in.SubMainInterface;

public class LoggerEducatorMain implements SubMainInterface {

	public static void main(String[] args) {
		new LoggerEducatorMain().execute();
	}

	private static Logger logger;
	private static FileHandler fileHandler;
	private static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private static StreamHandler streamHandler;
    private static ConsoleHandler consoleHandler;

    public LoggerEducatorMain() {

    }

    @Override
	public void execute() {
    	setUp();
		outputLogger();
	}

	public void setUp() {
		logger = Logger.getLogger("LoggerEducator");
		try {
            fileHandler = new FileHandler("logs\\loggerEducator.log");
            fileHandler.setLevel(Level.SEVERE);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            streamHandler = new StreamHandler(byteArrayOutputStream, new SimpleFormatter());
            streamHandler.setLevel(Level.SEVERE);
            logger.addHandler(streamHandler);

//            consoleHandler = new ConsoleHandler();
//            consoleHandler.setLevel(Level.FINEST);
//            logger.addHandler(consoleHandler);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fail init logger to file", e);
        }
	}

	private void outputLogger() {
		logger.log(Level.OFF, "OFF 無");
		System.out.println("①size : " + byteArrayOutputStream.size());
		logger.log(Level.ALL, "ALL 全");
		System.out.println("②size : " + byteArrayOutputStream.size());
		logger.log(Level.FINEST, "FINEST 最小");
		System.out.println("③size : " + byteArrayOutputStream.size());
		logger.log(Level.FINER, "FINER 小小");
		System.out.println("④size : " + byteArrayOutputStream.size());
		logger.log(Level.FINE, "FINE 小");
		System.out.println("⑤size : " + byteArrayOutputStream.size());
		logger.log(Level.CONFIG, "CONFIG 中");
		System.out.println("⑥size : " + byteArrayOutputStream.size());
		logger.log(Level.INFO, "INFO 中中");
		System.out.println("⑦size : " + byteArrayOutputStream.size());
		logger.log(Level.WARNING, "WARNING 大");
		System.out.println("⑧size : " + byteArrayOutputStream.size());
		logger.log(Level.SEVERE, "SEVERE 最大");
		System.out.println("⑨size : " + byteArrayOutputStream.size());

		try {
			System.out.println("⑩size : " + byteArrayOutputStream.size());
			throw new Exception();
		} catch (Exception e) {
			System.out.println("⑪size : " + byteArrayOutputStream.size());
			logger.log(Level.SEVERE, "", e);
			System.out.println("⑫size : " + byteArrayOutputStream.size());
		}

	}

}
