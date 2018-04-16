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
 * @version 0.1.1
 * Written: April 11th, 2018
 * Last Updated: April 14th, 2018
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
		player = new Player("PL01" /* I'm setting this as the UID of the player*/, name, hp, strength, defense, inventory, experience, description, armor, weapon, level, room);
	}
	
	private void loadNewRooms() {
		// TODO: make this actually read the Room information and generate Room Objects into the Rooms arraylist
		String fileName = ".\\src\\docs\\Rooms.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
				// Check if the line starts with the comment symbol. If it does, just move on, otherwise, read in the line
				if(!line.startsWith("~")) {
					
					String UID = line.substring(5, line.length()-1);
					line = bufferedReader.readLine();
					
					String Name = line.substring(6, line.length()-1);
					line = bufferedReader.readLine();
					
					String Type = line.substring(6, line.length()-1);
					line = bufferedReader.readLine();
					
					String description = line.substring(13, line.length()-1);
					line = bufferedReader.readLine();
					
					//TODO: build an items interpreter method for this
					ArrayList<GenericItem> items = new ArrayList<GenericItem>();
					line = bufferedReader.readLine();
					
					String search = line.substring(8, line.length()-1);
					line = bufferedReader.readLine();
					
					//TODO: build a doors interpreter method for this
					Door[] doors = new Door[8];
					line = bufferedReader.readLine();
					
					Character[] monsters = {};
					
					rooms.add(new Room(UID, Name, Type, description, items, search, monsters, doors));
				}
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
		String fileName = ".\\src\\docs\\Items.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
				if(!line.startsWith("~")) {
					
					String itemName = line.substring(10, line.length()-1);
					line = bufferedReader.readLine();
					
					String shortName = line.substring(11, line.length()-1);
					line = bufferedReader.readLine();
					
					String description = line.substring(13, line.length()-1);
					line = bufferedReader.readLine();
					
					String type = line.substring(6, line.length());
					
					if(type == "Weapon") {
						
						line = bufferedReader.readLine();
						
					} else if (type == "Armor") {
						
						line = bufferedReader.readLine();
						
					} else if (type == "Blueprint") {
						
					} else if (type == "Consumable") {
						
						line = bufferedReader.readLine();
						
					} else if (type == "Crafting Item") {
						
					}
					
				}
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/* HERE STARTS THE PUBLIC METHODS, FOR USE WITH ANYTHING THAT NEEDS TO TELL THE MODEL WHAT TO DO, OR TO GET INFORMATION FROM IT. */
	
	/**
	 * Method: getRoom(String)
	 * @param roomID the rooms UID that you are trying to get
	 * @return returns the actual room that you requested, or null if such a room does not exist.
	 */
	public Room getRoom(String roomID) {
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).getUID().equals(roomID)) {
				return rooms.get(i);
			}
		}
		return null;
	}


}
