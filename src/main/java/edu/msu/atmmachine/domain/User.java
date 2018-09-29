package edu.msu.atmmachine.domain;

public class User {
	private String username;
	private String firstName;
	private String lastName; 
	private double balance; 
	
	User() {
		
	}
	
	User(String username, String firstName, String lastName, double balance) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
