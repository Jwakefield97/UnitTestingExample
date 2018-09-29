package edu.msu.atmmachine.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AtmDaoTest.class,UserTest.class})
public class AllTests {
	
	@BeforeClass
	public static void init() throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
	    // initialize database
	    initDatabase();
	}
	
	@AfterClass
    public static void destroy() throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.executeUpdate("DROP TABLE employee");
            connection.commit();
        }
    }
	
	private static void initDatabase() throws SQLException {
		
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE employee (id INT NOT NULL, name VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
            connection.commit();
            statement.executeUpdate(
                    "INSERT INTO employee VALUES (1001,'Vinod Kumar Kashyap', 'vinod@javacodegeeks.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1002,'Dhwani Kashyap', 'dhwani@javacodegeeks.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1003,'Asmi Kashyap', 'asmi@javacodegeeks.com')");
            connection.commit();

        }

    }
		
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "vinod", "vinod");
    }
    
    
}

