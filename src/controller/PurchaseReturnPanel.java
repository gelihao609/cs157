package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PurchaseReturnPanel {

	private JFrame CashierFrame;
	private JTextField itemTextField;
	private JTextField quantityTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseReturnPanel window = new PurchaseReturnPanel();
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
	public PurchaseReturnPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CashierFrame = new JFrame();
		CashierFrame.setBounds(100, 100, 253, 160);
		CashierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CashierFrame.getContentPane().setLayout(null);
		
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
		btnPurchase.setBounds(12, 78, 85, 25);
		CashierFrame.getContentPane().add(btnPurchase);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(121, 78, 71, 25);
		CashierFrame.getContentPane().add(btnReturn);
	}
}
