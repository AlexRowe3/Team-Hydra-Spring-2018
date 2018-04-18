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
				
				if(!line.startsWith("~")) {
					
					String name = line.substring(6, line.length()-1);
					line = bufferedReader.readLine();
					
					String shortName = line.substring(11, line.length()-1);
					line = bufferedReader.readLine();
					
					String desc = line.substring(13, line.length()-1);
					line = bufferedReader.readLine();
					
					int maxHP = Integer.parseInt(line.substring(7, line.length()));
					line = bufferedReader.readLine();
					
					int damage = Integer.parseInt(line.substring(7, line.length()));
					line = bufferedReader.readLine();
					
					int def = Integer.parseInt(line.substring(4, line.length()));
					line = bufferedReader.readLine();
					
					int xp = Integer.parseInt(line.substring(11, line.length()));
					line = bufferedReader.readLine();
					
					String itemsString = line.substring(7, line.length()-1);
					ArrayList<GenericItem> items = retrieveItems(itemsString.split(","));
					
					monsters.add(new Monster(shortName, name, maxHP, damage, def, items, xp, desc));
					
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

	private void loadNewPuzzles() {
		// TODO: Finish the file reading for the Monsters
		String fileName = ".\\src\\docs\\Puzzle.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				
				if(!line.startsWith("~")) {
					
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
						// I have to account for the KeyID line, but i don't care for it's input
						// So I tell the system to just take it in, but don't do anything with it.
						line = bufferedReader.readLine();
						
					} else {
						
						locked = true;
						line = bufferedReader.readLine();
						String keyID = line.substring(7, line.length()-1);
						addDoor(UID, room1, room2, locked, keyID);
						
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
			
			addDoorToRoom(door, targetRoom1, targetDirection1);
			addDoorToRoom(door, targetRoom2, targetDirection2);
		} else {
			door = new Door(targetRoom1, targetRoom2);
			
			addDoorToRoom(door, targetRoom1, targetDirection1);
			addDoorToRoom(door, targetRoom2, targetDirection2);
		}
	}

	private void addDoorToRoom(Door door, Room room, String direction) {
	
		if(direction.equals("N")) {
			room.addDoor(door, Room.NORTH);
		} else if (direction.equals("NE")) {
			room.addDoor(door, Room.NORTHEAST);
		} else if (direction.equals("E")) {
			room.addDoor(door, Room.EAST);
		} else if (direction.equals("SE")) {
			room.addDoor(door, Room.SOUTHEAST);
		} else if (direction.equals("S")) {
			room.addDoor(door, Room.SOUTH);
		} else if (direction.equals("SW")) {
			room.addDoor(door, Room.SOUTHWEST);
		} else if (direction.equals("W")) {
			room.addDoor(door, Room.WEST);
		} else if (direction.equals("NW")) {
			room.addDoor(door, Room.NORTHWEST);
		}
	}

	private void loadNewPlayer() {
		// Might make this changeable in a different view
		String name = "Player";
		// TODO: Check this information against the Requirements document
		int hp = 20;
		int strength = 0;
		int defense = 0;
		ArrayList<GenericItem> inventory = new ArrayList<GenericItem>();
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
					
					ArrayList<GenericItem> roomItems = retrieveItems(line.substring(7, line.length()-1).split(","));
					line = bufferedReader.readLine();
					
					String search = line.substring(8, line.length()-1);
					line = bufferedReader.readLine();
					
					ArrayList<Monster> monsters = retrieveMonsters(line.substring(0).split(","));
					
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

	private ArrayList<Monster> retrieveMonsters(String[] UIDList) {
		ArrayList<Monster> out = new ArrayList<Monster>();
		
		for(int i = 0; i < UIDList.length; i++) {
			
			for(int j = 0; j < items.size(); j++) {
				
				if(items.get(j).getShortName().equals(UIDList[i])) {
					out.add(monsters.get(j));
				}
			}
		}
		
		return out;
	}

	private ArrayList<GenericItem> retrieveItems(String[] UIDList) {
		
		ArrayList<GenericItem> out = new ArrayList<GenericItem>();
		
		String[] multiItemTemp = new String[2];
		int quantity;
		for(int i = 0; i < UIDList.length; i++) {
			
			if(UIDList[i].contains(":")) {
				
				multiItemTemp = UIDList[i].split(":");
				quantity = Integer.parseInt(multiItemTemp[1]);
				for(int j = 0; j < items.size(); j++) {
					
					if(items.get(j).getShortName().equals(multiItemTemp[0])) {
						for(int k = 0; k < quantity; k++) {
							out.add(items.get(j));
						}
					}
				}
				
				
			} else {
				
				for(int j = 0; j < items.size(); j++) {
						
					if(items.get(j).getShortName().equals(UIDList[i])) {
						out.add(items.get(j));
						break;
					}
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
						
					} else if (type.equals("BluePrint")) {
						
						line = bufferedReader.readLine();
						String[] inItems = line.substring(13, line.length()-1).split(",");
						ArrayList<GenericItem> requiredItems = retrieveItems(inItems);
						line = bufferedReader.readLine();
						
						String item = line.substring(13, line.length()-1);
						
						// It is going to be a generic item until then
						items.add(new Blueprint(itemName, shortName, description, requiredItems, retrieveItem(item)));
						
						
					} else if (type.equals("Consumable")) {
						
						line = bufferedReader.readLine();
						int effect = Integer.parseInt(line.substring(7,line.length()));
						items.add(new Consumable(itemName, shortName, description, effect));
						
					} else if (type.equals("Crafting Item") || type.equals("Key")) {
						
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
		int index = player.getCurrentRoom().getDoor(direction).checkRooms(player.getCurrentRoom());
		if(index == 1) {
			index = 0;
		} else if (index == 0) {
			index = 1;
		} else {
			return;
		}
		player.changeRoom(player.getCurrentRoom().getDoor(direction).getRoom(index));
		
		setChanged();
		notifyObservers(player);
	}
	
	public boolean checkDirection(int direction) {
		return player.getCurrentRoom().checkDirection(direction);
	}

	public GenericItem getPlayerItem(int selectedIndex) {
		return player.getItem(selectedIndex);
	}

}
