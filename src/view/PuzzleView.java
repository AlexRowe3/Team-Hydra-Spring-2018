package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.GenericItem;
import model.Model;
import model.Player;
// This import is needed for the Action listeners as well as the Room connection indexes.
import model.Room;

public class PuzzleView extends Application{
	
	
	private Label hint;
	private TextField tf;
	private Button buttTin;
	private Label result;
	
	
	static final int numberOfButtons = 2;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane pane = new Pane();
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(5,5,5,5));
		
		hint = new Label("test");
		tf = new TextField();
		buttTin = new Button();
		result = new Label("test");
		
		// Create and fill the vbox with buttons
		
		
		vbox.getChildren().addAll(hint, tf, buttTin, result);
		// vbox.getChildren().addAll(generateButtons());
		vbox.setSpacing(10);
		
		pane.getChildren().add(vbox);
		
		Scene scene = new Scene(pane, 400, 100);
		primaryStage.setTitle("Puzzle");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
