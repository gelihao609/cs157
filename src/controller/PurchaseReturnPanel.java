package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import backend.ExeQuery;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PurchaseReturnPanel {

	private JFrame CashierFrame;
	private JTextField itemTextField;
	private JTextField quantityTextField;
	private int userid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseReturnPanel window = new PurchaseReturnPanel(1);
					window.CashierFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PurchaseReturnPanel(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		CashierFrame = new JFrame();
		userid = id;
		CashierFrame.setBounds(100, 100, 253, 160);
		CashierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		CashierFrame.getContentPane().setLayout(null);
		CashierFrame.setVisible(true);
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(12, 13, 56, 16);
		CashierFrame.getContentPane().add(lblItem);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(12, 42, 56, 16);
		CashierFrame.getContentPane().add(lblQuantity);
		
		itemTextField = new JTextField();
		itemTextField.setBounds(75, 10, 116, 22);
		CashierFrame.getContentPane().add(itemTextField);
		itemTextField.setColumns(10);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(75, 39, 116, 22);
		CashierFrame.getContentPane().add(quantityTextField);
		quantityTextField.setColumns(10);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//item: 1.userId 2.itemId 3. itemQuantity
				ArrayList<String> dataContainer = new ArrayList<String>(3);
				dataContainer.add(Integer.toString(userid));
				dataContainer.add(itemTextField.getText());
				dataContainer.add(quantityTextField.getText());
				ExeQuery test = new ExeQuery();
				@SuppressWarnings("unchecked")
				//1. responseCmd 2.item name 3. quantity in stock
				ArrayList<String> result = (ArrayList<String>) test.test("purchase", dataContainer);
				String response = result.get(0);
				if(response.equals("itemnotfound"))
				{
					JOptionPane.showMessageDialog(CashierFrame, "Out of stock");

				}
				else if(response.equals("insufficient"))
				{
					JOptionPane.showMessageDialog(CashierFrame, "Insufficient. Only charge quantity of "+result.get(2) + " for "+result.get(1));
				}
				else if(response.equals("success"))
				{
					JOptionPane.showMessageDialog(CashierFrame, "Purchased "+result.get(1)+" Quantity: "+result.get(2));
				}
				else
				{
					JOptionPane.showMessageDialog(CashierFrame, "Unexpected error in purchaseReturnPan.");
				}
			}
		});
		btnPurchase.setBounds(12, 78, 97, 25);
		CashierFrame.getContentPane().add(btnPurchase);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//item: 1.userId 2.itemId 3. itemQuantity
				ArrayList<String> dataContainer = new ArrayList<String>(3);
				dataContainer.add(Integer.toString(userid));
				dataContainer.add(itemTextField.getText());
				dataContainer.add(quantityTextField.getText());
				ExeQuery test = new ExeQuery();
				@SuppressWarnings("unchecked")
				//1. responseCmd 2.item name 3. quantity in stock
				ArrayList<String> result = (ArrayList<String>) test.test("return", dataContainer);
				String response = result.get(0);
				if(response.equals("itemnotfound"))
				{
					JOptionPane.showMessageDialog(CashierFrame, "It's not bought from ours.");
				}
				else if(response.equals("success"))
				{
					JOptionPane.showMessageDialog(CashierFrame, "Return "+result.get(1)+" Quantity: "+result.get(2)+" succeed.");
				}

			}
		});
		btnReturn.setBounds(138, 78, 85, 25);
		CashierFrame.getContentPane().add(btnReturn);
	}
}
