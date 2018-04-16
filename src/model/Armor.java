package model;

public class Armor extends GenericItem{
	
	private int defense;
	
	public Armor(String itemName, String itemShortName, String description, int armorValue) {
		super(itemName, itemShortName, description);
		defense = armorValue;
	}
	
	public int getArmorValue() {
		return defense;
	}

}
