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

	public ArrayList<String> purchaseReturn(Connection conn, int operatorid,int itemid, int quantity, String type)
	{
		ArrayList<String> resultContainer = new ArrayList<String>(3);
		int inventory = get_item_inventory(conn,itemid);
		if(inventory==-1)//item not inStock
		{
			resultContainer.add("itemnotfound");
			return resultContainer;
		}
		else//item in stock
		{
			int newQuantity = type.equals("purchase")?(inventory-quantity):(inventory+quantity);
			float price = get_item_price(conn,itemid);
			String name = get_item_name(conn,itemid);
			float total;
			if(type.equals("purchase")) update_Inventory(conn,itemid,newQuantity);
			else if(type.equals("return"))update_Inventory(conn,itemid,newQuantity);
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
			if(type.equals("purchase")) write_transaction(conn,operatorid,itemid,total,"purchase");
			else if(type.equals("return")) write_transaction(conn,operatorid,itemid,total*(-1),"return");
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
	
	public String check_cashier_trans_count(Statement stmt) {
		String q = "SELECT employee_ID, first_name, last_name, count(*) as NumOfTransaction "
				+ "FROM transactions join employee on employee_ID=operator_id "
				+ "GROUP BY operator_id HAVING operator_id in "
				+ "(SELECT employee_ID FROM employee WHERE level=\"cashier\")";
		String result ="ID\tName\tTotalNums\n";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				int id = rs.getInt("employee_ID");
				String name = rs.getString("last_name")+","+rs.getString("first_name");
				int count = rs.getInt("NumOfTransaction"); 
				result+=id+"\t"+name+"\t"+count+"\n";
	        }			
		} catch (SQLException e) {
			System.out.println("Error in check_cashier_trans_count");
			e.printStackTrace();
		}
		return result;
	}
	public String check_item_has_multi_supplier(Statement stmt) {
		String q ="SELECT item_id,item_name "
				+ "FROM item "
				+ "WHERE item_id in "
				+ "(SELECT iditem "
				+ "FROM supply as s1 "
				+ "WHERE exists"
				+ "(SELECT * "
				+ "FROM supply "
				+ "WHERE s1.iditem=iditem and s1.idsupplier!=idsupplier))";
		String result ="ID\tName\n";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				int id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				result+=id+"\t"+name+"\n";
	        }			
		} catch (SQLException e) {
			System.out.println("Error in check_item_has_multi_supplier");
			e.printStackTrace();
		}
		return result;
	}
	public String check_item_not_sold(Statement stmt) {
		String q="SELECT item.item_id,item.item_name "
				+ "FROM item  LEFT JOIN transactions on item.item_id=transactions.item_id "
				+ "WHERE transactions.idtrans is NULL";
		String result ="ID\tName\n";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				int id = rs.getInt("item.item_id");
				String name = rs.getString("item.item_name");
				result+=id+"\t"+name+"\n";
			}
		} catch (SQLException e) {
			System.out.println("Error in check_item_not_sold");
			e.printStackTrace();
		}
		return result;
	}
	public String find_supplier(Statement stmt, String itmid) {
		String q="SELECT supplier_name,phone_num,supplier_id "
				+ "FROM supplier "
				+ "WHERE supplier_id in "
				+ "(SELECT supply.idsupplier "
				+ "FROM supply join item on iditem=item_id "
				+ "where iditem="+itmid+")";
		String result ="ID\tName\tContact\n";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				int id = rs.getInt("supplier_id");
				String name = rs.getString("supplier_name");
				String phone = rs.getString("phone_num");
				result+=id+"\t"+name+"\t"+phone+"\n";
			}
			if(result.equals("ID\tName\tContact\n")) result="Nofound";
		} catch (SQLException e) {
			System.out.println("Error in check_item_has_multi_supplier");
			e.printStackTrace();
		}
		return result;
	}

	public String find_item_by_supplier(Statement stmt, String supplierid) {
		if(supplierid.length()!=0)
		{
		String q="SELECT item_id,item_name "
				+ "FROM item "
				+ "WHERE item_id in "
				+ "(SELECT iditem "
				+ "FROM supply "
				+ "WHERE idsupplier="+supplierid+")";
		String result ="ID\tName\n";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				int id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				result+=id+"\t"+name+"\n";
			}
			if(result.equals("ID\tName\n")) result="Nofound";
		} catch (SQLException e) {
			System.out.println("Error in find_item_by_supplier");
			e.printStackTrace();
		}
		return result;
		}
		else
			return "NoPointer";
	}
	public String find_item_below_quantity(Statement stmt, String quantity) {
		if(quantity.length()!=0)
		{
		String q="SELECT item.item_id,item.item_name,inventory.quantity "
				+ "FROM item join inventory on item.item_id=inventory.iditem "
				+ "WHERE inventory.quantity<"+quantity;
		String result ="ID\tName\tQuantity\n";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				int id = rs.getInt("item.item_id");
				String name = rs.getString("item.item_name");
				int quan = rs.getInt("inventory.quantity");
				result+=id+"\t"+name+"\t"+quan+"\n";
			}
			if(result.equals("ID\tName\tQuantity\n")) result="Nofound";
		} catch (SQLException e) {
			System.out.println("Error in find_item_below_quantity");
			e.printStackTrace();
		}
		return result;
		}
		else return "NoPointer";
	}
	public String getEarning(Statement stmt, Date start, Date end) {
		if(start!=null && end!=null)
		{
		Timestamp st = new Timestamp(start.getTime());
		Timestamp ed = new Timestamp(end.getTime());
		String q="SELECT SUM(trans_total) as total "
				+ "FROM transactions "
				+ "WHERE updatedAt >= \""+st+"\""+"and updatedAt<=\""+ed+"\"";
		String result="";
		try {
			ResultSet rs = stmt.executeQuery(q);
			while(rs.next()){
				float id = rs.getFloat("total");
				result+=id;
			}
			if(result.equals("")) result="Transactions not found";
		} catch (SQLException e) {
			System.out.println("Error in find_item_below_quantity");
			e.printStackTrace();
		}
		return result;
	}
		else return "NoPointer";
	}
	public String addItem(Statement stmt, String name, String price) {
		if(name.length()!=0 && price.length()!=0)
		{
			String q="Insert into item(item_name,item_price) "
					+ "values(\""+name+"\","+price+")";
			String result="";
			try {
				int rs = stmt.executeUpdate(q);
				if(rs==0) result="ViolateRule";
				else result ="success";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		else
		return "NoPointer";
	}
	public String modifyItem(Statement stmt, String id, String price) {
		if(id.length()!=0 && price.length()!=0)
		{
			String q="UPDATE item SET item_price ="+price
					+ " WHERE item_id="+id;
			String result="";
			try {
				int rs = stmt.executeUpdate(q);
				if(rs==0) result="ViolateRule";
				else result ="success";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		else
		return "NoPointer";
	}
	public String viewEmployee(Statement stmt) {
		String result ="ID\tName\tLevel\n";
		try {
			ResultSet rs = stmt.executeQuery(
					  "SELECT employee_ID, first_name, last_name, level "
					+ "FROM employee");
			while(rs.next()){
				int id = rs.getInt("employee_ID");
				String name = rs.getString("last_name")+","+rs.getString("first_name");
				String level= rs.getString("level");
				result+=id+"\t"+name+"\t"+level+"\n";
     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
