package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectSQL {
	private static String serverName = "localhost";
	private static String dbName = "21110187_NguyenHuy_KiemTraLapTrinhWeb";
	private static String portNumber = "1433";
	private static String instance = "";
	private static String userID = "sa";
	private static String password = "sa";

	public Connection getConnection() throws Exception {
			String url = "jdbc:sqlserver://"+serverName+":"+portNumber + "\\" + instance +";databaseName="+dbName;
			
			if (instance == null || instance.trim().isEmpty())
				
				url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			return DriverManager.getConnection(url, userID, password);
	}
	
	//Test chuong trinh
	public static void main(String[] args) {
		try {
			System.out.println(new DBConnectSQL().getConnection());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

