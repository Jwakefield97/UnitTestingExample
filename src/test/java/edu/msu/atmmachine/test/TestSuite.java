package edu.msu.atmmachine.test;



import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.msu.atmmachine.app.DBInitializer;

@RunWith(Suite.class)
@SuiteClasses({AtmDaoTest.class, UserTest.class, AtmTest.class})
public class TestSuite {
	
	private static DBInitializer initDB; 
	public static Connection DBConnection;
	
	@BeforeClass
	public static void init() {
		initDB = new DBInitializer(); //initialize HSQLDB 
		DBConnection = initDB.getConnection(); //set db connection
	}
	
	@AfterClass
	public static void destroy() {
		initDB.destroy(); //destroy user table after tests.
    }
	
	
}

