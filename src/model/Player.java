package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable{
	
	private Armor equippedArmor;
	private Weapon equippedWeapon;
	private int level = 1;
	private Room currentRoom;
	private int experience;
	
	// These are used for the observers, they purely keep track of what changed 
	private boolean roomChanged = false;
	private boolean weaponChanged = false;
	private boolean armorChanged = false;
	private boolean xpChanged = true;
	private boolean lvlChanged = false;
	private boolean isAlive = true;
	
	public Player(String UID, String name, int healthPoints, int strength, int defense, ArrayList<GenericItem> heldItems,
			int experience, String description, Armor equippedArmor, Weapon equippedWeapon, int level,
			Room currentRoom) {
		super(UID, name, healthPoints, strength, defense, heldItems, description);
		this.experience = experience;
		this.equippedArmor = equippedArmor;
		this.equippedWeapon = equippedWeapon;
		this.level = level;
		this.currentRoom = currentRoom;
		changeRoom(currentRoom);
	}
	
	public boolean checkIsAlive() {
		return isAlive;
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
		roomChanged = false;
		return currentRoom;
	}
	
	public boolean getLevelChanged() {
		return lvlChanged;
	}
	
	public boolean getExpChanged() {
		return xpChanged;
	}
	
	public int getLevel() {
		lvlChanged = false;
		return level;
	}
	
	public int getExp() {
		xpChanged = false;
		return experience;
	}
	
	private void levelUp(int reqExpForLevel) {
		level++;
		
		addSTR();
		addHP();
		fullHeal();
		
		experience = experience % reqExpForLevel;
		lvlChanged = true;
	}
	
	public void increaseXp(int amount) {
		experience += amount;
		xpChanged = true;
		
		// TODO: set the actual level up timings from the SRS, here's the template
		// This is for IF you needed to have 1000 xp to get to level 3.  I'd rather not use a else if,
		// in case the player gets enough xp to level up multiple times in one fight.  Somehow 
		
		if (experience >= 10 && level == 1) {
			levelUp(10);
		}
		if (experience >= 20 && level == 2) {
			levelUp(20);
		}
		if (experience >= 40 && level == 3) {
			levelUp(40);
		}
		if (experience >= 80 && level == 4) {
			levelUp(80);
		}
	}
	
	public void die() {
		isAlive = false;
	}
}
