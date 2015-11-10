package backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
/*
 * run queries from QuerySet
 */
public class ExeQuery {
	
	DataSource ds = DataSourceFactory.getMySQLDataSource();     
    Connection conn = null;
    Statement stmt = null;
     
	public Object test(String cmd, Object arg) {
        
        try {
        	conn = ds.getConnection(); 
        	stmt = conn.createStatement();
        	QuerySet q = new QuerySet();
    		//View inventory
        	if(cmd.equals("view_inventory"))
        	{
        		return q.view_inventory(stmt);
        	}
        	//view transactions
        	if(cmd.equals("view_transactions"))
        	{
        		return q.view_transactions(stmt);
        	}
        	//call archive
        	if(cmd.equals("archive"))
        	{
            	java.util.Date cutOffDate = (java.util.Date) arg;
        		q.archive(conn,cutOffDate);
        	}
        	//view supplier
        	if(cmd.equals("view_suppliers"))
        	{
        		return q.view_suppliers(stmt);
        	}
        	//authenticate
        	if(cmd.equals("login"))
        	{
        		@SuppressWarnings("unchecked")
				ArrayList<Object> container = (ArrayList<Object>) arg;
        		int id = (int) container.get(0);
        		String password = (String) container.get(1);
        		return q.authenticate(stmt,id,password);
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
		return "Finished";
}

}
