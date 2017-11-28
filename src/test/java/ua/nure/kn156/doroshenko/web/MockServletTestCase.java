package ua.nure.kn156.doroshenko.web;

import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.kn156.doroshenko.db.DaoFactory;
import ua.nure.kn156.doroshenko.db.MockDaoFactory;

public class MockServletTestCase extends BasicServletTestCaseAdapter {
	
	private Mock mockUserDao;

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory)DaoFactory.getInstanse()).getMockUserDao());
	}

	protected void tearDown() throws Exception {
		//getMockUserDao().verify();
		super.tearDown();
		
	}

}
