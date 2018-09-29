package edu.msu.atmmachine.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBInitializer {
	
	//query to create the user table
	private static final String CREATE_USER_TABLE = "DROP TABLE IF EXISTS user "
													+ "CREATE TABLE user "
													+ "(username VARCHAR(50) NOT NULL, "
													+ "firstName VARCHAR(100) "
													+ "NOT NULL, lastName VARCHAR(100), "
													+ "balance DOUBLE NOT NULL)";
	//queries to insert records into the user table.
	private static final String[] INITIAL_USER_INSERTS = new String[] {
			"INSERT INTO user VALUES ('bill2','Billy','Janson',100.0)",
			"INSERT INTO user VALUES ('jake1','Jacob','Wakefield',125.5)",
			"INSERT INTO user VALUES ('noah12','Noah','Zeilmann',1000.25)",
			"INSERT INTO user VALUES ('d1','Dario','Mendez',512.0)",
			"INSERT INTO user VALUES ('colt45','Colten','Johnson',50.12)",
			"INSERT INTO user VALUES ('trent9','Trenton','Nale',750.45)"
	};
	 
	private static final String DROP_USER_TABLE = "DROP TABLE user"; //used for clean up purposes
	
	public DBInitializer() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); //set hsqldb driver
			initDatabase(); // initialize database
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:user", "vinod", "vinod");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void initDatabase() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
        	statement.executeQuery(CREATE_USER_TABLE);
        	connection.commit();
        	for(String userInsert: INITIAL_USER_INSERTS) {
        		statement.executeUpdate(userInsert);
        		connection.commit();
        	}
        }

    }
    
    public void destroy() {
    	try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
    		statement.executeUpdate(DROP_USER_TABLE);
    		connection.commit();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
}
