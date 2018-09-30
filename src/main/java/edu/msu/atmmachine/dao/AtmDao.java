package edu.msu.atmmachine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.msu.atmmachine.domain.User;

public class AtmDao {
	
	private Connection connection;
	
	public AtmDao(Connection connection) {
		this.connection = connection;
	}
	
	private static final String CREATE_USER = "INSERT INTO user (username,firstName,lastName,balance) VALUES (?,?,?,?)";
	
	/**
	 * @param username of the user to be created.
	 * @param firstName of the user to be created.
	 * @param lastName of the user to be created.
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	public void createUser(String username, String firstName, String lastName) throws SQLException {
		executeQuery(CREATE_USER, new Object[]{username,firstName, lastName, new Double("0.0")}, true);
	}
	
	
	private static final String GET_USER = "SELECT * FROM user WHERE username = ?";
	
	/**
	 * @param username of the user to retrieve
	 * @return a user object
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	public User getUser(String username) throws SQLException {
		return mapUser(executeQuery(GET_USER,new Object[]{username},false));
	}
	
	
	private static final String IS_USER_EXISTS = "SELECT COUNT(1) FROM user WHERE username = ?";
	
	/**
	 * @param username to check for its presence in the database.
	 * @return a boolean indicating whether the user exits.
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	public boolean isUserExists(String username) throws SQLException {
		ResultSet rs = executeQuery(IS_USER_EXISTS,new Object[]{ username },false);
		rs.next(); //get the first/only result from the resultset generator
		return (rs.getInt(1) != 0);
	}
	
	
	private static final String UPDATE_BALANCE = "UPDATE user SET balance = ? WHERE username = ?";
	
	/**
	 * @param username of the user whose balance is being updated
	 * @param newBalance for the user.
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	public void updateBalance(String username, Double newBalance) throws SQLException {
		executeQuery(UPDATE_BALANCE,new Object[]{ newBalance, username }, true);
	}
	
	
	private static final String GET_BALANCE = "SELECT balance FROM user WHERE username = ?";
	
	/**
	 * @param username of the user whose balance is being retrieved.
	 * @return a Double representation of the users balance.
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	public Double getBalance(String username) throws SQLException {
		ResultSet rs = executeQuery(GET_BALANCE, new Object[] { username } ,false);
		rs.next();
		return rs.getDouble(1);
	}
	
	
	/**
	 * This is a helper function for adding parameters to a query and auto committing after the statement execution.
	 * @param query the query to be executed with "?" characters representing parameter placement. 
	 * @param params an object array containing paramerters in the order they appear in the query.
	 * @param isUpdate a boolean indicating whether the query is an update query (true) or a query with a result (false).
	 * @return a null ResultSet (update query) or a populated ResultSet (non update).
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	private ResultSet executeQuery(String query, Object[] params, boolean isUpdate) throws SQLException {
		ResultSet result = null; //returns null for update queries
		PreparedStatement statement = connection.prepareStatement(query);
		
		//add positional parameters to query
		for(int i = 0; i < params.length; i++) {
			Object param = params[i];
			if(param instanceof String) {
				statement.setString(i+1, param.toString());
			}else if (param instanceof Double){
				statement.setDouble(i+1, (Double) param);
			}
		}
		
		if(isUpdate) { //if it is an update query, else its a query with a result 
			statement.executeUpdate(); //execute query
		}else {
			result = statement.executeQuery();
		}
		connection.commit(); // commit changes
		
		return result;
	}
	
	
	/**
	 * @param rs the ResultSet from the query containing User information
	 * @return a populated User object
	 * @throws SQLException if an unforeseen error occurs with the database.
	 */
	private User mapUser(ResultSet rs) throws SQLException {
		rs.next(); //get the first result from the generator
		//map the user based on the resultset. assuming data is returned in order (username,firstName,lastName,balance)
		return new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
	}
	
	
	/**
	 * @return the connection object that this Dao is using.
	 */
	public Connection getConnection() {
		return connection;
	}

	
	/**
	 * @param connection to be set for this Dao.
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
