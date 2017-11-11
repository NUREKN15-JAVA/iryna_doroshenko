package ua.nure.kn156.doroshenko.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	
	protected static final String USER_DAO = "ua.nure.kn156.doroshenko.db.UserDAO";
	private static final String DAO_FACTORY = "dao.factory";
	protected static Properties properties;
	
	private static DaoFactory instance;
	static {
		properties=new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static synchronized DaoFactory getInstanse(){
		if (instance == null){
			Class factoryClass;
			try {
				factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
		return instance;
}
	
	protected DaoFactory() {
		/*properties=new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/

	}
	public static void init(Properties prop){
		properties = prop;
		instance = null;
		
	}
	
	protected ConnectionFactory getConnectionFactory(){
		/*String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");*/
		return new ConnectionFactoryImp(properties);
	}
	
	public abstract UserDao getUserDao();/*{
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			 result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
			
		} catch (Exception e) {
			throw new  RuntimeException(e);
		}
		return result;
		
	}*/
	
	

}

