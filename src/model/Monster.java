package model;

public class Monster extends Character{
	
	private String monsterShortName;
	
	public Monster(String UID, String name, int healthPoints, int strength, int defense, GenericItem[] heldItems,
			int experience, String description, String monsterShortName) {
		super(UID, name, healthPoints, strength, defense, heldItems, experience, description);
		this.monsterShortName = monsterShortName;
		
	}
}