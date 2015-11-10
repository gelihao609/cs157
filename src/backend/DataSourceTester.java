package backend;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;
public class DataSourceTester {
	public static void main (String [] args) throws SQLException
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
	    		/* testing
	    		//CallableStatement  stmt = connection.prepareCall("{call archive(?)}");
				//Timestamp ts = new Timestamp(115,10,7,0,0,0,0);
				//stmt.setTimestamp(1, ts);
				//String st = stmt.toString();
	    		CallableStatement  stmt = connection.prepareCall("{call archive(\"2015-11-07\")}");
				try{
				stmt.execute();
				}
				catch(SQLException e){
		    		System.out.println("Failed! Check output console");
		    		e.printStackTrace();
		    		return;
				}
				*/


	    	} else {
	    		System.out.println("Failed to make connection!");
	    	}
	 }        
}
