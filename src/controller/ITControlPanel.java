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
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JCheckBox;

public class ITControlPanel {

	private TextField firstnameTextField;
	private TextField lastnameTextField;
	private TextField passwordTextField;
	private TextField rePasswordTextField;
	private Label lblLevel;
	private int userid;
        private GridPane itPanelView = new GridPane();
        private Button addUserMenu = new Button("Add New Employee");
        private Button viewEmployeesMenu = new Button("View All Employees");
private Label lblFirstName = new Label("First Name:");
	private Label lblLastName = new Label("Last Name:");
	private Label lblPassword = new Label("Password:");
        private Label lblRetype = new Label("Re-type:");
        private Stage ITStage = new Stage();
        private RadioButton rdbtnIt = new RadioButton("IT");
	private RadioButton rdbtnCashier = new RadioButton("Cashier");
	private RadioButton rdbtnManager = new RadioButton("Manager");
        private Button btnSignUp = new Button("Sign Up");
        private TextArea employeeTextArea = new TextArea();
        Button btnView = new Button("View All");
        private BorderPane itBorderPane = new BorderPane();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ITControlPanel window = new ITControlPanel(1002);
					//window.frame.setVisible(true);
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
		          Platform.runLater(new Runnable() {

                              @Override
                              public void run() {
		userid=id;
		
		addUserMenu.setMinSize(100, 100);
                viewEmployeesMenu.setMinSize(100, 100);
		firstnameTextField = new TextField();
                lastnameTextField = new TextField();
		passwordTextField = new PasswordField();
		rePasswordTextField = new PasswordField();
		lblLevel = new Label("Level:");
                ToggleGroup buttonGroup = new ToggleGroup();
                rdbtnCashier.setToggleGroup(buttonGroup);
                rdbtnIt.setToggleGroup(buttonGroup);
                rdbtnManager.setToggleGroup(buttonGroup);

		//buttonGroup.add(rdbtnIt);
		//buttonGroup.add(rdbtnCashier);
		//buttonGroup.add(rdbtnManager);		
		
		Button btnClear = new Button("Clear");
		
               Button deleteUser = new Button("Delete a User");
              //  deleteUser.setBounds(120, 350, 125, 25);
               // frame.getContentPane().add(deleteUser);

		Label lblNewLabel = new Label("IT Control");
		
		
		Label lblId = new Label("ID:"+userid);
		
		
		ScrollPane scrollPane = new ScrollPane();
		
		scrollPane.setContent(employeeTextArea);
		
		btnClear.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				employeeTextArea.setText(null);
			}
		});
		btnView.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				employeeTextArea.setText((String)test.test("viewEmployee", null));
			}
		});
		btnSignUp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String level="";
				if(rdbtnIt.isSelected()) level="IT";
				else if(rdbtnCashier.isSelected())level="Cashier";
				else if(rdbtnManager.isSelected())level="Manager";
				String firstname = firstnameTextField.getText();
				String lastname = lastnameTextField.getText();
				String pass = passwordTextField.getText();
				String repass = rePasswordTextField.getText();
				if(!pass.equals(repass)) {					
					//JOptionPane.showMessageDialog(frame, "Password doesn't match. Try again.");
                                }
				else if(level.equals(""))
				{
					//JOptionPane.showMessageDialog(frame, "Please select a level.");
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
                deleteUser.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent ae) {
                        ExeQuery test = new ExeQuery();
			String employees =(String)test.test("viewEmployee", null);
                        String[] empList=employees.split("\\n");
                        
                JFrame deleteUsersFrame = new JFrame();
                deleteUsersFrame.setBounds(100, 100, 377, 388);
		deleteUsersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deleteUsersFrame.getContentPane().setLayout(null);
		deleteUsersFrame.setVisible(true);
                        JCheckBox x = new JCheckBox("Delete All Users");
                        x.setBounds(0, 0, 20, 20);
                        deleteUsersFrame.getContentPane().add(x);
                        for(int i=0;i<empList.length;i++){
                            System.out.println(empList[i]);
                        }
                    
                    }
                });
                
                addUserMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                            getAddEmployeeContent();
                            itBorderPane.setCenter(itPanelView);
                            BorderPane.setMargin(itPanelView, new Insets(100, 0, 0, 200));

                        }
		});
                  viewEmployeesMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                            getViewAllContent();
                            itBorderPane.setCenter(itPanelView);
                            BorderPane.setMargin(itPanelView, new Insets(100, 0, 0, 200));


                        }
		});
                itBorderPane.setLeft(getITMenu());
                 itBorderPane.setMaxSize(600, 600);
                getViewAllContent();
                Scene scene = new Scene(itBorderPane, 2000,1500);
                ITStage.setScene(scene);
                ITStage.showAndWait();
                    }
                          });
	}
        public GridPane getITMenu(){
            GridPane menu = new GridPane();
            menu.add(addUserMenu,0,0);
            menu.add(viewEmployeesMenu,0,1);
            return menu;
        }
        public void getAddEmployeeContent(){
            itPanelView.getChildren().clear();
            itPanelView.add(lblFirstName, 0, 0);
            itPanelView.add(firstnameTextField,1,0);
            itPanelView.add(lblLastName, 0, 1);
            itPanelView.add(lastnameTextField, 1, 1);
            itPanelView.add(lblPassword,0,2);
            itPanelView.add(passwordTextField, 1, 2);
            itPanelView.add(lblRetype, 0, 3);
            itPanelView.add(rePasswordTextField, 1, 3);
            itPanelView.add(rdbtnCashier, 0, 4);
            itPanelView.add(rdbtnIt, 0, 5);
            itPanelView.add(rdbtnManager, 0, 6);
            itPanelView.add(btnSignUp, 0, 7);
        }
          public void getViewAllContent(){
            itPanelView.getChildren().clear();
            itPanelView.add(btnView, 0, 0);
            itPanelView.add(employeeTextArea, 0, 1);
        }
        
       public GridPane getContents(){
           return itPanelView;
       }
}
