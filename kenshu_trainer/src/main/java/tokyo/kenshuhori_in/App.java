package tokyo.kenshuhori_in;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) throws IOException {

    	Map<String, Double> prodDBusedSpaceMap = new HashMap<String, Double>();
    	prodDBusedSpaceMap.put("USR_JINJI", 100.0);
    	prodDBusedSpaceMap.put("USR_KYUYO", 200.0);
    	prodDBusedSpaceMap.put("USR_WORK", 300.0);
    	prodDBusedSpaceMap.put("USR_INDEX01", 400.0);
    	prodDBusedSpaceMap.put("USR_INDEX02", 500.0);
    	prodDBusedSpaceMap.put("SYSTEM", 600.0);

		Map<String, Double> testDBtotalSpaceMap = new HashMap<String, Double>();
		testDBtotalSpaceMap.put("USR_JINJI", 300.0);
		testDBtotalSpaceMap.put("USR_KYUYO", 300.0);
		testDBtotalSpaceMap.put("USR_WORK", 300.0);
		testDBtotalSpaceMap.put("USR_INDEX01", 300.0);
		testDBtotalSpaceMap.put("USR_INDEX02", 300.0);
		testDBtotalSpaceMap.put("TEMP", 600.0);

//		prodDBusedSpaceMap.entrySet().stream()
//			.forEach(record -> System.out.println(testDBtotalSpaceMap.get(record.getKey()) + " / " + record.getKey() + " : " + record.getValue()));
//			.filter(record -> testDBtotalSpaceMap.get(record.getKey()) > record.getValue())
//			.forEach(System.out::println);
//			.collect(Collectors.toList());

		List<Entry<String, Double>> result = prodDBusedSpaceMap.entrySet().stream()
			.filter(record -> testDBtotalSpaceMap.get(record.getKey()) != null && testDBtotalSpaceMap.get(record.getKey()) < record.getValue())
			.collect(Collectors.toList());

		System.out.println(result.toString());
    }
}
