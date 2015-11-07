package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class TestQuery {

	public static void main(String[] args) {
        DataSource ds = DataSourceFactory.getMySQLDataSource();     
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	conn = ds.getConnection(); 
        	stmt = conn.createStatement();
            rs = stmt.executeQuery("select first_name, last_name from employee");
            while(rs.next()){
                System.out.println("User firstName="+rs.getString("first_name")+", lastName="+rs.getString("last_name"));
            }
    	} catch (SQLException e) {
    		System.out.println("Connection Failed! Check output console");
    		e.printStackTrace();
    	}catch(Exception e){
    	      //Handle errors for Class.forName
    	      e.printStackTrace();
    	   }
        finally{
            //finally block used to close resources
            try{
               if(stmt!=null)
                  stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
               if(conn!=null)
                  conn.close();
            }catch(SQLException se){
               se.printStackTrace();
            }//end finally try
	}
}
}
