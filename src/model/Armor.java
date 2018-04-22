package model;

import java.io.Serializable;

public class Armor extends GenericItem implements Serializable{
	
	private int defense;
	
	public Armor(String itemName, String itemShortName, String description, int armorValue) {
		super(itemName, itemShortName, description);
		defense = armorValue;
	}
	
	public int getArmorValue() {
		return defense;
	}

}
