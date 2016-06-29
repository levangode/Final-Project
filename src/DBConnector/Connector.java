package DBConnector;

import java.sql.SQLException;
 
import java.sql.Connection;
 
public class Connector {
  
	static ConnectionPool pool = new ConnectionPool();
  
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection connection = pool.getConnectionFromPool();
		return connection;
	}
  
	public static void returnConnection(Connection connection) {
		pool.returnConnectionToPool(connection);
	}
}
