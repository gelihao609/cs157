package test;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
public class DataSourceTester {
	public static void main (String [] args)
	 {
	        DataSource ds = DataSourceFactory.getMySQLDataSource();     
	        Connection connection =  null; 
	        try {
	    		connection = ds.getConnection(); 
	    	} catch (SQLException e) {
	    		System.out.println("Connection Failed! Check output console");
	    		e.printStackTrace();
	    		return;
	    	}
	     
	    	if (connection != null) {
	    		System.out.println("You made it in DST, take control your database now!");
	    	} else {
	    		System.out.println("Failed to make connection!");
	    	}
	 }        
}
