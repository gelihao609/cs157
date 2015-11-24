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



public class ViewPanel {

        private Stage viewStage;

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
            Platform.runLater(new Runnable() {

                @Override
                public void run() {           
		viewStage = new Stage();
                viewStage.setTitle("View Panel");
                GridPane vPane = new GridPane();
                Button btnViewInventory = new Button("view Inventory");
		Button btnClearContent = new Button("Clear");		
		
		TextArea textArea = new TextArea();
                ScrollPane sPane = new ScrollPane();
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
				
		Button btnViewTransactions = new Button("view Trans.");
		btnViewTransactions.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			ExeQuery test = new ExeQuery();
			textArea.setText((String)test.test("view_transactions",null));
                        System.out.println((String)test.test("view_transactions",null));
			}
		});
		
		Button btnViewSupplier = new Button("view Supplier");
		btnViewSupplier.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				ExeQuery test = new ExeQuery();
				textArea.setText((String)test.test("view_suppliers",null));
			}
		});
                        vPane.add(btnViewInventory, 0, 0);
                        vPane.add(btnClearContent, 1, 0);
                        vPane.add(btnViewSupplier,2,0);
                        vPane.add(btnViewTransactions, 3, 0);
                        vPane.add(textArea, 2, 1);
                        Scene viewScene = new Scene(vPane,1000,1000);
                        viewStage.setScene(viewScene);
                        viewStage.showAndWait();
                  }
            });
	}
        
        
        
}