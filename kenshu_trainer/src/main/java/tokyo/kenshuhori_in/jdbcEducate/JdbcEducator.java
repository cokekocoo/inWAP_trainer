package tokyo.kenshuhori_in.jdbcEducate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tokyo.kenshuhori_in.SubMainInterface;

public class JdbcEducator implements SubMainInterface {

	List<String> tableList;

	public static void main(String[] args) {
		new JdbcEducator().execute();
	}

	@Override
	public void execute() {
		buildComSchemaList();
		tableList.stream().forEach(System.out::println);
		System.out.println(tableList.size());
	}

	public JdbcEducator() {
		tableList = new ArrayList<String>();
	}

	public List<String> createComTables() {
		buildComSchemaList();
		return tableList;
	}

	public List<String> createGymTables() {
		tableList.clear();
		buildGymSchemaList();
		return tableList;
	}

	public void buildComSchemaList() {
		try {
			String url = "jdbc:oracle:thin:@192.168.182.158:1521:cp06";
			String user = "companycom";
			String pass = "companycom";
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			String sql = "select * from USER_TABLES order by TABLE_NAME";
			ResultSet result =  st.executeQuery(sql);
			while(result.next()) {
				tableList.add(result.getString(1));
			}
			st.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void buildGymSchemaList() {
		try {
			String url = "jdbc:oracle:thin:@192.168.182.158:1521:cp06";
			String user = "jinji";
			String pass = "jinji";
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			String sql = "select * from USER_TABLES order by TABLE_NAME";
			ResultSet result =  st.executeQuery(sql);
			while(result.next()) {
				tableList.add(result.getString(1));
			}
			st.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void truncateComTable(List<String> tableList) {
		try {
			String url = "jdbc:oracle:thin:@192.168.182.158:1521:cp06";
			String user = "companycom";
			String pass = "companycom";
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			String sql = "TRUNCATE TABLE %s";
			int result = 0;
			for (String table : tableList) {
				String tableName = String.format(sql, table);
				System.out.println("tableName : " + tableName);
				result =  st.executeUpdate(tableName);
			}
			System.out.println("result num : " + result);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void truncateGymTable(List<String> tableList) {
		try {
			String url = "jdbc:oracle:thin:@192.168.182.158:1521:cp06";
			String user = "jinji";
			String pass = "jinji";
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			String sql = "TRUNCATE TABLE %s";
			int result = 0;
			for (String table : tableList) {
				String tableName = String.format(sql, table);
				System.out.println("tableName : " + tableName);
				result =  st.executeUpdate(tableName);
			}
			System.out.println("result num : " + result);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
