package ua.nure.kn156.doroshenko;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	private User user;
	private Date dateOfBirthd;
	private final static int YEAR = 1997;
	private final static int DATE = 18;

	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, Calendar.NOVEMBER, DATE);
		//calendar.set(1997, Calendar.NOVEMBER, 18);
		dateOfBirthd=calendar.getTime();
	}
	
	public void testGetFullName() {
		user.setFirstName("Iryna");
		user.setLasttName("Doroshenko");
		assertEquals("Doroshenko, Iryna", user.getFullName());
		
	}
	
public void testgetAge(){
		
		//user.setDateOfBirthd(dateOfBirthd);
		//assertEquals(2017-1997, user.getAge());
	       Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        int currentYear = calendar.get(Calendar.YEAR);
	        user.setDateOfBirthd(dateOfBirthd);
	        assertEquals(currentYear - YEAR, user.getAge());
	}

}
