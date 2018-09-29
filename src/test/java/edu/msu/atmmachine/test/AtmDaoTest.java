package edu.msu.atmmachine.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.msu.atmmachine.dao.AtmDao;

public class AtmDaoTest {
	
	//create the dao object for testing
	AtmDao atmDao = new AtmDao(TestSuite.DBConnection);
	
	@Test
	public void test() {
		assertEquals(true,true);
	}

}
