package tokyo.kenshuhori_in.regExpEducate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tokyo.kenshuhori_in.SubMainInterface;

public class regExpMain implements SubMainInterface {

	@Override
	public void execute() throws IOException {
//		String regex ="<a href=\"(.*?)\" >";
//        String href = "<a href=\"http://www.yahoo.co.jp\" >";
        String regex ="jdbc:oracle:thin:@(.*):(.*):(.*)";
        String href = "jdbc:oracle:thin:@172.16.164.255:1521:cp07";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(href);

        if( m.find()){
            System.out.print("group(0)は文字列全体 "+ m.group(0)+"\n");
            System.out.print("grpuo(1)は1グループ目の一致 "+ m.group(1)+"\n");
            System.out.print("grpuo(2)は2グループ目の一致 "+ m.group(2)+"\n");
            System.out.print("grpuo(3)は3グループ目の一致 "+ m.group(3)+"\n");
        }
	}
}
