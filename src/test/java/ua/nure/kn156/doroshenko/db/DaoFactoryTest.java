package ua.nure.kn156.doroshenko.db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	public void testGetUserDao() {
		try {
			DaoFactory daoFactory =  DaoFactory.getInstanse();
			assertNotNull("DaoFactory instanse is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao instanse is null", userDao);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			fail(e.toString());
		}
	
	}

}
