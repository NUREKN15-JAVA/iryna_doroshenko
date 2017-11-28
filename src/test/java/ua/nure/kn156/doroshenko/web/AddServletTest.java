package ua.nure.kn156.doroshenko.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn156.doroshenko.User;

public class AddServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}public void testAdd() {
		Date date = new Date();
		User newUser = new User("John", "Doe", date);
		User user = new User(new Long(1000), "John", "Doe", date);
		getMockUserDao().expectAndReturn("create", newUser, user);

		addRequestParameter("firstName", "John");
		addRequestParameter("lastname", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	public void testAddEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("lastname", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyLastName() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	public void testAddEmptyDate() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("lastname", "Doe");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testAddEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("firstName", "John");
		addRequestParameter("lastname", "Doe");
		addRequestParameter("date", "dfghkjgfdsa");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	

}
