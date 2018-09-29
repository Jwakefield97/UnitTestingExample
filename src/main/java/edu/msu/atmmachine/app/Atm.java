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
		
		System.out.println(initDB.getConnection());
		
		initDB.destroy(); //destroy user table
	}
    
}
