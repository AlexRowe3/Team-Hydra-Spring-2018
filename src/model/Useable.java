package model;

public class Useable extends GenericItem{
	
	private String usage;
	
	public Useable(String itemName, int uniqueID, String itemShortName, String description, String usage) {
		super(itemName, uniqueID, itemShortName, description);
		this.usage = usage;
		
	}
	
	public void use() {
		
	}

}
