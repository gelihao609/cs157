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
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;



public class ModifyPanel extends GridPane{
        private GridPane view = new GridPane();
	private TextField nametextField = new TextField();
	private TextField pricetextField = new TextField();
	private TextField idtextField = new TextField();
        private TextField newPricetextField = new TextField();
        private Label lblAddItem = new Label("Add item:");
        private Label lblName = new Label("Name:");
        private Label lblPrice = new Label("Price:");
	private Button btnAdd = new Button("Add");
        private Button AddMenuButton = new Button("Add New Item");
        private Button ModifyMenuButton = new Button("Modify Item Info");
        private TextArea AddResulttextArea = new TextArea();
        Label lblChangeItemPrice = new Label("Modify item Price:");
	Label lblNewLabel = new Label("Item id:");
	Label lblNewPrice = new Label("New Price:");
	Button btnModify = new Button("Modify");
private ScrollPane addResultscrollPane = new ScrollPane();
//private ScrollPane modifyReslscrollPane = new ScrollPane();
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPanel window = new ModifyPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
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
		
		
		addResultscrollPane.setContent(AddResulttextArea);
		
		
		//TextArea modifyResulttextArea = new TextArea();
		//modifyResulttextArea.setText("");
		//modifyReslscrollPane.setContent(modifyResulttextArea);
		
		Button clearAddBtn = new Button("Clear");
		
		
		Button clearModBtn = new Button("Clear");
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
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
		
		clearAddBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				AddResulttextArea.setText(null);
			}
		});
		clearModBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//modifyResulttextArea.setText(null);
			}
		});
		btnModify.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String id=idtextField.getText();
				String price=newPricetextField.getText();
				ArrayList<String> requestContainer = new ArrayList<String>(2);
				requestContainer.add(id);
				requestContainer.add(price);
				String result=(String) test.test("modifyItem", requestContainer);
				if(result.equals("success")) AddResulttextArea.setText("Price is modified to "+price);
				else if((result.equals("NoPointer")))AddResulttextArea.setText("Please enter valid data.");
				else if((result.equals("ViolateRule")))AddResulttextArea.setText("Item ID not found.");
				else AddResulttextArea.setText("Failed");
			}
		});
                AddMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                            AddResulttextArea.setText("");
                            getAddContent();
			}
		});
                ModifyMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                            AddResulttextArea.setText("");
                            getModifyContent();
			}
		});
	}
        
        public GridPane getMenu(){
            GridPane menu = new GridPane();
            menu.add(AddMenuButton, 0, 0);
            menu.add(ModifyMenuButton,1,0);
            return menu;
        }
        
        public void getAddContent(){
            view.getChildren().clear();
            view.add(lblName, 0, 0);
            view.add(nametextField,1,0);
            view.add(lblPrice,2,0);
            view.add(pricetextField,3,0);
            view.add(btnAdd, 0, 1);
            //view.add(addResultscrollPane,0,4);
        }
         public void getModifyContent(){
            view.getChildren().clear();
            view.add(lblNewLabel, 0, 0);
            view.add(idtextField,1,0);
            view.add(lblNewPrice,0,1);
            view.add(newPricetextField,1,1);
            view.add(btnModify,0, 2);
            //view.add(modifyReslscrollPane, 3, 2);
        }
        public GridPane getView(){
            return view;
        }
        public ScrollPane getResult(){
            return addResultscrollPane;
        }
}
