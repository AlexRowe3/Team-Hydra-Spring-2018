package model;

public class Useable extends GenericItem{
	
	private String usage;
	
	public Useable(String itemName, String itemShortName, String description, String usage) {
		super(itemName, itemShortName, description);
		this.usage = usage;
		
	}
	
	public void use() {
		
	}

}
