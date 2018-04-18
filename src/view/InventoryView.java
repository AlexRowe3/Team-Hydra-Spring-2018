package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GenericItem;

public class InventoryView implements Observer{
	
	// Having fun with variable names
	
	// TODO: Make the variables less vulgar
	
	private ObservableList<GenericItem> oItemList;
	private ListView<GenericItem> itemList;
	private HBox hBox;
	private VBox vBox;
	private Button useItem;
	private Button examineItem;
	private Button equipItem;
	private Button discardItem;

	public InventoryView(ArrayList<GenericItem> items) throws Exception {
		
		Stage primaryStage = new Stage();
		Pane pane = new Pane();
		
		vBox = new VBox();
		hBox = new HBox();
		hBox.setPadding(new Insets(5,5,5,5));
		vBox.setPadding(new Insets(5,5,5,5));
		
		// Create the buttons that insult you
		
		useItem = new Button("Waste it on Yourself");
		examineItem = new Button("Give it a Good Look");
		equipItem = new Button("Put it On");
		discardItem = new Button("Toss this Shit");
		
		// Add the fucking list view
		
		oItemList = FXCollections.observableArrayList(items);
		itemList = new ListView<GenericItem>(oItemList);
		
		// Create and fill the vbox with buttons
		
		
		vBox.getChildren().addAll(useItem, examineItem, equipItem, discardItem);
		// vbox.getChildren().addAll(generateButtons());
		vBox.setSpacing(10);
		
		hBox.getChildren().addAll(itemList, vBox);
		
		pane.getChildren().add(hBox);
		
		Scene scene = new Scene(pane, 400, 100);
		primaryStage.setTitle("Puzzle");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO: auto generated
	}
	

}
