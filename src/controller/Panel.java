package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import test.TestQuery;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Panel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel window = new Panel();
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
	public Panel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton btnViewInventory = new JButton("view Inventory");
		btnViewInventory.setBounds(45, 13, 115, 25);
		frame.getContentPane().add(btnViewInventory);
		JButton btnClearContent = new JButton("Clear content");		
		JScrollPane testAreaScrollContainer = new JScrollPane();
		testAreaScrollContainer.setBounds(55, 51, 236, 161);
		frame.getContentPane().add(testAreaScrollContainer);
		
		JTextArea textArea = new JTextArea();
		testAreaScrollContainer.setViewportView(textArea);
		btnClearContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClearContent.setBounds(167, 13, 124, 25);
		frame.getContentPane().add(btnClearContent);

		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestQuery test = new TestQuery();
				textArea.setText(test.test("view_inventory"));
			}
		});

	}
}
