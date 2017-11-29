package tokyo.kenshuhori_in;

import java.io.IOException;

import tokyo.kenshuhori_in.threads.ThreadMain;

public class App {
    public static void main( String[] args ) throws IOException {

    	new ThreadMain().execute();

    }
}
