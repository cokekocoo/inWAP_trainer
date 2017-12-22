package tokyo.kenshuhori_in;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main( String[] args ) throws IOException {

    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(int i = 0; i<3; i++) {
    		map.put("hori", 10 + map.getOrDefault("hori", 0));
    	}
    	map.put("katada", 10);

    	System.out.println(map.size());

    }
}
