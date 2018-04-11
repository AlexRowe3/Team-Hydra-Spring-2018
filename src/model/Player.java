package model;

public class Player extends Character{
	
	private GenericItem equippedArmor;
	private GenericItem equippedWeapon;
	private int level;
	private Room currentRoom;
	// this is used to help the observer know if it should display new room information
	// when we tell them we updated the player.
	private boolean roomChanged;
	
	public Player() {
		roomChanged = true;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void changeRoom(Room targetRoom) {
		currentRoom = targetRoom;
		roomChanged = true;
		setChanged();
		notifyObservers();
		
	}
	
	public void changeArmor() {
		
	}
	
	public void changeWeapon() {
		
	}
	
	public void editInventory() {
		
	}
	
	public boolean getRoomChanged() {
		/* I built this this way so that I can make sure that I return the correct value,
		 * but set roomChanged to false regardless. Since this method will only be used
		 * by the observer classes.
		 */
		boolean temp = roomChanged;
		roomChanged = false;
		return temp;
	}

}
