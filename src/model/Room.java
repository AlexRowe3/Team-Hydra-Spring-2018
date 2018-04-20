package model;

import java.util.ArrayList;

public class Room {
	
	private String roomUniqueID;
	private String roomName;
	private String roomType;
	private String roomDescription;
	private ArrayList<GenericItem> roomItems;
	private String roomSearch;
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
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
	
	private boolean itemsChanged = false;
	
	private boolean monstersChanged = false;
	
	public Room (String roomUniqueID, String roomName, String roomType, String roomDescription, ArrayList<GenericItem> roomItems, 
			String roomSearch, ArrayList<Monster> monsters) {
		this.roomUniqueID = roomUniqueID;
		this.roomName = roomName;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.roomItems = roomItems;
		this.roomSearch = roomSearch;
		this.monsters = monsters;
		
		for(int i = 0; i < doors.length; i++) {
			doors[i] = null;
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
	
	public ArrayList<Monster> getMonsterList() {
		monstersChanged = false;
		return monsters;
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
	
	public boolean checkMonstersChanged() {
		return monstersChanged;
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
	
	public void removeMonster(int index) {
		monstersChanged = true;
		monsters.remove(index);
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
	
	public void killMonster(int target) {
		
		roomItems.addAll(monsters.get(target).getHeldItems());
		itemsChanged = true;
		
		monsters.remove(target);
		monstersChanged = true;
	}
}
