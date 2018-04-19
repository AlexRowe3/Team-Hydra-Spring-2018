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
	private Button pickupDropItem;
	private Stage stage;
	private boolean type;
	
	public static final boolean PLAYER = true;
	public static final boolean ROOM = false;
	
	// used simply for the controller located here
	private Model model;
	
	public InventoryView(ArrayList<GenericItem> items, Model model, String title, boolean type) throws Exception {
		
		this.model = model;
		this.type = type;
		
		stage = new Stage();
		Pane pane = new Pane();
		
		vBox = new VBox();
		hBox = new HBox();
		hBox.setPadding(new Insets(5,5,5,5));
		vBox.setPadding(new Insets(5,5,5,5));
		
		oItemList = FXCollections.observableArrayList(items);
		
		if(type) {
			setupPlayer();
		} else {
			setupRoom();
		}
		
		pane.getChildren().add(hBox);
		
		Scene scene = new Scene(pane, 400, 200);
		
		itemList.setPrefHeight(scene.getHeight()-20);
		
		stage.setTitle(title);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	private void setupRoom() {
		useItem = generateUseItemButton();
		examineItem = generateExamineItemButton();
		pickupDropItem = generatePickUpItemButton();
		
		itemList = new ListView<GenericItem>(oItemList);
		
		vBox.getChildren().addAll(useItem, examineItem, pickupDropItem);
		vBox.setSpacing(10);
		
		hBox.getChildren().addAll(itemList, vBox);
	}

	private Button generatePickUpItemButton() {
		Button button = new Button("Pick Up Item");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int index = itemList.getSelectionModel().getSelectedIndex();
				
				if ((index > -1) && oItemList.size()>0 && index < oItemList.size()) {
					model.transferItem(index, model.ROOM);
				}
			}
			
		});
		
		return button;
	}

	private void setupPlayer() {
		useItem = generateUseItemButton();
		examineItem = generateExamineItemButton();
		equipItem = generateEquipItemButton();
		pickupDropItem = generateDropItemButton();
		
		itemList = new ListView<GenericItem>(oItemList);
		
		vBox.getChildren().addAll(useItem, examineItem, equipItem, pickupDropItem);
		vBox.setSpacing(10);
		
		hBox.getChildren().addAll(itemList, vBox);
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
				int index = itemList.getSelectionModel().getSelectedIndex();
				
				if ((index > -1) && oItemList.size()>0 && index < oItemList.size()) {
					
					if(type == ROOM) {
						
						model.examineRoomItem(index);
						
					} else {
						
						model.examinePlayerItem(index);
						
					}
				}
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
		
		if (arg instanceof Player) {
			
			if (((Player) arg).getInventoryChanged() && (type == PLAYER)) {
				
				itemList.getItems().setAll(((Player) arg).getHeldItems());
				
			} else if(((Player)arg).checkRoomChanged() && (type == ROOM)) {
				
				stage.close();
			}
			
		} else if (arg instanceof Room && type == ROOM) {
			
			itemList.getItems().setAll(((Room) arg).getRoomItems());
			
		}
	}
	

}
