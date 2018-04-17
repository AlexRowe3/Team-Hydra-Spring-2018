package model;

import java.util.ArrayList;

public class Monster extends Character{
	
	public Monster(String UID, String name, int healthPoints, int strength, int defense, ArrayList<GenericItem> heldItems,
			int experience, String description) {
		super(UID, name, healthPoints, strength, defense, heldItems, experience, description);
		
		// TODO: figure out what distinguishes a Monster from a Character
	}
}