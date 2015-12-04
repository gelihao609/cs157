package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.Date;

import backend.ExeQuery;

public class CheckPanel {

	private JFrame frame;
	private JTextField itmIDtextField;
	private JTextField quantiTextField;
	private JTextField earningTextField;
	private JTextField supplierIdtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckPanel window = new CheckPanel();
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
	public CheckPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Check & Find Panel");
		frame.setBounds(100, 100, 542, 414);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JButton chkCshConbutton = new JButton("Go");
		chkCshConbutton.setBounds(12, 37, 60, 25);
		frame.getContentPane().add(chkCshConbutton);
		
		JLabel lblCheckCashiersTransaction = new JLabel("Check cashier's transaction counts:");
		lblCheckCashiersTransaction.setBounds(12, 13, 210, 16);
		frame.getContentPane().add(lblCheckCashiersTransaction);
		
		JLabel lblCheckItemsHave = new JLabel("Check items have multiple suppliers:");
		lblCheckItemsHave.setBounds(12, 75, 233, 16);
		frame.getContentPane().add(lblCheckItemsHave);
		
		JButton chkItmMltiSplbutton = new JButton("Go");
		chkItmMltiSplbutton.setBounds(12, 104, 60, 25);
		frame.getContentPane().add(chkItmMltiSplbutton);
		
		JScrollPane mainScrollPane = new JScrollPane();
		mainScrollPane.setBounds(234, 13, 278, 235);
		frame.getContentPane().add(mainScrollPane);
		
		JTextArea mainTextArea = new JTextArea();
		mainTextArea.setEditable(false);
		mainScrollPane.setViewportView(mainTextArea);
		
		JLabel lblFindSupplierFor = new JLabel("Find supplier by itemId:");
		lblFindSupplierFor.setBounds(12, 204, 148, 16);
		frame.getContentPane().add(lblFindSupplierFor);
		
		itmIDtextField = new JTextField();
		itmIDtextField.setBounds(12, 223, 75, 22);
		frame.getContentPane().add(itmIDtextField);
		itmIDtextField.setColumns(10);
		
		JButton findSupBtn = new JButton("Go");
		findSupBtn.setBounds(99, 223, 50, 22);
		frame.getContentPane().add(findSupBtn);
		
		JLabel lblFindItemBelow = new JLabel("Find item below quantity of:");
		lblFindItemBelow.setBounds(12, 313, 167, 16);
		frame.getContentPane().add(lblFindItemBelow);
		
		quantiTextField = new JTextField();
		quantiTextField.setBounds(12, 331, 75, 22);
		frame.getContentPane().add(quantiTextField);
		quantiTextField.setColumns(10);
		
		JButton findLowInvenBtn = new JButton("Go");
		findLowInvenBtn.setBounds(99, 331, 50, 22);
		frame.getContentPane().add(findLowInvenBtn);
		
		JLabel lblGetEarning = new JLabel("Start Date:");
		lblGetEarning.setBounds(218, 300, 81, 16);
		frame.getContentPane().add(lblGetEarning);
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(293, 297, 100, 22);
		frame.getContentPane().add(startDateChooser);
		
		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.setBounds(293, 331, 100, 22);
		frame.getContentPane().add(endDateChooser);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(225, 334, 56, 16);
		frame.getContentPane().add(lblEndDate);
		
		earningTextField = new JTextField();
		earningTextField.setEditable(false);
		earningTextField.setBounds(405, 296, 107, 22);
		frame.getContentPane().add(earningTextField);
		earningTextField.setColumns(10);
		
		JButton earningBtn = new JButton("Get Earnings");
		earningBtn.setBounds(405, 328, 107, 25);
		frame.getContentPane().add(earningBtn);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(442, 252, 70, 25);
		frame.getContentPane().add(btnClear);
		
		JLabel lblCheckItemsNot = new JLabel("Check items not been sold yet:");
		lblCheckItemsNot.setBounds(12, 142, 210, 16);
		frame.getContentPane().add(lblCheckItemsNot);
		
		JButton notSoldBtn = new JButton("Go");
		notSoldBtn.setBounds(12, 166, 60, 25);
		frame.getContentPane().add(notSoldBtn);
		
		JLabel lblFind = new JLabel("Find item by supplierId:");
		lblFind.setBounds(12, 258, 148, 16);
		frame.getContentPane().add(lblFind);
		
		supplierIdtextField = new JTextField();
		supplierIdtextField.setBounds(12, 278, 75, 22);
		frame.getContentPane().add(supplierIdtextField);
		supplierIdtextField.setColumns(10);
		
		JButton suppBtn = new JButton("Go");
		suppBtn.setBounds(99, 278, 50, 22);
		frame.getContentPane().add(suppBtn);
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainTextArea.setText("");
			}
		});
		
		chkCshConbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				mainTextArea.setText((String)test.test("check_cashier_trans_count",null));
			}
		});
		
		chkItmMltiSplbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				mainTextArea.setText((String)test.test("check_item_has_multi_supplier",null));
			}
		});
		findSupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String itemid=itmIDtextField.getText();
				String result = (String)test.test("find_supplier",itemid);
				if(result.equals("Nofound")) 
				{
					JOptionPane.showMessageDialog(frame, "No supplier found. Try another item.");
				}
				else
				mainTextArea.setText(result);
			}
		});
		notSoldBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				mainTextArea.setText((String)test.test("check_item_not_sold",null));
			}
		});
		suppBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String supplierid=supplierIdtextField.getText();
				String result = (String)test.test("find_item_by_supplier",supplierid);
				if(result.equals("Nofound")) 
				{
					JOptionPane.showMessageDialog(frame, "No item found by this supplier. Try another.");
				}
				else if(result.equals("ViolateRule")) 
				{
					JOptionPane.showMessageDialog(frame, "Insertion violate rules. Try another.");
				}
				else
				mainTextArea.setText(result);
			}
		});
		findLowInvenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String quantity=quantiTextField.getText();
				String result = (String)test.test("find_item_below_quantity",quantity);
				if(result.equals("Nofound")) 
				{
					JOptionPane.showMessageDialog(frame, "All items have inventory above "+quantity);
				}
				else
				mainTextArea.setText(result);			
				}
		});
		earningBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				Date start =startDateChooser.getDate();
				Date end =endDateChooser.getDate();
				//1. start date 2.end date
				ArrayList<Date> requestContainer = new ArrayList<Date>(2);
				requestContainer.add(start);
				requestContainer.add(end);
				String result = (String)test.test("getEarning",requestContainer);
				if(result.equals("NoPointer"))earningTextField.setText("Please enter correct dates");
				else earningTextField.setText(result);
			}
		});
	}
}
