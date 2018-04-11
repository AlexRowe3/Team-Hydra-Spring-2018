package model;

public class Artifact {
	
	private String itemName;
	private int uniqueID;
	private String itemShortName;
	private String description;
	private String type;
	private int listOfActions;
	
	public String examineItem() {
		return description;
	}
	
	public String getName() {
		return itemName;
	}
	
	public int getUID() {
		return uniqueID;
	}
}
