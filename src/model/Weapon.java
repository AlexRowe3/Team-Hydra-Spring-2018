package model;

public class Weapon extends GenericItem{
	
	private int strength;
	private int attackBonus;
	
	public Weapon(String itemName, int uniqueID, String itemShortName, String description, int strength,
			int attackBonus) {
		super(itemName, uniqueID, itemShortName, description);
		this.strength = strength;
		this.attackBonus = attackBonus;
	}
	
	public int getAttackBonus() {
		return attackBonus;
		
	}
	
	public int getStrength() {
		return strength;
		
	}

}
