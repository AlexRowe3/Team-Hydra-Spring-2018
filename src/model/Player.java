package model;

import java.util.ArrayList;

public class Player extends Character{
	
	private Armor equippedArmor;
	private Weapon equippedWeapon;
	private int level;
	private Room currentRoom;
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
		return roomChanged;
	}
	
	public void changeRoom(Room targetRoom) {
		currentRoom = targetRoom;
		roomChanged = true;
	}
	
	public void changeArmor() {
		
	}
	
	public void changeWeapon() {
		
	}
	
	public void addItem() {
		
	}
	
	public Room getChangedRoom() {
		roomChanged = false;
		return currentRoom;
	}
	
	public int getLevel() {
		return level;
	}
}
