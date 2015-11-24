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
                     
		userid=id;
		Button btnPurchasereturn = new Button("Purchase&Return");
		btnPurchasereturn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				                        Platform.runLater(new Runnable() {
					public void run() {
						try {
							new PurchaseReturnPanel(userid);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
                btnPurchasereturn.setMinSize(300, 300);
		mPanel.add(btnPurchasereturn, 0, 0);
		Button btnView = new Button("View...");
		btnView.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.runLater(new Runnable() {
					public void run() {
						try {
							new ViewPanel();
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
							 new ArchivePanel();
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
				new CheckPanel();
			}
		});
                btnCheckfind.setMinSize(300, 300);
		mPanel.add(btnCheckfind, 0, 2);
		Button btnModify = new Button("Modify...");
		btnModify.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new ModifyPanel();
			}
		});
                btnModify.setMinSize(300, 300);
                mPanel.add(btnModify, 0, 3);
                
                Scene scene = new Scene(mPanel, 3000,1500);
              managerStage.setScene(scene);
                            managerStage.showAndWait();
                     }
                });  
	}
}
