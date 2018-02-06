package tokyo.kenshuhori_in;

import java.io.IOException;

import tokyo.kenshuhori_in.jdbcEducate.JdbcEducator;

public class App {
    public static void main( String[] args ) throws IOException {
    	new JdbcEducator().execute();
    }
}
