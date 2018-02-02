package tokyo.kenshuhori_in;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main( String[] args ) throws IOException {

    	File file = new File("C:\\Users\\works\\Desktop\\趣味ファイル\\DBコピーツール\\Db_copy\\2.db_copy_main\\CJK\\par");
    	for(File f : file.listFiles()) {
    		System.out.println(f.getName());
    	}

    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(int i = 0; i<3; i++) {
    		map.put("hori", 10 + map.getOrDefault("hori", 0));
    	}
    	map.put("katada", 10);

    	System.out.println(map.size());

    }
}
