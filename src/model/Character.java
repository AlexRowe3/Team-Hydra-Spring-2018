package model;

import java.util.Observable;

public class Character extends Observable {
	private String UID;
	private String name;
	private int healthPoints;
	private int strength;
	private int defense;
	private GenericItem[] heldItems;
	//private Artifact[] heldArtifacts;
	private int experience;
	private String description;
	
	public Character(String UID, String name, int healthPoints, int strength, int defense, GenericItem[] heldItems,
			int experience, String description) {
		this.UID = UID;
		this.name = name;
		this.healthPoints = healthPoints;
		this.strength = strength;
		this.defense = defense;
		this.heldItems = heldItems;
		this.experience = experience;
		this.description = description;
	}
	
	
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
