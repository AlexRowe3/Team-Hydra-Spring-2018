package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GenericItem;
import model.*;
// Created by Tom
// Edited by William Bullock
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
	
	// used simply for the controller located here
	private Model model;
	
	public InventoryView(ArrayList<GenericItem> items, Model model, String title) throws Exception {
		
		this.model = model;
		
		Stage primaryStage = new Stage();
		Pane pane = new Pane();
		
		vBox = new VBox();
		hBox = new HBox();
		hBox.setPadding(new Insets(5,5,5,5));
		vBox.setPadding(new Insets(5,5,5,5));
		
		// Create the buttons that insult you
		
		useItem = generateUseItemButton();
		examineItem = generateExamineItemButton();
		equipItem = generateEquipItemButton();
		discardItem = generateDropItemButton();
		
		// Add the fucking list view
		
		oItemList = FXCollections.observableArrayList(items);
		itemList = new ListView<GenericItem>(oItemList);
		
		// Create and fill the vbox with buttons
		
		
		vBox.getChildren().addAll(useItem, examineItem, equipItem, discardItem);
		// vbox.getChildren().addAll(generateButtons());
		vBox.setSpacing(10);
		
		hBox.getChildren().addAll(itemList, vBox);
		
		pane.getChildren().add(hBox);
		
		Scene scene = new Scene(pane, 400, 200);
		primaryStage.setTitle(title);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Button generateDropItemButton() {
		Button button = new Button("Drop Item");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO: fill handler
			}
			
		});
		
		return button;
	}

	private Button generateEquipItemButton() {
		Button button = new Button("Equip Item");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO: fill handler
			}
			
		});
		
		return button;
	}

	private Button generateExamineItemButton() {
		Button button = new Button("Examine Item");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				model.examinePlayerItem(itemList.getSelectionModel().getSelectedIndex());
			}
			
		});
		
		return button;
	}

	private Button generateUseItemButton() {
		Button button = new Button("Use Item");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO: fill handler
			}
			
		});
		
		return button;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Player) {
			if(((Player) arg).getInventoryChanged()) {
				itemList.getItems().setAll(((Player) arg).getHeldItems());
			}
		}
	}
	

}
