package model;

import java.util.ArrayList;

public class Player extends Character{
	
	private Armor equippedArmor;
	private Weapon equippedWeapon;
	private int level;
	private Room currentRoom;
	
	// These are used for the observers, they purely keep track of what changed 
	private boolean roomChanged = false;
	
	public Player(String UID, String name, int healthPoints, int strength, int defense, ArrayList<GenericItem> heldItems,
			int experience, String description, Armor equippedArmor, Weapon equippedWeapon, int level,
			Room currentRoom) {
		super(UID, name, healthPoints, strength, defense, heldItems, experience, description);
		this.equippedArmor = equippedArmor;
		this.equippedWeapon = equippedWeapon;
		this.level = level;
		this.currentRoom = currentRoom;
		changeRoom(currentRoom);
	}

	public Armor getArmor() {
		return equippedArmor;
	}
	
	public Weapon getWeapon() {
		return equippedWeapon;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public boolean checkRoomChanged() {
		if(roomChanged) {
			roomChanged = false;
			return true;
		} else {
			return false;
		}
	}
	
	public void changeRoom(Room targetRoom) {
		currentRoom = targetRoom;
		roomChanged = true;
	}
	
	public void falseChangeRoom() {
		roomChanged = true;
	}
	
	public void changeArmor() {
		
	}
	
	public void changeWeapon() {
		
	}
	
	public Room getChangedRoom() {
		return currentRoom;
	}
	
	public int getLevel() {
		return level;
	}
}
