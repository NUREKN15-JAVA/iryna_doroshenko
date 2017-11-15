package ua.nure.kn156.doroshenko.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn156.doroshenko.User;

public class HsqldbUserDao implements UserDao {
	
	private static final String UPDATE_USERS_QUERY = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE id=?";
	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_USERS_QUERY = "INSERT INTO users (firstname, lastname, dateofBirth) VALUES (?, ?, ?) ";
	private static final String SELECT_USERS_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
	private static final String DELETE_USERS_QUERY = "DELETE FROM users WHERE id=?";
	private ConnectionFactory connectionFactory;
	 
	 public HsqldbUserDao() {
		
	}
	 
	 public HsqldbUserDao(ConnectionFactory connectionFactory) {
			this.connectionFactory=connectionFactory;
		}
	


	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}





	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}





	@Override
	public User create(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_USERS_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLasttName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			int n = statement.executeUpdate();
			if (n!=1){
				throw new DatabaseException("Number of inserted rows" + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long(keys.getLong(1)));

			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;

		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}

	@Override
	public void update(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLasttName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			statement.setLong(4, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Updating hasn't been implemented successfully! " + n);
			}

			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

	}

	@Override
	public void delete(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_USERS_QUERY);
			statement.setLong(1, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Deleting hasn't been implemented successfully!" + n);
			}

			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}

	@Override
	public User find(Long id) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_USERS_QUERY);
			statement.setLong(1, id);

			ResultSet keys = statement.executeQuery();
			User foundUser = new User();
			if (keys.next()) {
				foundUser.setFirstName(keys.getString(2));
				foundUser.setLasttName(keys.getString(3));
				foundUser.setDateOfBirthd(new java.util.Date(keys.getDate(4).getTime()));
			}
			keys.close();
			statement.close();
			connection.close();
			return foundUser;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLasttName(resultSet.getString(3));
				user.setDateOfBirthd(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
			statement.close();
			connection.close();
			return result;	
			

		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		
	
	}

}

