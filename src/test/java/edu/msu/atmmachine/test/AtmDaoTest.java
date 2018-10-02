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
		User user = new User("bob72","bob","jones",0.0);
		try {
			atmDao.createUser(user.getUsername(), user.getFirstName(), user.getLastName());
			User testUser = atmDao.getUser(user.getUsername());
			assertEquals(testUser, user);
		} catch(SQLException e) {
			fail();
		}
	}
	
	@Test
	public void testGetUser() {
		User user = new User("bill2","Billy","Janson",100.0);
		try {
			User testUser = atmDao.getUser(user.getUsername());
			assertEquals(testUser, user);
		} catch(SQLException e) {
			fail();
		}
	}
	
	
	@Test
	public void testIsUserExistsTrue() {
		String usernameExists = "bill2";
		try {
			assertTrue(atmDao.isUserExists(usernameExists));
		} catch(SQLException e) {
			fail();
		}
	}
	
	
	@Test
	public void testIsUserExistsFalse() {
		String usernameNotExists = "someguy";
		try {
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
