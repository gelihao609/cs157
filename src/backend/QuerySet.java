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

	public ArrayList<String> purchase(Connection conn, int operatorid,int itemid, int quantity)
	{
		ArrayList<String> resultContainer = new ArrayList<String>(3);
		Statement stmt;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Error in purchase");
			e.printStackTrace();
		}
		int inventory = get_item_inventory(conn,itemid);
		if(inventory==-1)//item not inStock
		{
			resultContainer.add("itemnotfound");
			return resultContainer;
		}
		else//item in stock
		{
			int newQuantity = inventory-quantity;
			float price = get_item_price(conn,itemid);
			String name = get_item_name(conn,itemid);
			float total;
			update_Inventory(conn,itemid,inventory-quantity);
			resultContainer.add("success");
			resultContainer.add(name);
			if(newQuantity>=0)
			{
				 total = price*quantity;
				resultContainer.add(Integer.toString(quantity));
			}
			else
			{
				 total = price*inventory;
				 resultContainer.add("is insufficient in stock. New purchase quantity: "+Integer.toString(inventory));
			}
			write_transaction(conn,operatorid,itemid,total,"purchase");
			return resultContainer;
		}
	}
	
	private void write_transaction( Connection conn, int operatorid,int itemid, float total, String type)
	{
		String opid = Integer.toString(operatorid);
		String iid = Integer.toString(itemid);
		String t = Float.toString(total);
		try {
			Statement stmt=conn.createStatement();
			String q="Insert into transactions(trans_type,trans_total,item_id,operator_id)"
					+ "values(\""+type+"\","+t+","+iid+","+opid+")";
			stmt.executeUpdate(q);
		} catch (SQLException e) {
			System.out.println("Error in write_transaction");
			e.printStackTrace();
		}
	}
	
	private int get_item_inventory(Connection conn,int itemid) 
	{
		String iid = Integer.toString(itemid);
		ResultSet rs;
		try {
			Statement stmt=conn.createStatement();
			rs = stmt.executeQuery("select quantity from inventory where iditem="+iid);
			if(rs.next())
			{
				return rs.getInt("quantity");
			}
			else return -1;
		} catch (SQLException e) {
			System.out.println("Error in get_item_inventory");
			e.printStackTrace();
		}
		return -1;
	}

	private float get_item_price(Connection conn,int itemid)
	{
		String iid = Integer.toString(itemid);
		ResultSet rs;
		try {
			Statement stmt=conn.createStatement();
			rs = stmt.executeQuery("select item_price from item where item_id="+iid);
			if(rs.next())
			{
				return rs.getFloat("item_price");
			}
			else return -1;
		} catch (SQLException e) {
			System.out.println("Error in get_item_price");
			e.printStackTrace();
		}
		return -1;
	}
	
	private String get_item_name(Connection conn,int itemid)
	{
		String iid = Integer.toString(itemid);
		ResultSet rs;
		try {
			Statement stmt=conn.createStatement();
			rs = stmt.executeQuery("select item_name from item where item_id="+iid);
			if(rs.next())
			{
				return rs.getString("item_name");
			}
			else return "getItemName error";
		} catch (SQLException e) {
			System.out.println("Error in get_item_price");
			e.printStackTrace();
		}
		return "getItemName error";
	}

	private void update_Inventory(Connection conn,int itemid,int newQuantity)
	{
		String iid = Integer.toString(itemid);
		String nq = Integer.toString(newQuantity);
		try {
			Statement stmt=conn.createStatement();
			String q = "update inventory set quantity = "+nq+ " where iditem="+iid;
			stmt.executeUpdate(q);
		} catch (SQLException e) {
			System.out.println("Error in update_inventory");
			e.printStackTrace();
		}
	}
}
