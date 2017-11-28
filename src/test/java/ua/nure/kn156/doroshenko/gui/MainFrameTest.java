package ua.nure.kn156.doroshenko.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn156.doroshenko.User;
import ua.nure.kn156.doroshenko.db.DaoFactory;
import ua.nure.kn156.doroshenko.db.DaoFactoryImpl;
import ua.nure.kn156.doroshenko.db.MockDaoFactory;
import ua.nure.kn156.doroshenko.db.MockUserDao;
import ua.nure.kn156.doroshenko.util.Messages;


public class MainFrameTest extends JFCTestCase {

	/*private MainFrame mainFrame;
	
	private Mock mockUserDao;

	protected void setUp() throws Exception {
		super.setUp();
		try{
		Properties properties = new Properties();
		properties.setProperty("ua.nure.kn156.doroshenko.db.UserDao", MockUserDao.class.getName());
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		mockUserDao = ((MockDaoFactory)  DaoFactory.getInstanse()).getMockUserDao();
		mockUserDao.expectAndReturn("findAll", new ArrayList());
		setHelper(new JFCTestHelper());
	    mainFrame = new MainFrame();
		} catch(Exception e){
			e.printStackTrace();
			
		}
	    
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Component find(Class componentClaas, String name){
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClaas, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame,0);
		assertNotNull("Could not fing component '"+name+"'", component);
		return component;
	}
	
	public void testBrowseControls(){
		
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Имя", table.getColumnName(1));
		assertEquals("Фамилия", table.getColumnName(2));
		
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
		
	
	}
	public void  testAddUser(){
		String firstName = "John";
		String lastName = "Doe";
		Date now = new Date();
		
		User user = new User(firstName,lastName,now);
		
		User expectedUser = new User(new Long(1),firstName,lastName,now);
		mockUserDao.expectAndReturn("create", user, expectedUser);
		
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		
		
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this,addButton));
		find(JPanel.class,"addPanel");
		
		JTextField firstNameField = (JTextField) find(JTextField.class,"firstNameField");
		JTextField lastNameField = (JTextField)find(JTextField.class,"lastNameField");
		JTextField dateOfBirthField = (JTextField)find(JTextField.class,"dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
		find(JButton.class, "cancelButton");
		
		
		
		getHelper().sendString(new StringEventData(this, firstNameField,firstName));
		
		getHelper().sendString(new StringEventData(this, lastNameField,lastName));
		DateFormat formatter = DateFormat.getDateInstance();
		
		
		String date = formatter.format(now);
		//SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
       // String date = format.format(new Date());
		getHelper().sendString(new StringEventData(this, dateOfBirthField,date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		 table = (JTable) find(JTable.class,"userTable");
		 assertEquals(1, table.getRowCount());
	}
	
	public void testDeleteUser() {
		try {
			User expectedUser = new User(new Long(1), "John", "Doe", new Date());
			mockUserDao.expect("delete", expectedUser);

			List users = new ArrayList();
			mockUserDao.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(0, table.getRowCount());
			mockUserDao.verify();

		} catch (Exception e) {
			fail(e.toString());
		}
	}*/
	List<User> users;
	private Mock mockUserDAO;
	private MainFrame mainFrame;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
			Properties properties = new Properties();
			properties.setProperty("dao.factory", MockDaoFactory.class.getName());
			DaoFactory.init(properties);
			mockUserDAO = ((MockDaoFactory) DaoFactory.getInstanse()).getMockUserDao();
			User expectedUser = new User(new Long(1000), "George", "Bush", new Date());
			users = Collections.singletonList(expectedUser);
			mockUserDAO.expectAndReturn("findAll", users);
			setHelper(new JFCTestHelper());
			mainFrame = new MainFrame();

		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		try {
			mockUserDAO.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Component find(Class componentClass, String name) {
		Component component;
		NamedComponentFinder finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;
	}

public void testBrowseControls(){
		
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Имя", table.getColumnName(1));
		assertEquals("Фамилия", table.getColumnName(2));
		
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
		
	
	}
	public void  testAddUser(){
		String firstName = "George";
		String lastName = "Bush";
		Date now = new Date();
		
		User user = new User(firstName,lastName,now);
		
		User expectedUser = new User(new Long(1),firstName,lastName,now);
		mockUserDAO.expectAndReturn("create", user, expectedUser);
		
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDAO.expectAndReturn("findAll", users);
		
		
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this,addButton));
		find(JPanel.class,"addPanel");
		
