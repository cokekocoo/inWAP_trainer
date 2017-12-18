package tokyo.kenshuhori_in;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {

    	String sep = System.lineSeparator();
    	String path = "X:\\個人用\\hori_ke";
    	File file = new File(path);
    	if (file.exists()) {
    		System.out.println("true : " + file.getName());
    	}
    	else {
    		System.out.println("false : " + file.getName());
    	}
    }
}
