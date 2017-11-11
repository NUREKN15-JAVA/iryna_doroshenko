package ua.nure.kn156.doroshenko;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lasttName;
	private Date dateOfBirthd;
	public User(String firstName, String lastName, Date now) {
		this.firstName = firstName;
		this.lasttName = lastName;
		this.dateOfBirthd = now;
	}
	public User(Long id, String firstName, String lastName, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lasttName = lastName;
		this.dateOfBirthd = date;
		
	}
	public User() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasttName() {
		return lasttName;
	}
	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}
	public Date getDateOfBirthd() {
		return dateOfBirthd;
	}
	public void setDateOfBirthd(Date dateOfBirthd) {
		this.dateOfBirthd = dateOfBirthd;
	}
	public String getFullName() {
		// TODO Auto-generated method stub
		StringBuilder fullName=new StringBuilder();
		fullName.append(getLasttName());
		fullName.append(", ");
		fullName.append(getFirstName());
		
		return fullName.toString();
	}
	public int getAge() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		//int currentDate = calendar.get(Calendar.DATE);
		calendar.setTime(getDateOfBirthd());
		int yearOfBirthd =calendar.get(Calendar.YEAR);
		//int dateOfBirth = calendar.get(Calendar.DATE);
		
	
		return currentYear-yearOfBirthd;
	}
	
	public boolean equals(Object obj){
		
		if(obj==null){
			return false;
		}
		
		if(this == obj){
			return true;
		}
		
		if(this.getId()==null && ((User)obj).getId()==null){
			return true;
		}
		
		
		return this.getId().equals(((User)obj).getId());
	}
	
	public int hashCode(){
		if(this.getId() == null){
			return 0;
		}
		return this.getId().hashCode();
	}

}
