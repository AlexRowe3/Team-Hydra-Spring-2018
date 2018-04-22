package model;

import java.util.ArrayList;

public class Blueprint extends GenericItem {
	
	ArrayList<GenericItem> requiredItems;
	GenericItem output;
	public Blueprint(String itemName, String itemShortName, String description, ArrayList<GenericItem> requiredItems, GenericItem output) {
		super(itemName, itemShortName, description);
		this.requiredItems = requiredItems;
		this.output = output;
	}
	
	public ArrayList<GenericItem> getRequiredItems() {
		return requiredItems;
	}
	
	public GenericItem getOutput() {
		return output;
	}

	public void removeItem(int i) {
		requiredItems.remove(i);
	}
	
}
