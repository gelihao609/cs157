package controller;
import backend.ExeQuery;
import java.util.ArrayList;
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

public class PurchaseReturnPanel extends GridPane{

	private Stage purchaseReturnStage;
	private TextField itemTextField;
	private TextField quantityTextField;
	private int userid;
        //private GridPane purchaseReturnPane ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					PurchaseReturnPanel window = new PurchaseReturnPanel(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PurchaseReturnPanel(int id) {
		initialize(id);
                 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
             purchaseReturnStage= new Stage();
            // purchaseReturnPane = new GridPane();
		userid = id;
		Label lblItem = new Label("Item:");
		add(lblItem, 0, 0);
		Label lblQuantity = new Label("Quantity:");
		add(lblQuantity, 0, 1);
		itemTextField = new TextField();
                add(itemTextField, 1, 0);
                quantityTextField = new TextField();		
		add(quantityTextField, 1, 1);

		Button btnPurchase = new Button("Purchase");
		btnPurchase.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//item: 1.userId 2.itemId 3. itemQuantity
				ArrayList<String> dataContainer = new ArrayList<String>(3);
				dataContainer.add(Integer.toString(userid));
				dataContainer.add(itemTextField.getText());
				dataContainer.add(quantityTextField.getText());
				ExeQuery test = new ExeQuery();
				@SuppressWarnings("unchecked")
				//1. responseCmd 2.item name 3. quantity in stock
				ArrayList<String> result = (ArrayList<String>) test.test("purchase", dataContainer);
				String response = result.get(0);
				if(response.equals("itemnotfound"))
				{
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Purchase Failed");
alert.setHeaderText("Purchase Failed");
alert.setContentText("Item Not Found!");
alert.showAndWait();
				}
				else if(response.equals("insufficient"))
				{
                                    Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Purchase Failed");
alert.setHeaderText("Purchase Failed");
alert.setContentText("Insufficient. Only charge quantity of "+result.get(2) + " for "+result.get(1)+"!");
alert.showAndWait();
                                    
				}
				else if(response.equals("success"))
				{
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Purchase Successful");
alert.setContentText("Purchased "+result.get(1)+" Quantity: "+result.get(2));
alert.showAndWait();
				}
				else
				{
                                    Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Unexpected Error");
alert.setContentText("Unexpected error in purchaseReturnPan.");
alert.showAndWait();
				}
			}
		});
                GridPane.setMargin(btnPurchase, new Insets(10, 30, 0, 0));

                add(btnPurchase, 0, 2);

		Button btnReturn = new Button("Return");
		btnReturn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				//item: 1.userId 2.itemId 3. itemQuantity
				ArrayList<String> dataContainer = new ArrayList<String>(3);
				dataContainer.add(Integer.toString(userid));
				dataContainer.add(itemTextField.getText());
				dataContainer.add(quantityTextField.getText());
				ExeQuery test = new ExeQuery();
				@SuppressWarnings("unchecked")
				//1. responseCmd 2.item name 3. quantity in stock
				ArrayList<String> result = (ArrayList<String>) test.test("return", dataContainer);
				String response = result.get(0);
				if(response.equals("itemnotfound"))
				{
               Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Return Failed");
alert.setContentText("Item Not Bought From This Supermarket.");
alert.showAndWait();
                                }
				else if(response.equals("success"))
				{
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Return Successful");
alert.setContentText("Return Successful: "+result.get(1)+" Quantity: "+result.get(2));
alert.showAndWait();
				}

			}
		});
                                GridPane.setMargin(btnReturn, new Insets(10, 0, 0, 0));

                		add(btnReturn, 1, 2);

             // Scene scene = new Scene(purchaseReturnPane, 3000,1500);
              //purchaseReturnStage.setScene(scene);
                            //purchaseReturnStage.showAndWait();
                            
                      
                            
                            
                  }
            });            
	}
        public GridPane getPurchaseReturnPanel(){
            return this;
        }
        
}
