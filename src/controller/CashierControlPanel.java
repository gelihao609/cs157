package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CashierControlPanel {

	private JFrame cashierFrame;
	private int userid;

	/**
	 * Create the application.
	 */
	public CashierControlPanel(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		userid=id;
		cashierFrame = new JFrame("Cashier Control Panel");
		cashierFrame.setBounds(100, 100, 319, 127);
		cashierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cashierFrame.getContentPane().setLayout(null);
		cashierFrame.setVisible(true);
		JButton btnPurchasereturn = new JButton("Purchase/Return");
		btnPurchasereturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPurchasereturn.setBounds(12, 13, 138, 25);
		cashierFrame.getContentPane().add(btnPurchasereturn);
	}

}
