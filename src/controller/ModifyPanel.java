package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		frame.setBounds(100, 100, 450, 359);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JLabel lblAddItem = new JLabel("Add item:");
		lblAddItem.setBounds(12, 13, 62, 16);
		frame.getContentPane().add(lblAddItem);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 42, 56, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(12, 71, 56, 16);
		frame.getContentPane().add(lblPrice);
		
		nametextField = new JTextField();
		nametextField.setBounds(71, 35, 116, 22);
		frame.getContentPane().add(nametextField);
		nametextField.setColumns(10);
		
		pricetextField = new JTextField();
		pricetextField.setBounds(71, 65, 116, 22);
		frame.getContentPane().add(pricetextField);
		pricetextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		
		btnAdd.setBounds(102, 100, 85, 25);
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
		btnModify.setBounds(318, 100, 85, 25);
		frame.getContentPane().add(btnModify);
		
		JScrollPane addResultscrollPane = new JScrollPane();
		addResultscrollPane.setBounds(12, 138, 175, 134);
		frame.getContentPane().add(addResultscrollPane);
		
		JTextArea AddResulttextArea = new JTextArea();
		addResultscrollPane.setViewportView(AddResulttextArea);
		
		JScrollPane modifyReslscrollPane = new JScrollPane();
		modifyReslscrollPane.setBounds(229, 138, 174, 134);
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
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String name=nametextField.getText();
				String price=pricetextField.getText();
				ArrayList<String> requestContainer = new ArrayList<String>(2);
				requestContainer.add(name);
				requestContainer.add(price);
				String result=(String) test.test("addItem", requestContainer);
				if(result.equals("success")) AddResulttextArea.setText(name+" is added." );
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
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
