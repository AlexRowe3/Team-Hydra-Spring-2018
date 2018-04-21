package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Observer;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GenericItem;
import model.Model;

/**
 * 
 * @author Thomas Biedenbach
 * @version 0.1
 * 
 * Written: April 21st, 2018
 * 
 * This class handles the window that will list all of the current save files, allowing the user to continue a saved game.
 * 
 */

public class LoadView implements Observer{
	
	private ObservableList<Model> oSaveList;
	private ListView<Model> saveList;
	private VBox vBox;
	private HBox hBox;
	private Button loadSaveFile;
	private Button cancel;
	private Label label;
	private Stage stage;
	
	private Model model;
	
	public LoadView() throws Exception{
		
		stage = new Stage();
		Pane pane = new Pane();
		ArrayList<Model> saves = new ArrayList<Model>();
		
		populate(saves);
		
		loadSaveFile = new Button("Load Game");
		cancel = new Button("Cancel");
		
		hBox = new HBox();
		hBox.setPadding(new Insets(5,5,5,5));
		hBox.getChildren().add(loadSaveFile);
		hBox.getChildren().add(cancel);
		
		vBox = new VBox();
		vBox.setPadding(new Insets(5,5,5,5));
		label = new Label("Select a save file: ");
		
		oSaveList = FXCollections.observableArrayList(saves);
		saveList = new ListView<Model>(oSaveList);
		
		vBox.getChildren().add(label);
		vBox.getChildren().add(saveList);
		vBox.getChildren().add(hBox);
	
		
		pane.getChildren().add(vBox);
		
		Scene scene = new Scene(pane, 400, 500);
		
		saveList.setPrefHeight(scene.getHeight()-60);
		
		stage.setTitle("Load Game");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
	
	// Method to populate the list based on the save file location
	public static void populate(ArrayList<Model> saves) {
		
		Path dir = Paths.get("..\\src\\saves\\");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.bin")){
			for(Path file : stream) {
				String file2 = file.toString();
				FileInputStream file3 = new FileInputStream(file2);
				ObjectInputStream ois = new ObjectInputStream(file3);
				Model mod;
				try {
					mod = (Model) ois.readObject();
					saves.add(mod);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				ois.close();
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(java.util.Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
