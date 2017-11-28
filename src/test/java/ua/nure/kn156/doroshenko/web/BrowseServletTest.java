package ua.nure.kn156.doroshenko.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.kn156.doroshenko.User;
import ua.nure.kn156.doroshenko.db.MockDaoFactory;

public class BrowseServletTest extends MockServletTestCase {

	 

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	
	
	public void testBrowse() {
		User user = new User(new Long(1000), "John", "Doe", new Date());
		List list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull(collection);
		assertSame(list, collection);	
	}
	
	public void testEdit() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", user);
        assertSame(user, userInSession);
    }
	/*public void testEditWithoutId() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        addRequestParameter("editButton", "Edit");
       doPost();
        assertNotNull("Could not find error message", getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }*/
	public void testDelete() {
	       
	       User user = new User(new Long(1000), "John", "Doe", new Date());
	       getMockUserDao().expectAndReturn("find", 1000L, user);
	       getMockUserDao().expect("delete", user);
	       List  list = Collections.singletonList(user);
	       getMockUserDao().expectAndReturn("findAll", list);
	       addRequestParameter("deleteButton", "Delete");
	       addRequestParameter("id", "1000");
	       doPost();
	       List<User> users = (List<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
	       assertNotNull("Couldn't find users in session", users);
	       assertSame(list,users);
	}

	public void testDetails() {
	    
     // create user
		User user = new User(new Long(1000), "John", "Doe", new Date());
     getMockUserDao().expectAndReturn("find", 1000L, user);
     addRequestParameter("detailsButton", "Details");
     addRequestParameter("id", "1000");
     doGet();
     
     // extract user from session
     User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
     assertNotNull("Could not find user in session", user);
     assertSame(user, userInSession);
     
}


}
