package model;

import java.io.Serializable;

public class Useable extends GenericItem implements Serializable {
	
	private String usage;
	
	public Useable(String itemName, String itemShortName, String description, String usage) {
		super(itemName, itemShortName, description);
		this.usage = usage;
		
	}
	
	public void use() {
		
	}

}
