package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class Character extends Observable implements Serializable{
	private String UID;
	private String name;
	private int maxHealthPoints;
	private int currentHealthPoints;
	private int strength;
	private int defense;
	private ArrayList<GenericItem> heldItems;
	private String description;
	
	// Variables for use in the MVC to help identify what to update:
	private boolean healthChanged = true;
	private boolean inventoryChanged = true;
	
	
	public Character(String UID, String name, int healthPoints, int strength, int defense, ArrayList<GenericItem> heldItems,
			String description) {
		this.UID = UID;
		this.name = name;
		maxHealthPoints = healthPoints;
		this.strength = strength;
		this.defense = defense;
		this.heldItems = heldItems;
		this.description = description;
		currentHealthPoints = maxHealthPoints;
	}
	
	public String getUID() {
		return UID;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<GenericItem> getHeldItems() {
		inventoryChanged = false;
		return heldItems;
	}
	
	public int getMaxHealth() {
		return maxHealthPoints;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getCurrentHealth() {
		healthChanged = false;
		return currentHealthPoints;
	}
	
	public void changeHealth(int effect) {
		if(effect > 0 && effect > (maxHealthPoints - currentHealthPoints)) {
			currentHealthPoints = maxHealthPoints;
		} else if (effect < 0 && effect < (-currentHealthPoints)) {
			currentHealthPoints = 0;
		} else {
			currentHealthPoints += effect;
		}
		healthChanged = true;
	}
	
	public boolean getHealthChanged() {
		return healthChanged;
	}
	
	public GenericItem getItem(int selectedIndex) {
		return heldItems.get(selectedIndex);
	}
	
	public void addItem(GenericItem newItem) {
		inventoryChanged = true;
		heldItems.add(newItem);
	}
	
	public void removeItem(int selectedIndex) {
		inventoryChanged = true;
		heldItems.remove(selectedIndex);
	}
	
	public boolean getInventoryChanged() {
		return inventoryChanged;
	}
	
	public void changeMaxHealth(int change) {
		healthChanged = true;
	}
	
	protected void addSTR() {
		strength++;
	}
	
	protected void addHP() {
		maxHealthPoints += 10;
		healthChanged = true;
	}
	
	protected void fullHeal() {
		currentHealthPoints = maxHealthPoints;
		healthChanged = true;
	}
}
