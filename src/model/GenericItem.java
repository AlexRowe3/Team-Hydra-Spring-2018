package model;

public class GenericItem {
	
	private String itemName;
	private int uniqueID;
	private String itemShortName;
	private String description;
	
	public GenericItem(String itemName, int uniqueID, String itemShortName, String description) {
		
		this.itemName = itemName;
		this.uniqueID = uniqueID;
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
	
	public int getUID() {
		return uniqueID;
	}
	
}
