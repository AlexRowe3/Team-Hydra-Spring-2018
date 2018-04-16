package model;

public class Consumable extends GenericItem{
	
	private int effect;
	
	public Consumable (String itemName, String itemShortName, String description, int effect) {
		
		super(itemName, itemShortName, description);
		this.effect = effect;
	}
	
	public int consume() {
		return effect;
	}

}
