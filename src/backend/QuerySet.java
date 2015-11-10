package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
/*
 * put all queries here and call from testQuery
 */
public class QuerySet {
  	//view all items' inventories
	public String view_inventory(Statement stmt) throws SQLException
	{
		ResultSet rs = stmt.executeQuery(
				  "SELECT item_id, item_name, quantity "
				+ "FROM item,inventory "
				+ "where item.item_id=inventory.iditem;");
		String result ="ID\tName\tQuantity\n";
		while(rs.next()){
			result+=rs.getInt("item_id")+"\t"+rs.getString("item_name")+"\t"+rs.getInt("quantity")+"\n";
        }
		return result;
	}
	public String view_suppliers(Statement stmt) throws SQLException
	{
		ResultSet rs = stmt.executeQuery(
				  "SELECT *"
				+ "FROM supplier");
		String result ="Name\tPhone Number\tID\n";
		while(rs.next()){
			result+=rs.getString("supplier_name")+"\t"+rs.getString("phone_num")+"\t"+rs.getInt("supplier_id")+"\n";
        }
		return result;
	}
	public String view_transactions(Statement stmt) throws SQLException
	{
		ResultSet rs = stmt.executeQuery(
				  "SELECT idtrans, trans_type, trans_total, updatedAt, item_id,operator_id "
				+ "FROM transactions");
		String result ="ID\tType\tTotal\tUpdatedAt\tItemID\tOperator\n";
		while(rs.next()){
			float total = rs.getFloat("trans_total");
			String formattedTotal = String.format("%.02f", total); 
			String time=rs.getTimestamp("updatedAt").toString().substring(0,10);
			result+=rs.getInt("idtrans")+"\t"+rs.getString("trans_type")+"\t"+formattedTotal+"\t"+time+"\t"+rs.getInt("item_id")+"\t"+rs.getInt("operator_id")+"\n";
        }
		return result;
	}
	
	public void archive(Connection conn, Date cutOffTime)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("call archive(?)");
			Timestamp ts = new Timestamp(cutOffTime.getTime());
			stmt.setTimestamp(1, ts);
			stmt.executeQuery();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public ArrayList<Object> authenticate(Statement stmt, int id, String password)
	{
		//result set, 1st store type, 2nd store id
		ArrayList<Object> resultToPass = new ArrayList<Object>(2);
		try
		{
			String q = "SELECT level,employee_ID FROM employee WHERE employee_ID="+Integer.toString(id)+" and password = \""+password+"\"";
			ResultSet rs = stmt.executeQuery(q);
					if(!rs.next()){
						resultToPass.add("fail");
		                return resultToPass;
		            }
		            else if(rs.getString("level").equals("cashier"))
		            	{	
							resultToPass.add("cashier");
							resultToPass.add(rs.getInt("employee_ID"));
		            		return resultToPass;
		            	}
		            else if(rs.getString("level").equals("manager"))
		            	{
							resultToPass.add("manager");
							resultToPass.add(rs.getInt("employee_ID"));
							return resultToPass; 
		            	}
		            else 
		            	{
							resultToPass.add("IT");
							resultToPass.add(rs.getInt("employee_ID"));
							return resultToPass; 
		            	}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		resultToPass.add("error");
		return resultToPass;
	}

}