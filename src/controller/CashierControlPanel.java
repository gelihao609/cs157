package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CashierControlPanel {
//YYYYYOOOOOOOOOOOOO
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
		cashierFrame.setBounds(100, 100, 183, 151);
		cashierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cashierFrame.getContentPane().setLayout(null);
		cashierFrame.setVisible(true);
		JButton btnPurchasereturn = new JButton("Purchase/Return");
		btnPurchasereturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new PurchaseReturnPanel(userid);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnPurchasereturn.setBounds(12, 67, 138, 25);
		cashierFrame.getContentPane().add(btnPurchasereturn);
		
		JLabel lblCashierControl = new JLabel("Cashier Control");
		lblCashierControl.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblCashierControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashierControl.setBounds(12, 13, 138, 16);
		cashierFrame.getContentPane().add(lblCashierControl);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(22, 38, 56, 16);
		cashierFrame.getContentPane().add(lblId);
	}

}
