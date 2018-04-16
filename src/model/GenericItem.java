package model;

public class GenericItem {
	
	private String itemName;
	private String itemShortName;
	private String description;
	
	public GenericItem(String itemName, String itemShortName, String description) {
		
		this.itemName = itemName;
		this.itemShortName = itemShortName;
		this.description = description;
		
	}
	
	public String examineItem() {
		return description;
		
	}
	
	public String getName() {
		return itemName;
		
	}
	
	public String getShortName() {
		return itemShortName;
	}
	
}
