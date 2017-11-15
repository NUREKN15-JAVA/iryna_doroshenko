package ua.nure.kn156.doroshenko.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImp implements ConnectionFactory {
	
	private String driver;
	private String url;
	private String user;
	private String password;


	
	
	public ConnectionFactoryImp(String driver, String url, String user, String password) {
		this.driver=driver;
		this.url=url;
		this.user=user;
		this.password=password;
	}

	public ConnectionFactoryImp(Properties properties) {
		String user ="sa";
		String password = "";
		String url = "jdbc:hsqldb:file:db/usermanagement";
		String driver = "org.hsqldb.jdbcDriver";
		/*String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");*/
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection("jdbc:hsqldb:file:db/usermanagement", "sa", "");
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}

