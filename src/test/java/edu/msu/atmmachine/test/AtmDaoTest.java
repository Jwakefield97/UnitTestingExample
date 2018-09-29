package edu.msu.atmmachine.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class AtmDaoTest {
	
	private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "vinod", "vinod");
    }
    
   
    
    private int getTotalRecords() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet result = statement.executeQuery("SELECT count(*) as total FROM employee");

            if (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

	@Test
    public void getTotalRecordsTest() {
        assertEquals(3, getTotalRecords());
    }
    
     
    
    @Test
    public void checkNameExistsTest() {

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            ResultSet result = statement.executeQuery("SELECT name FROM employee");

            if (result.first()) {
            	assertEquals("Vinod Kumar Kashyap", result.getString("name"));
            }

            if (result.last()) {
                assertEquals("Asmi Kashyap", result.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
