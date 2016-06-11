package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
	private Connection connection;

	public DBconnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String server = INFO.MYSQL_DATABASE_SERVER;
		String userName = INFO.MYSQL_USERNAME;
		String password = INFO.MYSQL_PASSWORD;
		String dbName = INFO.MYSQL_DATABASE_NAME;
		try {
			//connection = DriverManager.getConnection(server + "/" + dbName, userName, password);
			connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306" + "/" + "sql8123237", "sql8123237", "i6aiya2KBz");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return connection;
	}
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
