package controller;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ManagerControlPanel {
        private Stage managerStage;
	private int userid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					ManagerControlPanel window = new ManagerControlPanel(1003);
                                        
                                } catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public ManagerControlPanel(int id) {
		initialize(id);
             
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
            
               Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
           
              managerStage= new Stage();
              managerStage.setTitle("Manager Control Panel");
              GridPane mPanel = new GridPane();
                                  BorderPane managerPanel = new BorderPane();
                                      managerPanel.setPadding(new Insets(10, 20, 10, 20));

		userid=id;
		Button btnPurchasereturn = new Button("Purchase&Return");
		btnPurchasereturn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				                        Platform.runLater(new Runnable() {
					public void run() {
						try {
                                                    PurchaseReturnPanel x = new PurchaseReturnPanel(userid); 
                                                    GridPane purchasePanel = x.getPurchaseReturnPanel();
                                                    BorderPane.setMargin(purchasePanel, new Insets(200,200,200,200));
                                                    managerPanel.setCenter(purchasePanel);
                                                    managerPanel.setTop(null);
                                                    managerPanel.setRight(null);


                                                } catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
                btnPurchasereturn.setMinSize(300, 300);
                //btnPurchasereturn.setStyle("-fx-background-color: blue");
		mPanel.add(btnPurchasereturn, 0, 0);
		Button btnView = new Button("View...");
		btnView.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.runLater(new Runnable() {
					public void run() {
						try {
					            ViewPanel x = new ViewPanel(); 
                                                     managerPanel.setRight(null);

                                                    GridPane viewMenu = x.getMenu();
                                                    GridPane contents = x.getContents();
                                                    BorderPane.setMargin(contents, new Insets(0, 0, 0, 300));

                                                    BorderPane.setMargin(viewMenu, new Insets(0, 0, 0, 570));
                                                    managerPanel.setTop(viewMenu);
                                                    managerPanel.setCenter(contents);
                                                    
                                                    
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
                btnView.setMinSize(300, 300);
                mPanel.add(btnView, 0, 4);
		
		Button btnArchive = new Button("Archive");
		btnArchive.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Platform.runLater(new Runnable() {
					public void run() {
						try {
							ArchivePanel x= new ArchivePanel();
                                                        managerPanel.setRight(null);
                                                        managerPanel.setTop(null);
                                                        managerPanel.setCenter(x.getArchivePanel());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
                btnArchive.setMinSize(300, 300);
		mPanel.add(btnArchive, 0, 1);
		
		Label lblManagerControl = new Label("Manager Control");
		
		Label lblId = new Label("ID:");
		
		
		Button btnCheckfind = new Button("Check&Find");
		btnCheckfind.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                CheckPanel x = new CheckPanel();
                                managerPanel.setRight(null);
                                GridPane menu = x.getMenu();
                                BorderPane.setMargin(menu, new Insets(0, 0, 0, 400));

                                managerPanel.setTop(menu);
                                GridPane contents = x.getContents();
                                BorderPane.setMargin(contents, new Insets(100, 0, 0, 250));

                                managerPanel.setCenter(contents);
                                /*GridPane checkPanel = new GridPane();
                                GridPane menu = x.findLowInventory();
                                GridPane view = x.getContents();
                                checkPanel.add(menu, 0, 0);
                                checkPanel.add(view,0,1);
                                managerPanel.setCenter(checkPanel);
                                        */

                        }
		});
                btnCheckfind.setMinSize(300, 300);
		mPanel.add(btnCheckfind, 0, 2);
		Button btnModify = new Button("Modify...");
		btnModify.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
                                ModifyPanel x = new ModifyPanel();
                                GridPane menu = x.getMenu();
                                BorderPane.setMargin(menu, new Insets(0, 0, 0, 500));

                                managerPanel.setTop(menu);
                                GridPane view = x.getView();
                                managerPanel.setCenter(view);
                                managerPanel.setRight(x.getResult());
                        }
		});
                btnModify.setMinSize(300, 300);
                mPanel.add(btnModify, 0, 3);
                 managerPanel.setLeft(mPanel);
                Scene scene = new Scene(managerPanel, 2000,1500);
                  //managerPanel.setStyle("-fx-background-color: black");
                 managerStage.setScene(scene);
                            managerStage.showAndWait();
                     }
                });  
	}
}
