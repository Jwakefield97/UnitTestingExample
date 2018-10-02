package edu.msu.atmmachine.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import edu.msu.atmmachine.dao.AtmDao;
import edu.msu.atmmachine.domain.User;

public class AtmDaoTest {
	
	//create the dao object for testing
	private static AtmDao atmDao;
	
	@BeforeClass
	public static void init() {
		atmDao = new AtmDao(TestSuite.DBConnection);
	}
	
	@Test
	public void testConnection() {
		assertNotNull(TestSuite.DBConnection);
	}
	
	@Test
	public void testConstructor() {
		AtmDao testAtmDao;
		testAtmDao = new AtmDao(TestSuite.DBConnection);
		try {
			assertEquals("Billy", testAtmDao.getUser("bill2").getFirstName());
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Test
	public void testCreateUser() {
		String username = "bob72";
		String firstName = "bob";
		String lastName = "jones";
		try {
			User testUser;
			atmDao.createUser(username, firstName, lastName);
			testUser = atmDao.getUser("bob72");
			assertEquals(firstName, testUser.getFirstName());
			assertEquals(lastName, testUser.getLastName());
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Test
	public void testGetUser() {
		String username = "bill2";
		String firstName = "Billy";
		String lastName = "Janson";
		try {
			User testUser;
			testUser = atmDao.getUser(username);
			assertNotNull(testUser);
			assertEquals(firstName, testUser.getFirstName());
			assertEquals(lastName, testUser.getLastName());
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Ignore
	@Test
	public void testIsUserExists() {
		String usernameExists = "bill2";
		String usernameNotExists = "someguy";
		try {
			assertTrue(atmDao.isUserExists(usernameExists));
			assertFalse(atmDao.isUserExists(usernameNotExists));
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Test(timeout=100)
	public void testUpdateBalance() {
		String username = "bill2";
		try {
			User testUser = atmDao.getUser(username);
			double balance = testUser.getBalance();
			atmDao.updateBalance(username, balance + 20);
			assertEquals(balance + 20, atmDao.getUser(username).getBalance(), 0);
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Test(expected=SQLException.class)
	public void testUpdateBalanceError() throws SQLException {
		String username = "";
		User testUser = atmDao.getUser(username);
		double balance = testUser.getBalance();
		atmDao.updateBalance(username, balance + 20);
		assertEquals(balance + 20, atmDao.getUser(username).getBalance(), 0);
	}
	
	
	@Test
	public void testGetBalance() {
		String username = "bill2";
		double expectedBalance = 100;
		try {
			Double bal = atmDao.getBalance(username);
			assertEquals(expectedBalance, bal, 0);
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Ignore
	@Test
	public void testGetConnection() {
		assertEquals(TestSuite.DBConnection, atmDao.getConnection());
	}
	
	@Test
	public void testSetConnection() {
		Connection held = atmDao.getConnection();
		//set connection to something else, assert that it is that, set it back to held, assert it is that 
		try {
			atmDao.setConnection(DriverManager.getConnection("jdbc:hsqldb:mem:user", "vinod", "vinod"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		assertNotEquals(atmDao.getConnection(),held);
	}

}
