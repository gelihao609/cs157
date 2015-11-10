package backend;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
	    		 
	    		CallableStatement  stmt = connection.prepareCall("{call archive(?)}");
	    		//CallableStatement  stmt2 = connection.prepareCall("{call archive(\"2015-11-07\")}");
	    		//CallableStatement  stmt3 = connection.prepareCall("{call test(?)}");
	    		//stmt3.setInt(1,10);
	    		Timestamp ts = new Timestamp(115,10,7,0,0,0,0);//2015-11-7
				stmt.setTimestamp(1, ts);
				//Statement stmt = connection.createStatement();
				//ResultSet rs = stmt.executeQuery("select level from employee");
				try{
				stmt.execute();
				//stmt2.execute();
					//stmt3.execute();
					/*if(rs.next()) {
						String result = rs.getString("level");
						System.out.println(result);
						}
						*/
	    		System.out.println("You made it in DST, take control your database now!");
				}
				catch(SQLException e){
		    		System.out.println("Failed! Check output console");
		    		e.printStackTrace();
		    		return;
				}
				


	    	} else {
	    		System.out.println("Failed to make connection!");
	    	}
	 }        
}
