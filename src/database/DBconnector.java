package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DBConnector.Connector;

public class DBconnector {
	private Connection connection;

	public DBconnector() {
		try {
			this.connection = Connector.getConnection();
			return;
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//
		String server = INFO.MYSQL_DATABASE_SERVER;
		String userName = INFO.MYSQL_USERNAME;
		String password = INFO.MYSQL_PASSWORD;
		String dbName = INFO.MYSQL_DATABASE_NAME;
		try {
			connection = DriverManager.getConnection(server + "/" + dbName,
					userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		Connector.returnConnection(connection);
	}

}
