package controller;


import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Date;
import backend.ExeQuery;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckPanel extends GridPane{

	private TextField itmIDtextField;
	private TextField quantiTextField;
	private TextField earningTextField;
	private TextField supplierIdtextField;
        private Button earningBtn = new Button("Get Earnings");
        Label lblStart = new Label("Start Date:");
	Label lblEndDate = new Label("End Date:");
        private DatePicker fromDate = new DatePicker();
        private DatePicker toDate = new DatePicker();
        Button chkItmMltiSplbutton;		
        Button chkCshConbutton;
        private ScrollPane mainScrollPane = new ScrollPane();		
	private TextArea mainTextArea = new TextArea();
	private Button findSupBtn = new Button("Find Supplier");
        private Button findLowInvenBtn = new Button("Find Low Inventory");
        Button notSoldBtn = new Button("Items not Sold");
        Button btnClear = new Button("Clear");		
        Button suppBtn = new Button("Find Item By Supplier");
        private Label lblFindItemBelow = new Label("Find item below quantity of:");		
        private Label lblFind = new Label("Find item by supplierId:");
        private Label lblFindSupplierFor = new Label("Find supplier by itemId:");
        private Button getItemBySupplierID = new Button("Find Item By Supplier");
        private Button getEarningMenuButton = new Button("Get Earnings Info");
        private Button getLowOnInvenButton = new Button("Low Inventory");
        private Button getItemsNotSold = new Button("Items Not Sold");
        private Button getSupplierByItemID = new Button("Get Supplier By Item");
        private Button getTransCount = new Button("Get Transaction Count");
        private Button getMultiSuppliers = new Button("Multiple Suppliers");

        private GridPane contentView ;

      
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckPanel window = new CheckPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
            */
            
            
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
           

                Stage checkAndFindStage = new Stage();
            GridPane gPane = new GridPane();
           contentView= new GridPane();
           
           
		 chkCshConbutton = new Button("Check Cashier Trans. Count ");
		Label lblCheckCashiersTransaction = new Label("Check cashier's transaction counts:");		
		Label lblCheckItemsHave = new Label("Check items have multiple suppliers:");		
		chkItmMltiSplbutton = new Button("Check Multiple Suppliers");		
		
		mainTextArea.setEditable(false);
		mainScrollPane.setContent(mainTextArea);		
		itmIDtextField = new TextField();		
		quantiTextField = new TextField();		
		earningTextField = new TextField();
		earningTextField.setEditable(false);		
		earningBtn = new Button("Get Earnings");		
		Label lblCheckItemsNot = new Label("Check items not been sold yet:");		
		
		
		supplierIdtextField = new TextField();
		
		
		btnClear.setOnAction(new EventHandler<javafx.event.ActionEvent>() {

                @Override
                public void handle(javafx.event.ActionEvent event) {
                    mainTextArea.setText("");
                }
            });
		
		chkCshConbutton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ExeQuery test = new ExeQuery();
				mainTextArea.setText((String)test.test("check_cashier_trans_count",null));
			}
		});
		
		chkItmMltiSplbutton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				mainTextArea.setText((String)test.test("check_item_has_multi_supplier",null));
			}
		});
		findSupBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String itemid=itmIDtextField.getText();
				String result = (String)test.test("find_supplier",itemid);
				if(result.equals("Nofound")) 
				{
                                    			
                                                            Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Failed");
alert.setHeaderText("Insertion Failed");
alert.setContentText("No supplier found. Try another item.");
alert.showAndWait();
				}
				else
				mainTextArea.setText(result);
			}
		});
		notSoldBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				mainTextArea.setText((String)test.test("check_item_not_sold",null));
			}
		});
		suppBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String supplierid=supplierIdtextField.getText();
				String result = (String)test.test("find_item_by_supplier",supplierid);
				if(result.equals("Nofound")) 
				{
                                                            Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Failed");
alert.setHeaderText("Insertion Failed");
alert.setContentText("No item found by this supplier. Try another.");
alert.showAndWait();
				}
				else if(result.equals("ViolateRule")) 
				{
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Failed");
alert.setHeaderText("Insertion Failed");
alert.setContentText("Insertion violate rules. Try another.");
alert.showAndWait();
				}
				else
				mainTextArea.setText(result);
			}
		});
		findLowInvenBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
				String quantity=quantiTextField.getText();
				String result = (String)test.test("find_item_below_quantity",quantity);
				if(result.equals("Nofound")) 
				{
                                                                        Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Failed");
alert.setHeaderText("Insertion Failed");
alert.setContentText("All items have inventory above "+quantity);
alert.showAndWait();
				}
				else
				mainTextArea.setText(result);			
				}
		});
		earningBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ExeQuery test = new ExeQuery();
                                LocalDate fromld = fromDate.getValue();
                                Instant fromInstant = fromld.atStartOfDay(ZoneId.systemDefault()).toInstant();
                                LocalDate told = toDate.getValue();
                                Instant toInstant = told.atStartOfDay(ZoneId.systemDefault()).toInstant();
				Date start = Date.from(fromInstant);
                                				Date end = Date.from(toInstant);
                                                                System.out.print(start.toString()+" " +end.toString());

                                //1. start date 2.end date
				ArrayList<Date> requestContainer = new ArrayList<Date>(2);
				requestContainer.add(start);
				requestContainer.add(end);
				String result = (String)test.test("getEarning",requestContainer);
				if(result.equals("NoPointer"))earningTextField.setText("Please enter correct dates");
				else earningTextField.setText(result);
			}
		});
                getEarningMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                getEarnings();
                        }
		});
                 getLowOnInvenButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                findLowInventory();

                        }
		});
                 getItemsNotSold.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                findItemsNotSold();

                        }
		});
                     getItemBySupplierID.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                findItemBySupplier();

                        }
		});
                     getSupplierByItemID.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                findSupplierByItemID();

                        }
		});
                     getTransCount.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                getTransactionsCount();

                        }
		});
                 getMultiSuppliers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                findMultipleSuppliers();

                        }
		});
            //Scene candf = new Scene(gPane,500,500);
            //checkAndFindStage.setScene(candf);
            //checkAndFindStage.showAndWait();
                
		               
	}
        public void getEarnings(){
            //GridPane getEarnings = new GridPane();
            contentView.getChildren().clear();
            contentView.add(lblStart,0,0);
            contentView.add(lblEndDate,1,0);
            contentView.add(fromDate, 0, 1);
            contentView.add(toDate,1,1);
            contentView.add(earningTextField,0,2);
            contentView.add(earningBtn, 1, 2);
            
        }
        public void findLowInventory(){
            contentView.getChildren().clear();
            mainTextArea.setText("");
            contentView.add(lblFindItemBelow, 0, 0);
            contentView.add(quantiTextField, 1, 0);
            contentView.add(findLowInvenBtn, 0, 2);
            contentView.add(mainScrollPane, 0, 3);
            contentView.add(btnClear,0,4);
            
        }
        public void findItemsNotSold(){
            contentView.getChildren().clear();
             mainTextArea.setText("");

            contentView.add(notSoldBtn, 0, 0);
            contentView.add(mainScrollPane, 1, 0);
            contentView.add(btnClear,0,1);

            
        }
        public void findItemBySupplier(){
            contentView.getChildren().clear();
                        mainTextArea.setText("");

            contentView.add(lblFind, 0, 0);
            contentView.add(supplierIdtextField, 1, 0);
            contentView.add(suppBtn,0,1);
            contentView.add(mainScrollPane, 0, 2);
            contentView.add(btnClear,0,3);

            
        }
         public void findSupplierByItemID(){
            contentView.getChildren().clear();
            mainTextArea.setText("");

            contentView.add(lblFindSupplierFor, 0, 0);
            contentView.add(itmIDtextField, 1, 0);
            contentView.add(findSupBtn,0,1);
            contentView.add(mainScrollPane, 0, 2);
            contentView.add(btnClear,0,3);

            
        }
          public void getTransactionsCount(){
            contentView.getChildren().clear();
            mainTextArea.setText("");
            contentView.add(chkCshConbutton, 0, 0);
            contentView.add(mainScrollPane, 0, 2);
            contentView.add(btnClear,0,3);  
        }
           public void findMultipleSuppliers(){
            contentView.getChildren().clear();
            mainTextArea.setText("");
            contentView.add(chkItmMltiSplbutton, 0, 0);
            contentView.add(mainScrollPane, 0, 1);
            contentView.add(btnClear,0,2);  
        }
        
        public GridPane getContents(){
           return contentView;
        }
         public GridPane getMenu(){
            GridPane menu  = new GridPane();
            menu.add(getEarningMenuButton, 0, 0);
            menu.add(getLowOnInvenButton, 1, 0);
            menu.add(getItemsNotSold,2,0);
            menu.add(getItemBySupplierID,3,0);
            menu.add(getSupplierByItemID,4,0);
            menu.add(getTransCount,5,0);
            menu.add(getMultiSuppliers,6,0);
            return menu;
        }
         public void setContents(){
             
         }
}
