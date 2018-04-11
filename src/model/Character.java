package model;

public class Character {
	private int player;
	private String name;
	private int healthPoints;
	private int strength;
	private int defense;
	private GenericItem[] heldItems;
	//private Artifact[] heldArtifacts;
	private int experience;
	private String description;
	
	public String getName() {
		return name;
		
	}
	
	public int getHealth() {
		return healthPoints;
		
	}
	
	public void changeHealth(int newHealth) {
		
	}
	
	public int getExperience() {
		return experience;
		
	}
	
	public int getDefense() {
		return defense;
		
	}
	
	public int getStrength() {
		return strength;
		
	}
	
	public String getDescription() {
		return name;
		
	}
	
	public void attack() {
		
	}
	
}
