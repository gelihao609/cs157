package controller;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class ITControlPanel {

	private JFrame frame;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField passwordTextField;
	private JTextField rePasswordTextField;
	private JLabel lblLevel;
	/**
	 * Create the application.
	 */
	public ITControlPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("IT Control Panel");
		frame.setBounds(100, 100, 450, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(12, 13, 77, 16);
		frame.getContentPane().add(lblFirstName);
		
		firstnameTextField = new JTextField();
		firstnameTextField.setBounds(110, 10, 116, 22);
		frame.getContentPane().add(firstnameTextField);
		firstnameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(12, 42, 77, 16);
		frame.getContentPane().add(lblLastName);
		
		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(110, 39, 116, 22);
		frame.getContentPane().add(lastnameTextField);
		lastnameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(12, 77, 77, 16);
		frame.getContentPane().add(lblPassword);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(110, 74, 116, 22);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblRetype = new JLabel("Re-type:");
		lblRetype.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetype.setBounds(34, 105, 56, 16);
		frame.getContentPane().add(lblRetype);
		
		rePasswordTextField = new JPasswordField();
		rePasswordTextField.setBounds(110, 102, 116, 22);
		frame.getContentPane().add(rePasswordTextField);
		rePasswordTextField.setColumns(10);
		
		lblLevel = new JLabel("Level:");
		lblLevel.setBounds(262, 13, 56, 16);
		frame.getContentPane().add(lblLevel);
		
		JRadioButton rdbtnIt = new JRadioButton("IT");
		rdbtnIt.setBounds(262, 33, 127, 25);
		frame.getContentPane().add(rdbtnIt);
		
		JRadioButton rdbtnCashier = new JRadioButton("Cashier");
		rdbtnCashier.setBounds(263, 58, 127, 25);
		frame.getContentPane().add(rdbtnCashier);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Manager");
		rdbtnNewRadioButton.setBounds(263, 83, 127, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnIt);
		buttonGroup.add(rdbtnCashier);
		buttonGroup.add(rdbtnNewRadioButton);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(129, 157, 97, 25);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(237, 157, 97, 25);
		frame.getContentPane().add(btnClear);
	}
}
