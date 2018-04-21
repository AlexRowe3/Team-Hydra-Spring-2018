package model;

public class Puzzle {
	
	private String puzzleUniqueID;
	private String description;
	private String puzzleQuestion;
	private String puzzleSolution;
	private String puzzleHint;
	
	public Puzzle(String puzzleUniqueID, String description, String puzzleQuestion, String puzzleSolution,
			String puzzleHint) {
		this.puzzleUniqueID = puzzleUniqueID;
		this.description = description;
		this.puzzleQuestion = puzzleQuestion;
		this.puzzleSolution = puzzleSolution;
		this.puzzleHint = puzzleHint;
	}
	
	public boolean checkSolution(String input) {
		if(puzzleSolution.equals(input)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getUID() {
		return puzzleUniqueID;
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
