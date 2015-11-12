package controller;


import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.ExeQuery;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//H
public class ITControlPanel {

	private JFrame frame;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField passwordTextField;
	private JTextField rePasswordTextField;
	private JLabel lblLevel;
	private int userid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ITControlPanel window = new ITControlPanel(1002);
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
	public ITControlPanel(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		frame = new JFrame("IT Control Panel");
		frame.setBounds(100, 100, 377, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		userid=id;
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(12, 53, 77, 16);
		frame.getContentPane().add(lblFirstName);
		
		firstnameTextField = new JTextField();
		firstnameTextField.setBounds(110, 50, 116, 22);
		frame.getContentPane().add(firstnameTextField);
		firstnameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(12, 82, 77, 16);
		frame.getContentPane().add(lblLastName);
		
		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(110, 79, 116, 22);
		frame.getContentPane().add(lastnameTextField);
		lastnameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(12, 117, 77, 16);
		frame.getContentPane().add(lblPassword);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(110, 114, 116, 22);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblRetype = new JLabel("Re-type:");
		lblRetype.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetype.setBounds(34, 145, 56, 16);
		frame.getContentPane().add(lblRetype);
		
		rePasswordTextField = new JPasswordField();
		rePasswordTextField.setBounds(110, 142, 116, 22);
		frame.getContentPane().add(rePasswordTextField);
		rePasswordTextField.setColumns(10);
		
		lblLevel = new JLabel("Level:");
		lblLevel.setBounds(262, 53, 56, 16);
		frame.getContentPane().add(lblLevel);
		
		JRadioButton rdbtnIt = new JRadioButton("IT");
		rdbtnIt.setBounds(262, 73, 62, 25);
		frame.getContentPane().add(rdbtnIt);
		
		JRadioButton rdbtnCashier = new JRadioButton("Cashier");
		rdbtnCashier.setBounds(262, 98, 127, 25);
		frame.getContentPane().add(rdbtnCashier);
		
		JRadioButton rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBounds(262, 123, 127, 25);
		frame.getContentPane().add(rdbtnManager);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnIt);
		buttonGroup.add(rdbtnCashier);
		buttonGroup.add(rdbtnManager);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(34, 318, 97, 25);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(241, 318, 77, 25);
		frame.getContentPane().add(btnClear);
		
		JLabel lblNewLabel = new JLabel("IT Control");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(136, 13, 97, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID:"+userid);
		lblId.setBounds(262, 14, 56, 16);
		frame.getContentPane().add(lblId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 174, 312, 131);
		frame.getContentPane().add(scrollPane);
		
		JTextArea employeeTextArea = new JTextArea();
		scrollPane.setViewportView(employeeTextArea);
		
		JButton btnView = new JButton("View All");
		btnView.setBounds(143, 318, 83, 25);
		frame.getContentPane().add(btnView);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeTextArea.setText(null);
			}
		});
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				employeeTextArea.setText((String)test.test("viewEmployee", null));
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String level="";
				if(rdbtnIt.isSelected()) level="IT";
				else if(rdbtnCashier.isSelected())level="Cashier";
				else if(rdbtnManager.isSelected())level="Manager";
				String firstname = firstnameTextField.getText();
				String lastname = lastnameTextField.getText();
				String pass = passwordTextField.getText();
				String repass = rePasswordTextField.getText();
				if(!pass.equals(repass)) 					
					JOptionPane.showMessageDialog(frame, "Password doesn't match. Try again.");
				else if(level.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please select a level.");
				}
				else
				{
					ExeQuery test = new ExeQuery();
					//1.firstName,2.lastName,3.password,4.level
					ArrayList<String> requestContainer = new ArrayList<String>(4);
					requestContainer.add(firstname);
					requestContainer.add(lastname);
					requestContainer.add(pass);
					requestContainer.add(level);
					String result =(String) test.test("signUp",requestContainer);
					if(result.equals("success")) employeeTextArea.setText("Employee is added.");
					else if((result.equals("NoPointer")))employeeTextArea.setText("Please enter valid data.");
					else if((result.equals("ViolateRule")))employeeTextArea.setText("Insertion fails.");
					else employeeTextArea.setText("Failed");
				}
			}
		});
	}
}
