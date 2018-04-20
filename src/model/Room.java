package model;

import java.util.ArrayList;

public class Room {
	
	private String roomUniqueID;
	private String roomName;
	private String roomType;
	private String roomDescription;
	private ArrayList<GenericItem> roomItems;
	private String roomSearch;
	private Monster monster;
	//private Artifact[] artifacts;
	private Door[] doors = new Door[8];
	
	//List of values representing each direction in the code. for use in the connected rooms check
	public static final int NORTH = 0;
	public static final int NORTHEAST = 1;
	public static final int EAST = 2;
	public static final int SOUTHEAST = 3;
	public static final int SOUTH = 4;
	public static final int SOUTHWEST = 5;
	public static final int WEST = 6;
	public static final int NORTHWEST = 7;
	
	// For use with communicating with the controller (identifying what's changed)
	private boolean itemsChanged = false;
	private boolean hasMonster = false;
	
	public Room (String roomUniqueID, String roomName, String roomType, String roomDescription, ArrayList<GenericItem> roomItems, 
			String roomSearch, Monster monster) {
		this.roomUniqueID = roomUniqueID;
		this.roomName = roomName;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.roomItems = roomItems;
		this.roomSearch = roomSearch;
		this.monster = monster;
		
		for(int i = 0; i < doors.length; i++) {
			doors[i] = null;
		}
		
		if (monster != null) {
			hasMonster = true;
		}
	}
	
	public String getUID() {
		return roomUniqueID;
	}
	
	public String getDescription() {
		return roomDescription;
	}
	
	public String getName() {
		return roomName;
	}
	
	public String getSearchResults() {
		return roomSearch;
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public String getType() {
		return roomType;
	}
	
	public ArrayList<GenericItem> getRoomItems() {
		return roomItems;
	}
	
	public boolean checkItemsChanged() {
		return itemsChanged;
	}
	
	public boolean checkHasMonster() {
		return hasMonster;
	}
	
	public boolean checkDirection(int direction) {
		
		if(doors[direction] == null) {
			return false;
		}
		return true;
	}
	
	public Door getDoor(int direction) {
		return doors[direction];
	}

	public void addDoor(Door door, int direction) {
		doors[direction] = door;
	}
	
	public void removeItem(int index) {
		itemsChanged = true;
		roomItems.remove(index);
	}

	public GenericItem getRoomItem(int selectedIndex) {
		return roomItems.get(selectedIndex);
	}
	
	public void addItem(GenericItem newItem) {
		itemsChanged = true;
		roomItems.add(newItem);
	}
	
	public void killMonster() {
		if (monster != null) {
			roomItems.addAll(monster.getHeldItems());
			itemsChanged = true;
			
			monster = null;
			hasMonster = false;
		}
	}
}
