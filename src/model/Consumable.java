package model;

public class Consumable extends GenericItem{
	
	private int effect;
	
	public Consumable (String itemName, int uniqueID, String itemShortName, String description, int effect) {
		
		super(itemName, effect, itemShortName, description);
		this.effect = effect;
	}
	
	public int consume() {
		return effect;
	}

}
