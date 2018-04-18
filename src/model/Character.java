package model;

import java.util.ArrayList;
import java.util.Observable;

public class Character extends Observable {
	private String UID;
	private String name;
	private int healthPoints;
	private int strength;
	private int defense;
	private ArrayList<GenericItem> heldItems;
	//private Artifact[] heldArtifacts;
	private int experience;
	private String description;
	
	// Variables for use in the MVC to help identify what to update:
	private boolean healthChanged = true;
	
	public Character(String UID, String name, int healthPoints, int strength, int defense, ArrayList<GenericItem> heldItems,
			int experience, String description) {
		this.UID = UID;
		this.name = name;
		this.healthPoints = healthPoints;
		this.strength = strength;
		this.defense = defense;
		this.heldItems = heldItems;
		this.experience = experience;
		this.description = description;
	}
	
	public String getUID() {
		return UID;
	}
	
	public String getName() {
		return name;
		
	}
	
	public ArrayList<GenericItem> getHeldItems() {
		return heldItems;
	}
	
	public int getHealth() {
		healthChanged = false;
		return healthPoints;
		
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getExperience() {
		return experience;
		
	}
	
	public int getDefense() {
		return defense;
		
	}
	
	public int getStrength() {
		return strength;
		
	}
	
	public void attack() {
		// TODO figure out how to handle this.  Might have nothing to do with characters?
	}
	
	public void changeHealth(int effect) {
		healthPoints += effect;
		healthChanged = true;
	}
	
	public boolean getHealthChanged() {
		return healthChanged;
	}
	
	public GenericItem getItem(int selectedIndex) {
		return heldItems.get(selectedIndex);
	}
}
