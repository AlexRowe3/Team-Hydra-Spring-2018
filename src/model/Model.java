package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
// Used for reading the default game state from files.
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * @author William Bullock
 * @version 0.1
 * Written: April 11th, 2018
 * 
 * This is the over-arching Model class for interaction with the model.
 * It is basically how I imagine some APIs to be like.
 */
public class Model extends Observable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<GenericItem> items = new ArrayList<GenericItem>();
	
	public Model() {
		loadNewGame();
	}
	//TODO: Add a constructor to load a game? Might not need as we can load the object from a .bin save

	private void loadNewGame() {
		
		loadNewItems();
		
		loadNewRooms();
		
		loadNewPlayer();
		
	}

	private void loadNewPlayer() {
		// Might make this changeable in a different view
		String name = "Player";
		// TODO: Check this information against the Requirements document
		int hp = 20;
		int strength = 0;
		int defense = 0;
		GenericItem[] inventory = {};
		int experience = 0;
		String description = "Thats me!";
		GenericItem armor = null;
		GenericItem weapon = null;
		int level = 1;
		Room room = getRoom("SR01");
		
		// I left the ID as 0 for now, not sure if I need to do something else with it just yet.
		player = new Player(0, name, hp, strength, defense, inventory, experience, description, armor, weapon, level, room);
	}

	private Room getRoom(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private void loadNewRooms() {
		// TODO: make this actually read the Room information and generate Room Objects into the Rooms arraylist
		String fileName = ".\\src\\docs\\Rooms.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
				/* debug TODO: remove this line when appropriate */ System.out.println(line);
				
				
				rooms.add(new Room(0, line, 0, line, 0, line, null, null));
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private void loadNewItems() {
		// TODO Auto-generated method stub
		String fileName = ".\\src\\docs\\Items.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
