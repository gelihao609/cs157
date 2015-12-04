package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import backend.ExeQuery;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ModifyPanel {

	private JFrame frame;
	private JTextField nametextField;
	private JTextField pricetextField;
	private JTextField idtextField;
	private JTextField newPricetextField;
	private JTextField addIdtextField;
	private JTextField removeItemTF;
	private JTextField removeInventoryTF;
	private JTextField removeTransTF;
	private JTextField removeSupplyTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPanel window = new ModifyPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifyPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Modify Panel");
		frame.setBounds(100, 100, 693, 359);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JLabel lblAddItem = new JLabel("Add item:");
		lblAddItem.setBounds(12, 13, 62, 16);
		frame.getContentPane().add(lblAddItem);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 68, 56, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(12, 97, 56, 16);
		frame.getContentPane().add(lblPrice);
		
		nametextField = new JTextField();
		nametextField.setBounds(71, 61, 116, 22);
		frame.getContentPane().add(nametextField);
		nametextField.setColumns(10);
		
		pricetextField = new JTextField();
		pricetextField.setBounds(71, 91, 116, 22);
		frame.getContentPane().add(pricetextField);
		pricetextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		
		btnAdd.setBounds(102, 126, 85, 25);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblChangeItemPrice = new JLabel("Modify item Price:");
		lblChangeItemPrice.setBounds(229, 13, 121, 16);
		frame.getContentPane().add(lblChangeItemPrice);
		
		idtextField = new JTextField();
		idtextField.setBounds(306, 35, 97, 22);
		frame.getContentPane().add(idtextField);
		idtextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Item id:");
		lblNewLabel.setBounds(229, 42, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewPrice = new JLabel("New Price:");
		lblNewPrice.setBounds(229, 71, 72, 16);
		frame.getContentPane().add(lblNewPrice);
		
		newPricetextField = new JTextField();
		newPricetextField.setBounds(306, 65, 97, 22);
		frame.getContentPane().add(newPricetextField);
		newPricetextField.setColumns(10);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(318, 126, 85, 25);
		frame.getContentPane().add(btnModify);
		
		JScrollPane addResultscrollPane = new JScrollPane();
		addResultscrollPane.setBounds(12, 164, 175, 108);
		frame.getContentPane().add(addResultscrollPane);
		
		JTextArea AddResulttextArea = new JTextArea();
		addResultscrollPane.setViewportView(AddResulttextArea);
		
		JScrollPane modifyReslscrollPane = new JScrollPane();
		modifyReslscrollPane.setBounds(229, 164, 174, 108);
		frame.getContentPane().add(modifyReslscrollPane);
		
		JTextArea modifyResulttextArea = new JTextArea();
		modifyResulttextArea.setText("");
		modifyReslscrollPane.setViewportView(modifyResulttextArea);
		
		JButton clearAddBtn = new JButton("Clear");
		
		clearAddBtn.setBounds(102, 274, 85, 25);
		frame.getContentPane().add(clearAddBtn);
		
		JButton clearModBtn = new JButton("Clear");
		clearModBtn.setBounds(318, 274, 85, 25);
		frame.getContentPane().add(clearModBtn);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 35, 56, 16);
		frame.getContentPane().add(lblId);
		
		addIdtextField = new JTextField();
		addIdtextField.setBounds(71, 33, 116, 22);
		frame.getContentPane().add(addIdtextField);
		addIdtextField.setColumns(10);
		
		JLabel lblRemoveItem = new JLabel("Remove item:");
		lblRemoveItem.setBounds(440, 13, 106, 16);
		frame.getContentPane().add(lblRemoveItem);
		
		JLabel lblItemId = new JLabel("Item id:");
		lblItemId.setBounds(450, 38, 56, 16);
		frame.getContentPane().add(lblItemId);
		
		removeItemTF = new JTextField();
		removeItemTF.setBounds(518, 35, 116, 22);
		frame.getContentPane().add(removeItemTF);
		removeItemTF.setColumns(10);
		
		JLabel lblRemoveInventory = new JLabel("Remove inventory:");
		lblRemoveInventory.setBounds(440, 91, 116, 16);
		frame.getContentPane().add(lblRemoveInventory);
		
		JButton btnGo_Item = new JButton("Go");
		btnGo_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				String id=removeItemTF.getText();
				String result=(String) test.test("removeItem", id);
				if(result.equals("success")) AddResulttextArea.setText("Item is removed." );
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
				else if((result.equals("foreignKeyViolate"))){
					AddResulttextArea.setText("Foreign key violation.");
					JOptionPane.showMessageDialog(frame, "Foreign key violation. Please remove the corresponding item in inventory first.");
				}

				else AddResulttextArea.setText("Failed.");
			}
		});
		btnGo_Item.setBounds(572, 59, 62, 25);
		frame.getContentPane().add(btnGo_Item);
		
		JLabel lblNewLabel_1 = new JLabel("Item id:");
		lblNewLabel_1.setBounds(440, 118, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		removeInventoryTF = new JTextField();
		removeInventoryTF.setBounds(518, 115, 116, 22);
		frame.getContentPane().add(removeInventoryTF);
		removeInventoryTF.setColumns(10);
		
		JButton btnGo_Inventory = new JButton("Go");
		btnGo_Inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String id=removeInventoryTF.getText();
				String result=(String) test.test("removeInventory", id);
				if(result.equals("success")) AddResulttextArea.setText("Inventory is removed." );
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
				else AddResulttextArea.setText("Failed.");
			}
		});
		btnGo_Inventory.setBounds(572, 137, 62, 25);
		frame.getContentPane().add(btnGo_Inventory);
		
		JLabel lblRemoveTransaction = new JLabel("Remove transaction:");
		lblRemoveTransaction.setBounds(436, 164, 120, 16);
		frame.getContentPane().add(lblRemoveTransaction);
		
		JLabel lblItemId_1 = new JLabel("Item id:");
		lblItemId_1.setBounds(440, 186, 56, 16);
		frame.getContentPane().add(lblItemId_1);
		
		removeTransTF = new JTextField();
		removeTransTF.setBounds(518, 186, 116, 22);
		frame.getContentPane().add(removeTransTF);
		removeTransTF.setColumns(10);
		
		JButton btnGo_trans = new JButton("Go");
		btnGo_trans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String id=removeTransTF.getText();
				String result=(String) test.test("removeTransaction", id);
				if(result.equals("success")) AddResulttextArea.setText("Transaction is removed." );
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
				else AddResulttextArea.setText("Failed.");
			}
		});
		btnGo_trans.setBounds(572, 209, 62, 25);
		frame.getContentPane().add(btnGo_trans);
		
		JLabel lblRemoveSupply = new JLabel("Remove supply:");
		lblRemoveSupply.setBounds(440, 234, 116, 16);
		frame.getContentPane().add(lblRemoveSupply);
		
		JLabel lblItemId_2 = new JLabel("Item id:");
		lblItemId_2.setBounds(440, 255, 56, 16);
		frame.getContentPane().add(lblItemId_2);
		
		removeSupplyTF = new JTextField();
		removeSupplyTF.setBounds(518, 255, 116, 22);
		frame.getContentPane().add(removeSupplyTF);
		removeSupplyTF.setColumns(10);
		
		JButton btnGo_Supply = new JButton("Go");
		btnGo_Supply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String id=removeTransTF.getText();
				String result=(String) test.test("removeSupply", id);
				if(result.equals("success")) AddResulttextArea.setText("Supply is removed." );
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
				else AddResulttextArea.setText("Failed.");
			}
		});
		btnGo_Supply.setBounds(574, 279, 62, 25);
		frame.getContentPane().add(btnGo_Supply);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String name=nametextField.getText();
				String price=pricetextField.getText();
				String newID=addIdtextField.getText();
				ArrayList<String> requestContainer = new ArrayList<String>(3);
				requestContainer.add(name);
				requestContainer.add(price);
				requestContainer.add(newID);
				String result=(String) test.test("addItem", requestContainer);
				if(result.equals("success")) AddResulttextArea.setText(name+" is added." );
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
				else if((result.equals("DuplicateKey"))){
					AddResulttextArea.setText("Primary key violation.");
					JOptionPane.showMessageDialog(frame, "Primary key violation. The new id is in use.");
				}

				else AddResulttextArea.setText("Failed.");
			}
		});
		
		clearAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddResulttextArea.setText(null);
			}
		});
		clearModBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyResulttextArea.setText(null);
			}
		});
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String id=idtextField.getText();
				String price=newPricetextField.getText();
				ArrayList<String> requestContainer = new ArrayList<String>(2);
				requestContainer.add(id);
				requestContainer.add(price);
				String result=(String) test.test("modifyItem", requestContainer);
				if(result.equals("success")) modifyResulttextArea.setText("Price is modified to "+price);
				else if((result.equals("NoPointer")))modifyResulttextArea.setText("Please enter valid data.");
				else if((result.equals("ViolateRule")))modifyResulttextArea.setText("Item ID not found.");
				else modifyResulttextArea.setText("Failed");
			}
		});
	}
}
