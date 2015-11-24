package controller;
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



public class CashierControlPanel {
        Stage cashierStage;
	private int userid;

	/**
	 * Create the application.
	 */
	public CashierControlPanel(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
         Platform.runLater(new Runnable() {
                @Override
                public void run() {
                cashierStage=new Stage();
                GridPane cashierPanel = new GridPane();
		userid=id;
		Button btnPurchasereturn = new Button("Purchase/Return");
		btnPurchasereturn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
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
		cashierPanel.add(btnPurchasereturn, 0, 2);
		Label lblCashierControl = new Label("Cashier Control");
		cashierPanel.add(lblCashierControl, 0, 0);
                Label lblId = new Label("ID:"+userid);
                cashierPanel.add(lblId, 0, 1);
                
                
              Scene scene = new Scene(cashierPanel, 500,500);
              cashierStage.setScene(scene);
                            cashierStage.showAndWait();
                   }
            });
	}

}
