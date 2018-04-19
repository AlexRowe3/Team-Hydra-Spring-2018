package model;

import java.util.ArrayList;

public class Player extends Character{
	
	private Armor equippedArmor;
	private Weapon equippedWeapon;
	private int level;
	private Room currentRoom;
	
	// These are used for the observers, they purely keep track of what changed 
	private boolean roomChanged = false;
	private boolean weaponChanged = false;
	private boolean armorChanged = false;
	
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
		armorChanged = false;
		return equippedArmor;
	}
	
	public Weapon getWeapon() {
		weaponChanged = false;
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
	
	public boolean checkWeaponChanged() {
		return weaponChanged;
	}
	
	public boolean checkArmorChanged() {
		return armorChanged;
	}
	
	public void changeRoom(Room targetRoom) {
		currentRoom = targetRoom;
		roomChanged = true;
	}
	
	public void falseChangeRoom() {
		roomChanged = true;
	}
	
	public void equip(int newEquipIndex) {
		if(getItem(newEquipIndex) instanceof Armor) {
			
			if (equippedArmor != null) {
				addItem(equippedArmor);
			}
			equippedArmor = (Armor) getItem(newEquipIndex);
			removeItem(newEquipIndex);
			armorChanged = true;
			
		} else if (getItem(newEquipIndex) instanceof Weapon) {
			
			if (equippedWeapon != null) {
				addItem(equippedWeapon);
			}
			equippedWeapon = (Weapon) getItem(newEquipIndex);
			removeItem(newEquipIndex);
			weaponChanged = true;
		}
	}
	
	public Room getChangedRoom() {
		return currentRoom;
	}
	
	public int getLevel() {
		return level;
	}
}
