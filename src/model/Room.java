package model;

public class Room {
	
	private int roomUniqueID;
	private String roomName;
	private int roomType;
	private String roomDescription;
	private int roomItems;
	private String roomSearch;
	private Character[] characters;
	//private Artifact[] artifacts;
	private Room[] connectedRooms;
	
	
	//List of values representing each direction in the code. for use in the connected rooms check
	public static final int NORTH = 0;
	public static final int NORTHEAST = 1;
	public static final int EAST = 2;
	public static final int SOUTHEAST = 3;
	public static final int SOUTH = 4;
	public static final int SOUTHWEST = 5;
	public static final int WEST = 6;
	public static final int NORTHWEST = 7;
	
	public Room (int roomUniqueID, String roomName, int roomType, String roomDescription, int roomItems, 
			String roomSearch, Character[] characters, Room[] connectedRooms) {
		this.roomUniqueID = roomUniqueID;
		this.roomName = roomName;
		this.roomType = roomType;
		this.roomDescription = roomDescription;
		this.roomItems = roomItems;
		this.roomSearch = roomSearch;
		this.characters = characters;
		this.connectedRooms = connectedRooms;
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
	
	public boolean checkDirection(int direction) {
		
		if(connectedRooms[direction].equals(null)) {
			return false;
		}
		return true;
		
	}
	
}
