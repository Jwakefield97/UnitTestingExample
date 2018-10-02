package edu.msu.atmmachine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.msu.atmmachine.domain.User;

public class UserTest {
	private static String username;
	private static String firstName;
	private static String lastName;
	private static Double balance;
	private static User testUser;
	
	@Before
	public void initUser() {
		username = "bob72";
		firstName = "Bob";
		lastName = "Bobington";
		balance = 72.0;
		testUser = new User(username, firstName, lastName, balance);
	}
	
	@After
	public void cleanUpUser() {
		testUser = null; //reset the testUser (for demo purpose)
	}
	
	@Test
	public void testConstructorDefault() {
		User testUser = new User();
		assertNull(testUser.getFirstName());
	}
	
	@Test
	public void testConstructorParameters() {
		String username = "jim38";
		String firstName = "Jimmah";
		String lastName = "John";
		Double balance = 876.0;
		
		testUser.setUsername("al;skdfjl;askdjfl;k"); //change the username of the shared test user 
		
		User testUser = new User(username, firstName, lastName, balance);
		assertEquals(firstName, testUser.getFirstName());
		assertEquals(lastName, testUser.getLastName());
		assertEquals(username, testUser.getUsername());
		assertEquals(balance, testUser.getBalance(), 0);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(firstName, testUser.getFirstName());
	}
	
	@Test
	public void testSetUsername() {
		String newUsername = "phil03";
		testUser.setUsername(newUsername);
		assertEquals(newUsername, testUser.getUsername());
		testUser.setUsername(username);
		assertEquals(username, testUser.getUsername());
	}
	
	@Test
	public void testGetFirstName() {
		assertEquals(firstName, testUser.getFirstName());
	}
	
	@Test
	public void testSetFirstName() {
		String newFirstName = "Phillip";
		testUser.setFirstName(newFirstName);
		assertEquals(newFirstName, testUser.getFirstName());
		testUser.setFirstName(firstName);
		assertEquals(firstName, testUser.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		assertEquals(lastName, testUser.getLastName());
	}
	
	@Test
	public void testSetLastName() {
		String newLastName = "Royce";
		testUser.setLastName(newLastName);
		assertEquals(newLastName, testUser.getLastName());
		testUser.setLastName(lastName);
		assertEquals(lastName, testUser.getLastName());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(balance, testUser.getBalance(), 0);
	}
	
	@Test
	public void testSetBalance() {
		Double newBalance = balance + 20.0;
		testUser.setBalance(newBalance);
		assertEquals(newBalance, testUser.getBalance(), 0);
		testUser.setBalance(balance);
		assertEquals(balance, testUser.getBalance(), 0);
	}

}
