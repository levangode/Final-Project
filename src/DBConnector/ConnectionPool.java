package DBConnector;

import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.mysql.jdbc.JDBC4Connection;

//import DBConnector.Configuration;
import java.sql.Connection;

public class ConnectionPool {
	Queue<Connection> Connections = new ConcurrentLinkedQueue<>();
	List<Connection> availableConnections = new ArrayList<Connection>();
 
	public ConnectionPool() {
		initializeConnectionPool();
	}
	 
	 
	private void initializeConnectionPool(){
		int counter = 0;
		while(!checkIfConnectionPoolIsFull()){
			System.out.println(counter++);
			availableConnections.add(createNewConnectionForPool());
		}
	}
	 
	private synchronized boolean checkIfConnectionPoolIsFull(){
		
		if(availableConnections.size() < PoolINFO.MAX_CONNECTIONS){
			return false;
		}
	 
		return true;
	}
	 
	//Creating a single connection
	private Connection createNewConnectionForPool(){
		try {
			Class.forName(DBINFO.DRIVER_NAME);
			Connection connection = (Connection) DriverManager.getConnection(
					DBINFO.MYSQL_DATABASE_SERVER + "/" + DBINFO.MYSQL_DATABASE_NAME,
					DBINFO.MYSQL_USERNAME,
					DBINFO.MYSQL_PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
		 
	public synchronized Connection getConnectionFromPool(){
		Connection connection = null;
		if(availableConnections.size() > 0){
			connection = (Connection) availableConnections.get(0);
			availableConnections.remove(0);
		}
		return connection;
	}
		 
	public synchronized void returnConnectionToPool(Connection connection){
		availableConnections.add(connection);
	}
	
}
