package ua.nure.kn156.doroshenko.db;

import java.util.Collection;
/**
 * Interface for User class with implement DAO pattern with all CRUD opps
 * @author user
 *
 */
import ua.nure.kn156.doroshenko.User;

public interface UserDao {
	/**
	 * * Add user into DB users table and get new user's id form DB
	 * @param user all fields of user must be non-null except of id field
	 * @return copy of user from DB with auto-created id
	 * @throws DatabaseException in case of any error with DB
	 */
	User create(User user) throws DatabaseException;
	/**
	 * 
	 * @param user
	 * @throws DatabaseException
	 */
	void update(User user) throws DatabaseException;
	void delete(User user) throws DatabaseException;
	User find(Long id) throws DatabaseException;
	Collection findAll() throws DatabaseException;
    void setConnectionFactory(ConnectionFactory connectionFactory);

}
