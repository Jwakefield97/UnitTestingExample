package edu.msu.atmmachine.dao;

import java.sql.Connection;

import edu.msu.atmmachine.domain.User;

public class AtmDao {
	private Connection connection;
	
	public AtmDao(Connection connection) {
		this.connection = connection;
	}
	
	private static final String CREATE_USER = "";
	
	public void createUser(String username, String firstName, String lastName) {
		
	}
	
	private static final String GET_USER = "";
	
	public User getUser(String username) {
		return null;
	}
	
	private static final String IS_USER_EXISTS = "";
	
	public boolean isUserExists(String username) {
		return false;
	}
	
	private static final String UPDATE_BALANCE = "";
	
	public void updateBalance(String username, double newBalance) {
		
	}
	
	private static final String GET_BALANCE = "";
	
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
