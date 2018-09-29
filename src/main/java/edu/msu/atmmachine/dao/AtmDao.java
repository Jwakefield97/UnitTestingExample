package edu.msu.atmmachine.dao;

import java.sql.Connection;

import edu.msu.atmmachine.domain.User;

public class AtmDao {
	private Connection connection;
	
	public AtmDao(Connection connection) {
		this.connection = connection;
	}
	
	private static final String CREATE_USER = "INSERT INTO user VALUES (?,?,?,?)";
	
	public void createUser(String username, String firstName, String lastName) {
		
	}
	
	private static final String GET_USER = "SELECT * FROM user WHERE username = ?";
	
	public User getUser(String username) {
		return null;
	}
	
	private static final String IS_USER_EXISTS = "SELECT username FROM user WHERE username = ?";
	
	public boolean isUserExists(String username) {
		return false;
	}
	
	private static final String UPDATE_BALANCE = "UPDATE user SET balance = ? WHERE username = ?";
	
	public void updateBalance(String username, double newBalance) {
		
	}
	
	private static final String GET_BALANCE = "SELECT balance FROM user WHERE username = ?";
	
	public double getBalance(String username) {
		return 0;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
