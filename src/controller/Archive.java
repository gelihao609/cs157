package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

import backend.ExeQuery;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Archive {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Archive window = new Archive();
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
	public Archive() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 155);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(159, 13, 100, 22);
		frame.getContentPane().add(dateChooser);
		
		JLabel lblChooseACutoff = new JLabel("Choose a cutOff time:");
		lblChooseACutoff.setBounds(12, 19, 135, 16);
		frame.getContentPane().add(lblChooseACutoff);
		
		JButton btnArchive = new JButton("Archive");
		btnArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date cutOff = dateChooser.getDate();
				ExeQuery test = new ExeQuery();
				test.test("archive",cutOff);
			}
		});
		btnArchive.setBounds(12, 66, 97, 25);
		frame.getContentPane().add(btnArchive);
	}
}
