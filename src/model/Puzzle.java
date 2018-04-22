package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Puzzle implements Serializable{
	
	private String puzzleUniqueID;
	private String description;
	private String puzzleQuestion;
	private String puzzleSolution;
	private String puzzleHint;
	private ArrayList<GenericItem> reward;
	private String incorrect;
	private boolean retryable;
	
	private boolean solved = false;
	
	public Puzzle(String puzzleUniqueID, String description, String puzzleQuestion, String puzzleSolution,
			String puzzleHint, ArrayList<GenericItem> reward, String incorrect) {
		this.puzzleUniqueID = puzzleUniqueID;
		this.description = description;
		this.puzzleQuestion = puzzleQuestion;
		this.puzzleSolution = puzzleSolution.toLowerCase();
		this.puzzleHint = puzzleHint;
		this.reward = reward;
		this.incorrect = incorrect;
		retryable = false;
	}
	
	public Puzzle(String puzzleUniqueID, String description, String puzzleQuestion, String puzzleSolution,
			String puzzleHint, ArrayList<GenericItem> reward) {
		this.puzzleUniqueID = puzzleUniqueID;
		this.description = description;
		this.puzzleQuestion = puzzleQuestion;
		this.puzzleSolution = puzzleSolution.toLowerCase();
		this.puzzleHint = puzzleHint;
		this.reward = reward;
		incorrect = "You may try this one again.";
		retryable = true;
	}
	
	public boolean checkSolution(String input) {
		if(puzzleSolution.equals(input)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getSolution() {
		return puzzleSolution;
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
	
	public boolean getRetryable() {
		return retryable;
	}
	
	public String getIncorrect() {
		return incorrect;
	}
	
	public ArrayList<GenericItem> getReward() {
		return reward;
	}

	public boolean getSolved() {
		return solved;
	}

	public void solve() {
		solved = true;
	}
}
