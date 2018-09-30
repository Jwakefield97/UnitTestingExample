package edu.msu.atmmachine.app;

import java.io.IOException;
import java.sql.SQLException;

import edu.msu.atmmachine.dao.AtmDao;

public class Atm {
	
	//create the HSQLDB 
	static DBInitializer initDB = new DBInitializer();
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		//create data access object with the connection from initDB
		AtmDao dao = new AtmDao(initDB.getConnection());
		
		
		System.out.println(dao.createUser("aaaa", "aaaf", "aaal")); //should print true because it successfully made an account
		System.out.println(dao.getUser("aaaa").getLastName()); //should print "aaal"
		
		System.out.println(dao.createUser("jake1", "aaaf", "aaal")); //should print false because it didn't create an account
		System.out.println(dao.getUser("jake1").getBalance()); //should print 125.5
		
		dao.updateBalance("jake1", 1000.1); //update balance from 125.5 to 1000.1
		System.out.println(dao.getUser("jake1").getBalance()); //should print 1000.1
		
		System.out.println(dao.isUserExists("d1")); //should print out true because "d1" is already in the database
		
		initDB.destroy(); //destroy user table
	}
    
}
