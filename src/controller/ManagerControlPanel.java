package controller;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ManagerControlPanel {

	private JFrame managerFrame;
	private int userid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerControlPanel window = new ManagerControlPanel(1003);
					window.managerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
		managerFrame.setBounds(100, 100, 180, 309);
		managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerFrame.getContentPane().setLayout(null);
		managerFrame.setVisible(true);

		JButton btnPurchasereturn = new JButton("Purchase&Return");
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
		btnPurchasereturn.setBounds(12, 62, 138, 25);
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
		btnView.setBounds(12, 138, 138, 25);
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
		btnArchive.setBounds(12, 100, 138, 25);
		managerFrame.getContentPane().add(btnArchive);
		
		JLabel lblManagerControl = new JLabel("Manager Control");
		lblManagerControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerControl.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblManagerControl.setBounds(12, 13, 138, 16);
		managerFrame.getContentPane().add(lblManagerControl);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(22, 33, 32, 16);
		managerFrame.getContentPane().add(lblId);
		
		JButton btnCheckfind = new JButton("Check&Find");
		btnCheckfind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckPanel();
			}
		});
		btnCheckfind.setBounds(12, 176, 138, 25);
		managerFrame.getContentPane().add(btnCheckfind);
		
		JButton btnModify = new JButton("Modify...");
		btnModify.setBounds(12, 214, 138, 25);
		managerFrame.getContentPane().add(btnModify);
	}
}
