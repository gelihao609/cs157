package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
	
	public void archive(Statement stmt, String cutOffTime) throws SQLException
	{
		stmt.executeQuery("call archive(cutoffTime)");
	}
}
