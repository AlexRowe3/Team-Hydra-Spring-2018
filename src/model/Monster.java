package model;

public class Monster extends Character{
	
	private String monsterShortName;
	
	public Monster(int player, String name, int healthPoints, int strength, int defense, GenericItem[] heldItems,
			int experience, String description, String monsterShortName) {
		super(player, name, healthPoints, strength, defense, heldItems, experience, description);
		this.monsterShortName = monsterShortName;
		
	}

}
