package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import backend.ExeQuery;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField idField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 241, 181);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(12, 13, 56, 16);
		frame.getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 53, 66, 16);
		frame.getContentPane().add(lblPassword);
		
		idField = new JTextField();
		idField.setBounds(88, 10, 116, 22);
		frame.getContentPane().add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 50, 116, 22);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				String password = passwordField.getText();
				ExeQuery test = new ExeQuery();
				List<Object> container = new ArrayList<Object>(2);
				container.add(id);
				container.add(password);
				@SuppressWarnings("unchecked")
				List<Object> resultContainer =(List<Object>)test.test("login", container);
				//resultContainer 1.level 2.id
				String level = (String) resultContainer.get(0);
				//error message
				if(level.equals("fail"))
					{
					JOptionPane.showMessageDialog(frame, "Login fails. Try again.");
					}
				//manager panel
				else if(level.equals("manager"))
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new ManagerControlPanel((int)resultContainer.get(1));
								frame.setVisible(false);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});					
				}
				//IT panel
				else if(level.equals("IT"))
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new ITControlPanel((int)resultContainer.get(1));
								frame.setVisible(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				//Cashier panel
				else if(level.equals("cashier"))
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new CashierControlPanel((int)resultContainer.get(1));
								frame.setVisible(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});		
				}
				else if(level.equals("error"))
				{
					JOptionPane.showMessageDialog(frame, "couldn't retrieve message from DB.");

				}
				
			}
		});
		btnLogIn.setBounds(67, 98, 97, 25);
		frame.getContentPane().add(btnLogIn);
	}

}
