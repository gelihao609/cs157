package controller;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class ManagerControlPanel {

	private JFrame managerFrame;
	private int userid;
	/**
	 * Create the application.
	 */
	public ManagerControlPanel(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		userid=id;
		managerFrame = new JFrame("Manager Control Panel");
		managerFrame.setBounds(100, 100, 319, 127);
		managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerFrame.getContentPane().setLayout(null);
		managerFrame.setVisible(true);

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
		btnPurchasereturn.setBounds(12, 13, 138, 25);
		managerFrame.getContentPane().add(btnPurchasereturn);
		
		JButton btnView = new JButton("View...");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new ViewPanel();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnView.setBounds(172, 13, 117, 25);
		managerFrame.getContentPane().add(btnView);
		
		JButton btnArchive = new JButton("Archive");
		btnArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							 new ArchivePanel();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnArchive.setBounds(12, 44, 138, 25);
		managerFrame.getContentPane().add(btnArchive);
	}
}
