package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author William Bullock
 * @version 0.1
 * 
 * Written: April 8th, 2018
 * 
 * This class handles the first window that the game will create, initiating the entirety of the game.
 * Since this window will only be made once, it is perfect for the launch() command to start off the 
 * entirety of the JavaFX application.
 */

public class StartupView extends Application{
	
	// We can edit this if we ever need more buttons on the main menu
	static final int numberOfButtons = 3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane pane = new Pane();
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(5,5,5,5));
		
		Label label = new Label("Main Menu:");
		
		// Create and fill the vbox with buttons
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(5,5,5,5));
		hbox.getChildren().addAll(generateButtons(primaryStage));
		hbox.setSpacing(20);
		
		vbox.getChildren().addAll(label, hbox);
		// vbox.getChildren().addAll(generateButtons());
		vbox.setSpacing(10);
		
		pane.getChildren().add(vbox);
		
		Scene scene = new Scene(pane, 400, 100);
		primaryStage.setTitle("Team Hydra Startup Client");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	// Generates all of the buttons for the menu
	private Button[] generateButtons(Stage stage) {
		
		Button[] buttons = new Button[numberOfButtons];
		
		Button NewGame = new Button("New Game");
		Button LoadGame = new Button("Load Game");
		Button QuitGame = new Button("Quit Game");
		
		// Creating all of the action listeners (Controller)
		NewGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GameView gameview = new GameView();
				stage.hide();
			}
			
		});
		
		LoadGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//TODO: open a new window to list the available saves? check the documentation.
			}
			
		});
		
		QuitGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Exiting");
				System.exit(0);
			}
			
		});
		// TODO: Finish the action listeners to the buttons
		
		buttons[0] = NewGame;
		buttons[1] = LoadGame;
		buttons[2] = QuitGame;
		
		return buttons;
	}

}
