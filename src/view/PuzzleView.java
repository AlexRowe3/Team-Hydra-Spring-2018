package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

public class PuzzleView implements Observer{
	
	private Label puzzle;
	private Label hint;
	private TextField tf;
	private Button solveBtn;
	private Button hintBtn;
	private Label result;
	
	private Model model;
	private Stage stage;
	
	public PuzzleView(Model model, Stage stage) {
		
		this.stage = stage;
		this.model = model;
		Pane pane = new Pane();
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(5,5,5,5));
		
		HBox hbox = new HBox();
		
		puzzle = new Label(this.model.getPuzzle().getQuestion());
		hint = new Label("Hint: Click the Hint button to recieve your hint");
		tf = new TextField();
		solveBtn = generateSolveBtn();
		hintBtn = generateHintBtn();
		result = new Label("");
		
		// Create and fill the vbox with buttons
		
		hbox.getChildren().addAll(solveBtn, hintBtn);
		
		vbox.getChildren().addAll(puzzle, hint, tf, hbox, result);
		vbox.setSpacing(10);
		
		pane.getChildren().add(vbox);
		
		Scene scene = new Scene(pane, 400, 100);
		this.stage.setTitle("Puzzle");
		this.stage.setResizable(false);
		this.stage.setScene(scene);
		this.stage.show();
	}

	private Button generateHintBtn() {
		Button button = new Button("Hint");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				hint.setText(model.getPuzzle().getHint());
			}
		});
		return button;
	}

	private Button generateSolveBtn() {
		Button button = new Button("Solve");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(model.getPuzzle().getRetryable()) {
					
					if(model.getPuzzle().checkSolution(tf.getText().toLowerCase())) {
						
						// puzzle solved, send reward to room and close window 
						model.puzzleSolved();
						stage.close();
						
					} else {
						
						model.puzzleFail();
					}
					
				} else {
					
					if(model.getPuzzle().checkSolution(tf.getText().toLowerCase())) {
						
						// puzzle solved, send reward to room and close window 
						model.puzzleSolved();
						stage.close();
						
					} else {
						
						model.truePuzzleFail();
						stage.close();
					}
				}
			}
		});
		return button;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
