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

public class CheckPanel {

	private TextField itmIDtextField;
	private TextField quantiTextField;
	private TextField earningTextField;
	private TextField supplierIdtextField;
 private DatePicker fromDate = new DatePicker();
                     private   DatePicker toDate = new DatePicker();

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
           
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
            Stage checkAndFindStage = new Stage();
            GridPane gPane = new GridPane();
           
           
           
		Button chkCshConbutton = new Button("Go");
		
		Label lblCheckCashiersTransaction = new Label("Check cashier's transaction counts:");
		
		Label lblCheckItemsHave = new Label("Check items have multiple suppliers:");
		
		Button chkItmMltiSplbutton = new Button("Go");
		
		ScrollPane mainScrollPane = new ScrollPane();
		
		TextArea mainTextArea = new TextArea();
		mainTextArea.setEditable(false);
		mainScrollPane.setContent(mainTextArea);
		
		Label lblFindSupplierFor = new Label("Find supplier by itemId:");
		
		itmIDtextField = new TextField();
		
		
		Button findSupBtn = new Button("Go");		
		Label lblFindItemBelow = new Label("Find item below quantity of:");
		
		quantiTextField = new TextField();
		
		Button findLowInvenBtn = new Button("Go");
		
		Label lblGetEarning = new Label("Start Date:");
		
		
		
		
		
		Label lblEndDate = new Label("End Date:");
		
		earningTextField = new TextField();
		earningTextField.setEditable(false);
		
		Button earningBtn = new Button("Get Earnings");
		
		Button btnClear = new Button("Clear");
		
		Label lblCheckItemsNot = new Label("Check items not been sold yet:");
		
		Button notSoldBtn = new Button("Go");
		
		Label lblFind = new Label("Find item by supplierId:");
		
		supplierIdtextField = new TextField();
		
		Button suppBtn = new Button("Go");
		
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
         
            gPane.add(fromDate, 0, 0);
            gPane.add(toDate,0,1);
            gPane.add(earningTextField,1,0);
            gPane.add(earningBtn, 1, 1);
             Scene candf = new Scene(gPane,500,500);
            checkAndFindStage.setScene(candf);
            checkAndFindStage.showAndWait();
                }
            });
		               
	}
}
