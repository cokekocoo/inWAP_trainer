package tokyo.kenshuhori_in.jdbcEducate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tokyo.kenshuhori_in.SubMainInterface;

public class TruncateEducatorForDBCopy implements SubMainInterface {

	List<String> tableList;
	List<String> fileTableList;

	public static void main(String[] args) {
		new TruncateEducatorForDBCopy().execute();
	}

	public TruncateEducatorForDBCopy() {
		fileTableList = new ArrayList<String>();
	}

	@Override
	public void execute() {
		executeCom();
		executeGym();
	}

	public void executeCom() {
		tableList = new JdbcEducator().createComTables();
		System.out.println("tableList1 : " + tableList.size());
		File file = new File("dbcopy_ini");
		if (file.exists()) {
			for (File one : file.listFiles()) {
				executeFile(one);
			}
		}
		System.out.println("tableList2 : " + tableList.size());
		new JdbcEducator().truncateComTable(tableList);
	}

	public void executeGym() {
		tableList = new JdbcEducator().createGymTables();
		System.out.println("tableList1 : " + tableList.size());
		File file = new File("dbcopy_ini");
		if (file.exists()) {
			for (File one : file.listFiles()) {
				executeFile(one);
			}
		}
		System.out.println("tableList2 : " + tableList.size());
		new JdbcEducator().truncateGymTable(tableList);
	}

	public void executeFile(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
			String tableName = null;
			while((tableName = br.readLine()) != null) {
				if (tableName.startsWith("/")) {
					tableName = tableName.split(",")[1].toUpperCase();
					fileTableList.add(tableName);
					tableList.remove(tableName);
				}
			}
			System.out.println("fileTableList : " + fileTableList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
