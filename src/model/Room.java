package model;

import java.util.ArrayList;

public class Room {
	
	private String roomUniqueID;
	private String roomName;
	private String roomType;
	private String roomDescription;
	private ArrayList<GenericItem> roomItems;
	private String roomSearch;
	private Character[] characters;
	//private Artifact[] artifacts;
	private Door[] doors;
	
	
	//List of values representing each direction in the code. for use in the connected rooms check
	public static final int NORTH = 0;
	public static final int NORTHEAST = 1;
	public static final int EAST = 2;
	public static final int SOUTHEAST = 3;
	public static final int SOUTH = 4;
	public static final int SOUTHWEST = 5;
	public static final int WEST = 6;
	public static final int NORTHWEST = 7;
	
	public Room (String roomUniqueID, String roomName, String roomType, String roomDescription, ArrayList<GenericItem> roomItems, 
			String roomSearch, Character[] characters) {
		this.roomUniqueID = roomUniqueID;
		this.roomName = roomName;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.roomItems = roomItems;
		this.roomSearch = roomSearch;
		this.characters = characters;
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
	
	public Character[] getMonsterList() {
		return characters;
		
	}
	
	public String getType() {
		return roomType;
	}
	
	public ArrayList<GenericItem> getRoomItems() {
		return roomItems;
	}
	
	public boolean checkDirection(int direction) {
		
		if(doors[direction].equals(null)) {
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
	
}
