package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
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

public class InventoryView extends Application{
	
	// Having fun with variable names
	
	// TODO: Make the variables less vulgar
	
	private ObservableList<GenericItem> fuckYou = FXCollections.observableArrayList();
	private ListView<GenericItem> itemList;
	private HBox whateverTheFuckIWant;
	private VBox dumbRetardBox;
	private Button useItem;
	private Button examineItem;
	private Button equip;
	private Button discardItem;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane pane = new Pane();
		
		dumbRetardBox = new VBox();
		whateverTheFuckIWant = new HBox();
		whateverTheFuckIWant.setPadding(new Insets(5,5,5,5));
		dumbRetardBox.setPadding(new Insets(5,5,5,5));
		
		// Create the buttons that insult you
		
		useItem = new Button("Waste it on Yourself");
		examineItem = new Button("Give it a Good Look");
		equip = new Button("Put it On");
		discardItem = new Button("Toss this Shit");
		
		// Add the fucking list view
		
		itemList = new ListView<GenericItem>(fuckYou);
		
		// Create and fill the vbox with buttons
		
		
		dumbRetardBox.getChildren().addAll(useItem, examineItem, equip, discardItem);
		// vbox.getChildren().addAll(generateButtons());
		dumbRetardBox.setSpacing(10);
		
		whateverTheFuckIWant.getChildren().add(dumbRetardBox);
		
		pane.getChildren().add(whateverTheFuckIWant);
		
		Scene scene = new Scene(pane, 400, 100);
		primaryStage.setTitle("Puzzle");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

}
