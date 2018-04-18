package view;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
/**
 * @author William Bullock
 * @version 0.1
 * 
 * Written: April 8th, 2018
 * 
 * This class will be handle the main display of the game, including the controller
 * so that it can initiate changes in the model.
 */
public class GameView implements Observer {
	
	// TODO: Bring the class-wide edited values out of their methods so that we can use them intra-method.
	private ObservableList<String> textOutList = FXCollections.observableArrayList("Welcome to the game!", "Test Message 2, Default Entry. Meant for testing the look of longer messages. Please remove after confirming the appearance of this message in the view.");
	private ListView<String> textOutputLView = new ListView<>(textOutList);
	//List for inventory with scrollbar
	private ListView<GenericItem> playerInventoryLView = new ListView<>();
	private ListView<Button> dynamicBtnLView = new ListView<>();
	
	//This is purely for the actionlisteners. DO NOT USE IT OUTSIDE OF AN ACTION LISTENER.
	private Model model;
	
	public GameView(Model model) {
		this.model = model;
		
		Stage stage = new Stage();
		
		Pane pane = new Pane();
		
		HBox mainHBox = new HBox();
		
		// fill the mainHBox with all of the VBoxes in order of how they should display.
		mainHBox.getChildren().addAll(generateMapButtonsVBox(), generateImgTextOutVBox(), generatePlayerInfoInventoryDirectionsVBox());
		
		// add the mainHBox to the pane
		pane.getChildren().add(mainHBox);
		
		// add the pane to the display and set miscellaneous settings.
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		// TODO: fix the title
		stage.setTitle("Game Title Goes Here");
		//I want the game to be non-resize-able and maximized.  I can change it, but it will change the appearance too
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.show();
	}
	// TODO: add a constructor that accepts a save, so that it can immediately load to the save on open
	
	// Creates the VBox that stores the Map image and the dynamic interaction buttons
	private VBox generateMapButtonsVBox() {
		VBox MapButtonsVBox = new VBox();
		
		// TODO: Add the map
		
		Button saveBtn = generateSaveButton();
		
		Button examineRoomBtn = generateExamineRoomButton();
		Button searchRoomBtn = generateSearchRoomButton();
		Button checkInventoryButton = generateCheckInventoryButton();
		
		
		dynamicBtnLView.getItems().addAll(checkInventoryButton, examineRoomBtn, searchRoomBtn, saveBtn);
		// TODO: Add the map node to the VBox
		MapButtonsVBox.getChildren().addAll(dynamicBtnLView);
		
		return MapButtonsVBox;
	}

	private Button generateCheckInventoryButton() {
		Button button = new Button("Open Inventory");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					InventoryView iv = new InventoryView(model.getPlayerItems(), model, "Player Inventory");
					model.addObserver(iv);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		return button;
	}

	private Button generateSearchRoomButton() {
		Button button = new Button("Search Room");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					InventoryView iv = new InventoryView(model.getRoom().getRoomItems(), model, "Room Inventory");
					model.addObserver(iv);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		return button;
	}

