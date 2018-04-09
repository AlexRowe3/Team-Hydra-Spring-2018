package view;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	
	public GameView() {
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
	
	// Creates the VBox that stores the Map image and the dynamic interaction buttons
	private VBox generateMapButtonsVBox() {
		// TODO Auto-generated method stub
		
		VBox MapButtonsVBox = new VBox();
		
		ListView<Button> dynamicBtnLView = new ListView<>();
		
		// TODO: Add the buttons that are on by default.
		
		MapButtonsVBox.getChildren().addAll(dynamicBtnLView);
		
		return MapButtonsVBox;
	}

	// Creates the VBox that stores the generic image and the listview that holds the text output.
	private VBox generateImgTextOutVBox() {
		
		VBox ImgTextOutVBox = new VBox();
		
		ImageView imgView = new ImageView();
		//TODO: get a good image, and make this image a little dynamic maybe?
		// Dynamic would take many images.
		
		
		ListView<String> textOutputLView = new ListView<>();
		textOutputLView.setPrefSize(600, 200);
		textOutputLView.autosize();
		
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
		
		//List for inventory with scrollbar
		ListView<String> playerInventoryLView = new ListView<>();
		
		
		PlayerInfoInventoryDirectionsVBox.getChildren().addAll(playerInfoHBox, playerInventoryLView);
		
		return PlayerInfoInventoryDirectionsVBox;
	}

	// TODO: add a constructor that accepts a save, so that it can immediately load to the save on open

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}