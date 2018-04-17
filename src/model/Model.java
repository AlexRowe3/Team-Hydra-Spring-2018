package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import com.sun.corba.se.spi.orb.StringPair;

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
	// The player's information
	private Player player;
	// The list of all the objects in the game. At least one copy of each
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<GenericItem> items = new ArrayList<GenericItem>();
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	
	public Model() {
		
	}

	public void loadNewGame() {
		
		loadNewItems();
		
		loadNewMonsters();
		
		loadNewPuzzles();
		
		loadNewRooms();
		
		loadNewDoors();
		
		loadNewPlayer();
		
		setChanged();
		notifyObservers(player);
		
	}

	private void loadNewMonsters() {
		// TODO: Finish the file reading for the Monsters
		String fileName = ".\\src\\docs\\Monsters.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadNewPuzzles() {
		// TODO: Finish the file reading for the Monsters
		String fileName = ".\\src\\docs\\Puzzle.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadNewDoors() {
		String fileName = ".\\src\\docs\\Doors.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
				if(!line.startsWith("~")) {
					
					String UID = line.substring(5, line.length()-1);
					line = bufferedReader.readLine();
					
					String room1 = line.substring(7, line.length()-1);
					line = bufferedReader.readLine();
					
					String room2 = line.substring(7, line.length()-1);
					line = bufferedReader.readLine();
					
					boolean locked;
					line = line.substring(8, line.length()-1);
					if(line.equals("False")) {
						
						locked = false;
						addDoor(UID, room1, room2, locked, "");
						line = bufferedReader.readLine();
					} else {
						
						locked = true;
						
						addDoor(UID, room1, room2, locked, "");
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

	private void addDoor(String UID, String room1, String room2, boolean locked, String keyID) {
		
		String[] target = new String[2];
		Room targetRoom1;
		String targetDirection1;
		Room targetRoom2;
		String targetDirection2;
		
		target = room1.split(",");
		targetRoom1 = getRoom(target[0]);
		targetDirection1 = target[1];
		
		target = room2.split(",");
		targetRoom2 = getRoom(target[0]);
		targetDirection2 = target[1];
		
		Door door;
		
		if(locked) {
			door = new Door(targetRoom1, targetRoom2, locked, retrieveItem(keyID));
			
			
		} else {
			
			
			
		}
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
		Armor armor = null;
		Weapon weapon = null;
		int level = 1;
		Room room = getRoom("SR01");
		
		// I left the ID as 0 for now, not sure if I need to do something else with it just yet.
		player = new Player("PL01" /* I'm setting this as the UID of the player*/, name, hp, strength, defense, inventory, experience, description, armor, weapon, level, room);
	}
	
	private void loadNewRooms() {
		String fileName = ".\\src\\docs\\Rooms.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
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
					
					ArrayList<GenericItem> roomItems = new ArrayList<GenericItem>();
					roomItems.addAll(Arrays.asList(retrieveItems(line.substring(7, line.length()-1).split(","))));
					line = bufferedReader.readLine();
					
					String search = line.substring(8, line.length()-1);
					line = bufferedReader.readLine();
					
					ArrayList<Monster> monsters = new ArrayList<Monster>();
					monsters.addAll(Arrays.asList(retrieveMonsters(line.substring(0).split(","))));
					
					rooms.add(new Room(UID, Name, Type, description, roomItems, search, monsters));
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

	private Monster[] retrieveMonsters(String[] UIDList) {
		Monster[] out = new Monster[UIDList.length];
		
		for(int i = 0; i < out.length; i++) {
			
			for(int j = 0; j < items.size(); j++) {
				
				if(items.get(j).getShortName().equals(UIDList[i])) {
					out[i] = monsters.get(j);
				}
			}
		}
		
		return out;
	}

	private GenericItem[] retrieveItems(String[] UIDList) {
		GenericItem[] out = new GenericItem[UIDList.length];
		
		for(int i = 0; i < out.length; i++) {
			
			for(int j = 0; j < items.size(); j++) {
				
				if(items.get(j).getShortName().equals(UIDList[i])) {
					out[i] = items.get(j);
				}
			}
		}
		
		return out;
	}
	private GenericItem retrieveItem(String UID) {
		
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getShortName().equals(UID)) {
				return items.get(i);
			}
		}
		return null;
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
					
					String type = line.substring(6, line.length()-1);
					
					if(type.equals("Weapon")) {
						
						line = bufferedReader.readLine();
						int power = Integer.parseInt(line.substring(9, line.length()));
						line = bufferedReader.readLine();
						int bonus = Integer.parseInt(line.substring(13, line.length()));
						items.add(new Weapon(itemName, shortName, description, power, bonus));
						
					} else if (type.equals("Armor")) {
						
						line = bufferedReader.readLine();
						int defense = Integer.parseInt(line.substring(9, line.length()));
						items.add(new Armor(itemName, shortName, description, defense));
						
					} else if (type.equals("Blueprint")) {
						
						// TODO: add the required items to the Items.txt and set up the good info here
						// It is going to be a generic item until then
						items.add(new GenericItem(itemName, shortName, description));
						
					} else if (type.equals("Consumable")) {
						
						line = bufferedReader.readLine();
						int effect = Integer.parseInt(line.substring(7,line.length()));
						items.add(new Consumable(itemName, shortName, description, effect));
						
					} else if (type.equals("Crafting Item")) {
						
						items.add(new GenericItem(itemName, shortName, description));
						
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
	
	/**
	 * Method checkRoomChanged()
	 * @return boolean 
	 * True if the player has moved since the last check. False otherwise.
	 */
	public boolean checkRoomChanged() {
		return player.checkRoomChanged();
	}
	
	public void movePlayer(int direction) {
		
		setChanged();
		notifyObservers(player);
	}

}
