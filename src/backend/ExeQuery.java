package backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
        	if(cmd.equals("purchase")||cmd.equals("return"))
        	{
        		@SuppressWarnings("unchecked")
				ArrayList<String> container = (ArrayList<String>) arg;
        		int operatorid = Integer.parseInt(container.get(0));
        		int itemid = Integer.parseInt(container.get(1));
        		int quantity = Integer.parseInt(container.get(2));
        		return cmd.equals("purchase")?q.purchaseReturn(conn,operatorid,itemid,quantity,"purchase")
        				:q.purchaseReturn(conn,operatorid,itemid,quantity,"return");
        	}
        	if(cmd.equals("check_cashier_trans_count"))
        	{
        		return q.check_cashier_trans_count(stmt);
        	}
        	if(cmd.equals("check_item_has_multi_supplier"))
        	{
        		return q.check_item_has_multi_supplier(stmt);
        	}
        	if(cmd.equals("find_supplier"))
        	{
        		String itmid = (String) arg;
        		return q.find_supplier(stmt,itmid);
        	}
        	if(cmd.equals("check_item_not_sold"))
        	{
        		return q.check_item_not_sold(stmt);
        	}
        	if(cmd.equals("find_item_by_supplier"))
        	{
        		String supplierid = (String) arg;
        		return q.find_item_by_supplier(stmt,supplierid);
        	}
        	if(cmd.equals("find_item_below_quantity"))
        	{
        		String quantity = (String) arg;
        		return q.find_item_below_quantity(stmt,quantity);
        	}
        	if(cmd.equals("getEarning"))
        	{
				@SuppressWarnings("unchecked")
				ArrayList<Date> container = (ArrayList<Date>) arg;
				Date start = container.get(0);
				Date end = container.get(1);
        		return q.getEarning(stmt,start,end);
        	}
        	if(cmd.equals("addItem"))
        	{
        		@SuppressWarnings("unchecked")
				ArrayList<String> container = (ArrayList<String>) arg;
        		String name = container.get(0);
        		String price = container.get(1);
        		return q.addItem(stmt,name,price);
        	}
        	if(cmd.equals("modifyItem"))
        	{
        		@SuppressWarnings("unchecked")
				ArrayList<String> container = (ArrayList<String>) arg;
        		String id = container.get(0);
        		String price = container.get(1);
        		return q.modifyItem(stmt,id,price);
        	}
        	if(cmd.equals("viewEmployee"))
        	{
        		return q.viewEmployee(stmt);
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
