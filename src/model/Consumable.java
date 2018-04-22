package model;

import java.io.Serializable;

public class Consumable extends GenericItem implements Serializable{
	
	private int effect;
	
	public Consumable (String itemName, String itemShortName, String description, int effect) {
		
		super(itemName, itemShortName, description);
		this.effect = effect;
	}
	
	public int consume() {
		return effect;
	}

}
