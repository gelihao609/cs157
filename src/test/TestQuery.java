package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
/*
 * run queries from QuerySet
 */
public class TestQuery {
	
	public static void main(String[] args) {
        DataSource ds = DataSourceFactory.getMySQLDataSource();     
        Connection conn = null;
        Statement stmt = null;
        try {
        	conn = ds.getConnection(); 
        	stmt = conn.createStatement();
        	QuerySet q = new QuerySet();
                QuerySet q2 = new QuerySet();
        	//View inventory
        	System.out.println(q.view_inventory(stmt));
                System.out.println(q2.login(stmt, 1001,"123john4"));
        	
        	
        	
        	
        	
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
