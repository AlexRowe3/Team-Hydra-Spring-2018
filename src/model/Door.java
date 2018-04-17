package model;

public class Door {
	
	private boolean isLocked = false;
	private Room[] rooms = new Room[2];
	private GenericItem key;
	
	
	public Door(Room room1, Room room2, boolean locked, GenericItem unlockKey) {
		rooms[0] = room1;
		rooms[1] = room2;
		isLocked = locked;
		key = unlockKey;
	}
	
	public Door(Room room1, Room room2) {
		rooms[0] = room1;
		rooms[1] = room2;
		isLocked = false;
		key = null;
	}
	
	/**
	 * Method: checkRooms(Room)
	 * @param room The room you want to see if it connects too
	 * @return 
	 * 0, 1 index of room identified room
	 * 2 requested room not found
	 * 
	 */
	public int checkRooms(Room room) {
		if(room.equals(rooms[0])) {
			return 0;
		} else if (room.equals(rooms[1])) {
			return 1;
		}
		return 2;
	}
	
	/**
	 * Method: getRoom(int)
	 * @param index the index of the room you are requesting (0, 1)
	 * @return the room that was requested
	 * Best when used with checkRooms() to get the index of the current room.
	 */
	public Room getRoom(int index) {
		return rooms[index];
	}
	
	
	public boolean getIsLocked() {
		return isLocked;
	}
	
	/**
	 * Method: getKey()
	 * @return GenericItem the required key to unlock this door
	 * returns null if the door is unlocked.
	 */
	public GenericItem getKey() {
		if(isLocked) {
			return key;
		}
		return null;
	}
	
	public void unlock() {
		isLocked = false;
	}
	
}
