package controller;

import javax.swing.JFrame;
import javax.swing.JButton;
import backend.ExeQuery;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewPanel {

	private JFrame frame;
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
		frame.setVisible(true);
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
				textArea.setText((String)test.test("view_inventory",null));
			}
		});
				
		JButton btnViewTransactions = new JButton("view Trans.");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ExeQuery test = new ExeQuery();
			textArea.setText((String)test.test("view_transactions",null));
			}
		});
		btnViewTransactions.setBounds(456, 51, 115, 25);
		frame.getContentPane().add(btnViewTransactions);
		
		JButton btnViewSupplier = new JButton("view Supplier");
		btnViewSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				textArea.setText((String)test.test("view_suppliers",null));
			}
		});
		btnViewSupplier.setBounds(456, 90, 115, 25);
		frame.getContentPane().add(btnViewSupplier);

	}
}