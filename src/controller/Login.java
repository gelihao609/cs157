/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ravi
 */
public class Login extends Application {
        TextField employeeID= new TextField();
        TextField password= new PasswordField();

    @Override
    public void start(Stage primaryStage) {
       employeeID.setStyle("-fx-border-color: blue;-fx-border-width: 6px ;");
             password.setStyle("-fx-border-color: blue;-fx-border-width: 6px ;");

      Image login = new Image(getClass().getResourceAsStream("loginButton.png"));
      Button btn = new Button("",new ImageView(login));
      btn.autosize();
                Label userName = new Label("Employee ID:");
                userName.setStyle("-fx-font-weight:bolder;");
                
                userName.setTextFill(Color.WHITE);
                                   Label pw = new Label("Password:");
                                                   pw.setTextFill(Color.WHITE);
                                                   pw.setStyle("-fx-font-weight:bolder;");


        GridPane root = new GridPane();
                root.add(userName, 0, 0);
                root.add(employeeID, 1, 0);
               root.add(pw, 0, 2);
                root.add(password, 1, 2);
                root.add(btn, 1, 3);
                root.setHgap(10);
                root.setVgap(10);
                GridPane.setMargin(userName, new Insets(0, 10, 0, 10));
                GridPane.setMargin(employeeID, new Insets(30, 10, 25, 20));
                GridPane.setMargin(pw, new Insets(0, 10, 0, 10));
                GridPane.setMargin(password, new Insets(5, 10, 25, 20));
                


        Scene scene = new Scene(root, 200, 250);
                root.setStyle("-fx-background-color: black");

                
        primaryStage.setTitle("Login into Account");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
                primaryStage.setMinHeight(500);
       primaryStage.setMaxHeight(500);
       primaryStage.setMaxWidth(700);

        primaryStage.show();
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                int id = Integer.parseInt(employeeID.getText());
				String inputtedPW = password.getText();
                                System.out.println(inputtedPW);
				ExeQuery test = new ExeQuery();
				List<Object> container = new ArrayList<Object>(2);
				container.add(id);
				container.add(inputtedPW);
				@SuppressWarnings("unchecked")
				List<Object> resultContainer =(List<Object>)test.test("login", container);
				//resultContainer 1.level 2.id
				String level = (String) resultContainer.get(0);
				//error message
				if(level.equals("fail"))
					{
                                            					Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Login Failed");
alert.setHeaderText("Login Failed");
alert.setContentText("Incorrect Employee ID or Password Please Try Again.");

alert.showAndWait();
					}
				//manager panel
				else if(level.equals("manager"))
				{
					Platform.runLater(new Runnable() {
						public void run() {
							try {
								new ManagerControlPanel((int)resultContainer.get(1));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});					
				}
				//IT panel
				else if(level.equals("IT"))
				{
					Platform.runLater(new Runnable() {
						public void run() {
							try {
					             new ITControlPanel((int)resultContainer.get(1));
	
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				//Cashier panel
				else if(level.equals("cashier"))
				{
					Platform.runLater(new Runnable() {
						public void run() {
							try {
					new CashierControlPanel((int)resultContainer.get(1));

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});		
				}
				else if(level.equals("error"))
				{
					Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Couldn't retrieve message from DB");
alert.showAndWait();

            }
             
        };
				
        });
                }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
