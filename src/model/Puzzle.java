
public class Puzzle {
	
	private int puzzleUniqueID;
	private Room location;
	private String description;
	private String puzzleQuestion;
	private String puzzleSolution;
	private String puzzleHint;
	
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