	private Button generateExamineRoomButton() {
		Button button = new Button("Examine Room");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				model.falseMovePlayer();
			}
			
		});
		
		return button;
	}
	// Creates the VBox that stores the generic image and the listview that holds the text output.
	private VBox generateImgTextOutVBox() {
		
		VBox ImgTextOutVBox = new VBox();
		
		ImageView imgView = new ImageView();
		//TODO: get a good image, and make this image a little dynamic maybe?
		// Dynamic would take many images.
		
		//TODO: Likely need to take this out of the method and fill it elsewhere, possibly a different method.
		textOutputLView.setPrefSize(600, 200);
		textOutputLView.autosize();
		textOutputLView.setEditable(true);
		
		/** DO NOT MESS WITH THIS IT IS REQUIRED FOR WORD WRAP IN THE TEXT OUT LISTVIEW */
		textOutputLView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> list) {
				final ListCell cell = new ListCell() {
					private Text text;
					
					@Override
					public void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						if(!isEmpty()) {
							text = new Text(item.toString());
							text.setWrappingWidth(textOutputLView.getPrefWidth()-10);
							setGraphic(text);
						}
					}
				};
				
				return cell;
			}
			
		});
		
		ImgTextOutVBox.getChildren().addAll(imgView, textOutputLView);
		
		return ImgTextOutVBox;
	}

	// Creates the VBox to store the Player's Information, Inventory, and a place for the direction commands.
	private VBox generatePlayerInfoInventoryDirectionsVBox() {
		
		// Create the left-most VBox to store the Player Information,
		// Player Inventory, and Dynamic Directions button box
		VBox PlayerInfoInventoryDirectionsVBox = new VBox();
		
		//Player Info Section:
		HBox playerInfoHBox = new HBox();
		playerInfoHBox.setPadding(new Insets(5,5,5,5));
		
		VBox staticLabelsVBox = new VBox();
		staticLabelsVBox.setPadding(new Insets(5,5,5,5));
		staticLabelsVBox.setSpacing(5);
		
		Label playerInfoLbl = new Label("Player Information: ");
		Label hpLbl = new Label("HP: ");
		Label lvlLbl = new Label("Level: ");
		Label xpLbl = new Label("Experience: ");
		Label equipmentLbl = new Label("Weapon: ");
		
		staticLabelsVBox.getChildren().addAll(playerInfoLbl, hpLbl, lvlLbl, xpLbl, equipmentLbl);
		
		VBox dynamicLabelsVBox = new VBox();
		dynamicLabelsVBox.setPadding(new Insets(5,5,5,5));
		
		//TODO: add the base labels
		
		playerInfoHBox.getChildren().addAll(staticLabelsVBox, dynamicLabelsVBox);
		// Player Info Box done
		
		//Pane with 8 buttons for direction commands
		Pane directionsPane = new Pane();
		
		VBox directionsVBox = new VBox();
		
		HBox topDirectionsHBox = new HBox();
		
		topDirectionsHBox.getChildren().addAll(generateDirectionButton(Room.NORTHWEST, "NW"), generateDirectionButton(Room.NORTH, "N"), generateDirectionButton(Room.NORTHEAST, "NE"));
		
		HBox midDirectionsHBox = new HBox();
		
		Label directionsLbl = new Label("Directions");
		
		midDirectionsHBox.getChildren().addAll(generateDirectionButton(Room.WEST, "W"), directionsLbl, generateDirectionButton(Room.EAST, "E"));
		
		HBox botDirectionsHBox = new HBox();
		
		botDirectionsHBox.getChildren().addAll(generateDirectionButton(Room.SOUTHWEST, "SW"), generateDirectionButton(Room.SOUTH, "S"), generateDirectionButton(Room.SOUTHEAST, "SE"));
		
		directionsVBox.getChildren().addAll(topDirectionsHBox, midDirectionsHBox, botDirectionsHBox);
		
		directionsPane.getChildren().add(directionsVBox);
		
		PlayerInfoInventoryDirectionsVBox.getChildren().addAll(playerInfoHBox, playerInventoryLView, directionsPane);
		
		return PlayerInfoInventoryDirectionsVBox;
	}

	private Button generateDirectionButton(int direction, String directionLabel) {
		Button button = new Button(directionLabel);
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if(model.checkDirection(direction)) {
					
					model.movePlayer(direction);
					
				} else {
					
					textOutputLView.getItems().add("You can't go that way!");
					
				}
			}
			
		});
		
		return button;
	}
	
	private Button generateSaveButton() {
		Button button = new Button("Save");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				ObjectOutputStream oos;
				
				LocalTime.now();
				File saveFile = new File(".\\src\\saves\\" + (new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + LocalTime.now().getHour() + LocalTime.now().getMinute()+".bin"));
				
				
				try {
					if(saveFile.exists()) {
						oos = new ObjectOutputStream(new FileOutputStream(saveFile));
						oos.writeObject(model);
						oos.close();
					}
				
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		return button;
	}

	@Override
	public void update(Observable o, Object a) {
		
		if (a instanceof Player) {
			
			if (model.checkRoomChanged()) {
				
				textOutputLView.getItems().add(((Player) a).getCurrentRoom().getDescription());
				ArrayList<GenericItem> items = new ArrayList<GenericItem>();
				items = ((Player) a).getCurrentRoom().getRoomItems();
				
				for(int i = 0; i < items.size(); i++) {
					textOutputLView.getItems().add(items.get(i).getName());
				}
			}
		} else if (a instanceof GenericItem) {
			textOutputLView.getItems().add(((GenericItem) a).getDescription());
		}
	}

}
