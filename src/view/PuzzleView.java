package view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PuzzleView extends Application implements Observer{
	
	
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
