package ua.nure.kn156.doroshenko.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import junit.framework.TestCase;
import ua.nure.kn156.doroshenko.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private static final long FIND_ID = 1000L;
	private static final long USERS_ID = 1000L;
	private static final Long UPDATES_ID = 1001L;
	private static final int DAY_OF_BIRTH = 18;
	private static final int MONTH_OF_BIRTH = 11;
	private static final int YEAR_OF_BIRTH = 1997;
	private static final String LAST_NAME = "Doroshenko";
	private static final String FIRST_NAME = "Iryna";
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	@Override
	protected void setUp() throws Exception {

		super.setUp();

		dao = new HsqldbUserDao(connectionFactory);
	}

	public void testCreate() {

		try {
			User user = new User();
			user.setFirstName(FIRST_NAME);
			user.setLasttName(LAST_NAME);
			user.setDateOfBirthd(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}

	}
	
	public void testFindAll(){
		try {
			Collection collection = dao.findAll();
			assertNotNull("collection is null",collection);
			assertEquals("collection size.",2,collection.size());
			
		} catch (DatabaseException e) {
			
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFind() {
		User userFind = new User();
		User newUser = new User();
		newUser.setFirstName("Bill");
		newUser.setLasttName("Gates");
		Calendar dateOfBirthNew = new GregorianCalendar(1968, 3, 26);
		newUser.setDateOfBirthd(dateOfBirthNew.getTime());
		try {
			userFind = dao.find(FIND_ID);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertNotNull(userFind.getFirstName());
		assertEquals( userFind.getFirstName(), newUser.getFirstName());
		assertEquals(userFind.getLasttName(), newUser.getLasttName());
		assertEquals( userFind.getDateOfBirthd(), newUser.getDateOfBirthd());

	}
	public void testUpdate() {
		User updatedUser = new User();
		User newUser = new User();
		newUser.setId(UPDATES_ID);
		newUser.setFirstName(FIRST_NAME);
		newUser.setLasttName(LAST_NAME);
		newUser.setDateOfBirthd(new Date());
		Calendar dateOfBirthNew = new GregorianCalendar(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		newUser.setDateOfBirthd(dateOfBirthNew.getTime());
		try {
			dao.update(newUser);
			updatedUser = dao.find(UPDATES_ID);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertNotNull(updatedUser.getFirstName());
		assertEquals(newUser.getLasttName(), updatedUser.getLasttName());
		assertTrue(newUser.getFirstName().equals(updatedUser.getFirstName()));
		assertEquals( newUser.getDateOfBirthd(), updatedUser.getDateOfBirthd());
	}
	
	public void testDelete(){
		Collection<User> collection = new LinkedList<>();
		User user = new User();
		user.setId(USERS_ID);
		user.setFirstName(FIRST_NAME);
		user.setLasttName(LAST_NAME);
		Calendar dateOfBirthNew = new GregorianCalendar(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirthd(dateOfBirthNew.getTime());
		try {
			dao.delete(user);
			collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 1, collection.size());

		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
	}
	
	

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImp("org.hsqldb.jdbcDriver","jdbc:hsqldb:file:db/usermanagement","sa","");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}


