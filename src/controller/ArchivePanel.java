package controller;
import javax.swing.JFrame;
import backend.ExeQuery;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ArchivePanel {

private GridPane archiveViewPanel = new GridPane();
	/**
	 * Create the application.
	 */
	public ArchivePanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DatePicker dateChooser = new DatePicker();
		
		Label lblChooseACutoff = new Label("Choose a cutOff time:");
		
		Button btnArchive = new Button("Archive");
		btnArchive.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				LocalDate cutOffLD = dateChooser.getValue();
                                Instant cutOffInstant = cutOffLD.atStartOfDay(ZoneId.systemDefault()).toInstant();
                                Date cutOff =Date.from(cutOffInstant);
				ExeQuery test = new ExeQuery();
				test.test("archive",cutOff);
			}
		});
                archiveViewPanel.add(lblChooseACutoff,0, 0);
                archiveViewPanel.add(dateChooser,1, 0);
                archiveViewPanel.add(btnArchive, 2,0);
	}
        
        public GridPane getArchivePanel(){
            return archiveViewPanel;
        }
}
