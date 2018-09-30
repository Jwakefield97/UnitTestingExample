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
	
	public boolean createUser(String username, String firstName, String lastName) throws SQLException {
		if(!isUserExists(username)) { //check to see if the user exists
			executeQuery(CREATE_USER, new Object[]{username,firstName, lastName, new Double("0.0")}, true);
			return true;
		}else {
			return false;
		}
	}
	
	private static final String GET_USER = "SELECT * FROM user WHERE username = ?";
	
	public User getUser(String username) throws SQLException {
		return mapUser(executeQuery(GET_USER,new Object[]{username},false));
	}
	
	private static final String IS_USER_EXISTS = "SELECT username FROM user WHERE username = ?";
	
	public boolean isUserExists(String username) throws SQLException {
		ResultSet rs = executeQuery(IS_USER_EXISTS,new Object[]{username},false);
		int rowCount = 0;
		while (rs.next()){
		    // Process the row.
		    rowCount++;
		}
		return rowCount < 1;
	}
	
	private static final String UPDATE_BALANCE = "UPDATE user SET balance = ? WHERE username = ?";
	
	public void updateBalance(String username, Double newBalance) throws SQLException {
		executeQuery(UPDATE_BALANCE,new Object[]{ newBalance, username }, true);
	}
	
	private static final String GET_BALANCE = "SELECT balance FROM user WHERE username = ?";
	
	public Double getBalance(String username) throws SQLException {
		return executeQuery(GET_BALANCE, new Object[] { username } ,false).getDouble(0);
	}
	
	public ResultSet executeQuery(String query, Object[] params, boolean isUpdate) throws SQLException {
		ResultSet result = null; //returns null for update queries
		PreparedStatement statement = connection.prepareStatement(query);
		
		//add positional parameters to query
		for(int i = 0; i < params.length; i++) {
			Object param = params[i];
			if(param instanceof String) {
				statement.setString(i, param.toString());
			}else if (param instanceof Double){
				statement.setDouble(i, (Double) param);
			}
		}
		
		if(isUpdate) {
			statement.executeUpdate(); //execute query
		}else {
			result = statement.executeQuery();
		}
		connection.commit(); // commit changes
		
		return result;
	}
	
	public User mapUser(ResultSet rs) throws SQLException {
		//map the user based on the resultset. assuming data is returned in order (username,firstName,lastName,balance)
		return new User(rs.getString(0),rs.getString(1),rs.getString(2),rs.getDouble(3));
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
