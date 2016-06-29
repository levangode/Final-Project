package DBConnector;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
//import DBConnector.Configuration;
import java.sql.Connection;;

public class ConnectionPool {
 
	List<Connection> availableConnections = new ArrayList<Connection>();
 
	public ConnectionPool() {
		initializeConnectionPool();
	}
	 
	 
	private void initializeConnectionPool(){
		while(!checkIfConnectionPoolIsFull()){
		  availableConnections.add(createNewConnectionForPool());
		}
	}
	 
	private synchronized boolean checkIfConnectionPoolIsFull(){
		
		if(availableConnections.size() < PoolINFO.MAX_CONNECTIONS){
			return false;
		}
	 
		return true;
	}
	 
	//Creating a connection
	private Connection createNewConnectionForPool(){
		//Configuration config = Configuration.getInstance();
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
