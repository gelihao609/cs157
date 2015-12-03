package controller;


import backend.ExeQuery;
import controller.CashierControlPanel;
import controller.ITControlPanel;
import controller.ManagerControlPanel;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;



public class ViewPanel extends GridPane {

        private Stage viewStage;
       private Button btnViewInventory;
       private Button btnClearContent;		
       private Button btnViewTransactions;
       private Button btnViewSupplier;
       private TextArea textArea;
       private ScrollPane sPane;




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

                  
		viewStage = new Stage();
                viewStage.setTitle("View Panel");
                 btnViewInventory =new Button("View Inventory");
		 btnClearContent = new Button("Clear");		
		
	        textArea = new TextArea();
                sPane = new ScrollPane();
                sPane.setContent(textArea);
		btnClearContent.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.setText("");
			}
		});

		btnViewInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				textArea.setText((String)test.test("view_inventory",null));
			}
		});
				
		 btnViewTransactions = new Button("View Trans.");
		btnViewTransactions.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			ExeQuery test = new ExeQuery();
			textArea.setText((String)test.test("view_transactions",null));
                        System.out.println((String)test.test("view_transactions",null));
			}
		});
		
		 btnViewSupplier = new Button("View Suppliers");
		btnViewSupplier.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				textArea.setText((String)test.test("view_suppliers",null));
			}
		});
                        add(btnViewInventory, 0, 0);
                        add(btnClearContent, 1, 0);
                        add(btnViewSupplier,2,0);
                        add(btnViewTransactions, 3, 0);
                        add(textArea, 2, 1);
                  }
                
            
           
	
             public GridPane getViewPanel(){
            return this;
        }
            public GridPane getMenu(){
                
                GridPane menu = new GridPane();
                btnClearContent.setMinWidth(200);
                btnViewInventory.setMinWidth(200);
                btnViewSupplier.setMinWidth(200);
                btnViewTransactions.setMinWidth(200);

                menu.add(btnViewInventory,0,0);
                menu.add(btnClearContent,1,0);
                menu.add(btnViewSupplier,2,0);
                menu.add(btnViewTransactions, 3, 0);
                return menu;
            }
            public GridPane getContents(){
                GridPane contents = new GridPane();
                contents.add(sPane, 0, 0);
                return contents;
            }
            
        
        
}