		JTextField firstNameField = (JTextField) find(JTextField.class,"firstNameField");
		JTextField lastNameField = (JTextField)find(JTextField.class,"lastNameField");
		JTextField dateOfBirthField = (JTextField)find(JTextField.class,"dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
		find(JButton.class, "cancelButton");
		
		
		
		getHelper().sendString(new StringEventData(this, firstNameField,firstName));
		
		getHelper().sendString(new StringEventData(this, lastNameField,lastName));
		DateFormat formatter = DateFormat.getDateInstance();
		
		
		String date = formatter.format(now);
		//SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
       // String date = format.format(new Date());
		getHelper().sendString(new StringEventData(this, dateOfBirthField,date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		 table = (JTable) find(JTable.class,"userTable");
		 assertEquals(1, table.getRowCount());
	}

	/*private void fillFields(String firstName, String lastName, Date now) {
		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");

		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		DateFormat formatter = DateFormat.getDateInstance();
		String date = formatter.format(now);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
	}
	
	public void testCancelAddUser() {
		try {
			String firstName = "John";
			String lastName = "Doe";
			Date now = new Date();

			ArrayList users = new ArrayList(this.users);
			mockUserDAO.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());

			JButton addButton = (JButton) find(JButton.class, "addButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

			find(JPanel.class, "addPanel");

			fillFields(firstName, lastName, now);

			JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());

			mockUserDAO.verify();
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	public void testDeleteUser() {
		try {
			User expectedUser = new User(new Long(1000), "George", "Bush", new Date());
			mockUserDAO.expect("delete", expectedUser);

			List users = new ArrayList();
			mockUserDAO.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(0, table.getRowCount());
			mockUserDAO.verify();

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	public void testEditUser() {
		try {
			User expectedUser = new User(new Long(1000), "George", "Bush", new Date());
			System.out.println(expectedUser);
			mockUserDAO.expect("update", expectedUser);

			List users = new ArrayList(this.users);
			mockUserDAO.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			JButton editButton = (JButton) find(JButton.class, "editButton");
			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

			find(JPanel.class, "editPanel");

			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");

			getHelper().sendString(new StringEventData(this, firstNameField, "1"));
			getHelper().sendString(new StringEventData(this, lastNameField, "1"));

			JButton okButton = (JButton) find(JButton.class, "okButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			mockUserDAO.verify();

		} catch (Exception e) {
			fail(e.toString());
		}
	}
*/

	private void findDialog(String title) {
		JDialog dialog;
		DialogFinder dFinder = new DialogFinder(title);
		dialog = (JDialog) dFinder.find();
		assertNotNull("Could not find dialog '" + title + "'", dialog);
		getHelper().disposeWindow(dialog, this);
	}
	
	public void testCancelEditUser() {
		try {
			String firstName = "John";
			String lastName = "Doe";
			Date now = new Date();

			User expectedUser = new User(new Long(1), firstName, lastName, now);
			List users = new ArrayList(this.users);
			users.add(expectedUser);

			mockUserDAO.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());

			JButton editButton = (JButton) find(JButton.class, "editButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

			String title = "Edit user";
			findDialog(title);

			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

			find(JPanel.class, "editPanel");

			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");

			assertEquals("George", firstNameField.getText());
			assertEquals("Bush", lastNameField.getText());

			getHelper().sendString(new StringEventData(this, firstNameField, firstName));
			getHelper().sendString(new StringEventData(this, lastNameField, lastName));
			DateFormat formatter = DateFormat.getDateInstance();
			String date = formatter.format(now);
			getHelper().sendString(new StringEventData(this, dateOfBirthField, date));

			JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(2, table.getRowCount());
			mockUserDAO.verify();

		} catch (Exception e) {
			fail(e.toString());
		}
	}

	public void testDetailsUser() {
		try {
			mockUserDAO.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());

			JButton detailsButton = (JButton) find(JButton.class, "detailsButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));

			String title = "Edit user";
			findDialog(title);

			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));

			find(JPanel.class, "detailsPanel");

			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");

			assertEquals("George", firstNameField.getText());
			assertEquals("Bush", lastNameField.getText());

			JButton backButton = (JButton) find(JButton.class, "backButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, backButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			mockUserDAO.verify();

		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
