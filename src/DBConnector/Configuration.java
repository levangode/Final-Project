package DBConnector;

import DBConnector.DBINFO;

public class Configuration {
  
	public String DB_USER_NAME ;
	public String DB_PASSWORD ;
	public String DB_URL;
	public String DB_DRIVER;
	public Integer DB_MAX_CONNECTIONS;
  
	public Configuration(){
		init();
	}

	private static Configuration configuration = new Configuration();
  
	public static Configuration getInstance(){ 
		return configuration;
	}
  
	private void init(){
		DB_USER_NAME = DBINFO.MYSQL_USERNAME;
		DB_PASSWORD = DBINFO.MYSQL_PASSWORD;
		DB_URL = DBINFO.MYSQL_DATABASE_SERVER;
		DB_DRIVER = DBINFO.DRIVER_NAME;
		DB_MAX_CONNECTIONS = 5;
	}
 
}