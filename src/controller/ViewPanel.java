package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import backend.ExeQuery;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewPanel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPanel window = new ViewPanel();
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
	public ViewPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton btnViewInventory = new JButton("view Inventory");
		btnViewInventory.setBounds(456, 13, 115, 25);
		frame.getContentPane().add(btnViewInventory);
		JButton btnClearContent = new JButton("Clear");		
		JScrollPane testAreaScrollContainer = new JScrollPane();
		testAreaScrollContainer.setBounds(12, 13, 426, 282);
		frame.getContentPane().add(testAreaScrollContainer);
		
		JTextArea textArea = new JTextArea();
		testAreaScrollContainer.setViewportView(textArea);
		btnClearContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClearContent.setBounds(489, 270, 82, 25);
		frame.getContentPane().add(btnClearContent);

		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				textArea.setText(test.test("view_inventory"));
			}
		});
		
		
		JButton btnViewTransactions = new JButton("view Trans.");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ExeQuery test = new ExeQuery();
			textArea.setText(test.test("view_transactions"));
			}
		});
		btnViewTransactions.setBounds(456, 51, 115, 25);
		frame.getContentPane().add(btnViewTransactions);

	}
}