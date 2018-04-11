package model;

public class Puzzle {
	
	private int puzzleUniqueID;
	private Room location;
	private String description;
	private String puzzleQuestion;
	private String puzzleSolution;
	private String puzzleHint;
	
	public Puzzle(int puzzleUniqueID, Room location, String description, String puzzleQuestion, String puzzleSolution,
			String puzzleHint) {
		this.puzzleUniqueID = puzzleUniqueID;
		this.location = location;
		this.description = description;
		this.puzzleQuestion = puzzleQuestion;
		this.puzzleSolution = puzzleSolution;
		this.puzzleHint = puzzleHint;
	}
	
	
	public boolean checkSolution() {
		return true;
		
	}
	
	public String getDescription() {
		return description;
		
	}
	
	public String getQuestion() {
		return puzzleQuestion;
		
	}
	
	public String getHint() {
		return puzzleHint;
		
	}
}
