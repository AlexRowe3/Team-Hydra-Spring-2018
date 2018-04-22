package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster extends Character implements Serializable{
	
	private int experience;
	
	public Monster(String UID, String name, int healthPoints, int strength, int defense, ArrayList<GenericItem> heldItems,
			int experience, String description) {
		super(UID, name, healthPoints, strength, defense, heldItems, description);
		
		this.experience = experience;
	}
	
	public int getExperience() {
		return experience;
	}
}