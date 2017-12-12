package ua.nure.kn156.doroshenko.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.kn156.doroshenko.User;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAMES = {"ID","Имя","Фамилия"};
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	private List users = null;
	
	public UserTableModel(Collection users){
		
		this.users =  new ArrayList(users);
		
	}

	@Override
	public int getRowCount() {
		
		return users.size();
	}

	@Override
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch(columnIndex){
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLasttName();
		}
		return null;
	}
	
	
	public Class getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }
	
	
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    public User getUser(int index) {
        return (User) users.get(index);
    }
    
    /**
	 * @param users2
	 */
	public void addUsers(Collection users) {
		this.users.addAll(users);

	}

	/**
	 * 
	 */
	public void clearUsers() {
		this.users = new ArrayList();
	}

}
