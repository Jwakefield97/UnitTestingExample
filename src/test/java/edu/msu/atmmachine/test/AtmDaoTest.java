package edu.msu.atmmachine.test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import edu.msu.atmmachine.dao.AtmDao;

public class AtmDaoTest {
	
	//create the dao object for testing
	AtmDao atmDao = new AtmDao(TestSuite.DBConnection);
	
	@Test
	public void test() throws SQLException {
		System.out.println(atmDao.getUser("jake1").getFirstName());
		assertEquals(true,true);
	}

}
