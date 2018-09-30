package edu.msu.atmmachine.domain;

public class User {
	private String username;
	private String firstName;
	private String lastName; 
	private Double balance; 
	
	public User() {
		
	}
	
	public User(String username, String firstName, String lastName, Double balance) {
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

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
