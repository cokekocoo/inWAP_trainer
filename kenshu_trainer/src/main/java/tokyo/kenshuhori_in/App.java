package tokyo.kenshuhori_in;

import tokyo.kenshuhori_in.process.ProcessEducator;

public class App {
    public static void main( String[] args ) {
    	String processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    	System.out.println("processId : " + processId);
        ProcessEducator pe = new ProcessEducator();
        pe.detectIfProcessStopped();
    }
}
