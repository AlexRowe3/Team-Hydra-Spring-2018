package view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Model;
import model.Monster;
import model.Player;
import model.Room;

public class CombatView implements Observer{
	
	private Stage stage;
	private Model model;
	
	// Player stats for the player info section
	private Label DplayerInfoLbl = new Label("");
	private Label DhpLbl = new Label("");
	private Label DlvlLbl = new Label("");
	private Label DxpLbl = new Label("");
	private Label DweaponLbl = new Label("Nothing");
	private Label DarmorLbl = new Label("Nothing");
	
	// Monster stats for the monster info section
	private Label DmonsterInfoLbl = new Label("");
	private Label DMhpLbl = new Label("");
	
	public CombatView(Model model, Stage stage) {
		this.model = model;
		this.stage = stage;
		Pane pane = new Pane();
		
		VBox bigBox = new VBox();
		bigBox.setPadding(new Insets(5,5,5,5));
		
		HBox monsterHBox = new HBox();
		monsterHBox.setPadding(new Insets(5,5,5,5));
		
		VBox monsterStaticVBox = new VBox();
		monsterStaticVBox.setPadding(new Insets(5,5,5,5));
		
		Label monsterInfoLbl = new Label("Monster Information: ");
		Label MhpLbl = new Label("HP: ");
		
		monsterStaticVBox.getChildren().addAll(monsterInfoLbl, MhpLbl);
		
		VBox monsterDynamicVBox = new VBox();
		monsterDynamicVBox.setPadding(new Insets(5,5,5,5));
		
		monsterDynamicVBox.getChildren().addAll(DmonsterInfoLbl, DMhpLbl);
		
		monsterHBox.getChildren().addAll(monsterStaticVBox, monsterDynamicVBox);
		
		HBox playerHBox = new HBox();
		playerHBox.setPadding(new Insets(5,5,5,5));
		
		VBox playerStaticVBox = new VBox();
		playerStaticVBox.setPadding(new Insets(5,5,5,5));
		
		Label playerInfoLbl = new Label("Player Information: ");
		Label hpLbl = new Label("HP: ");
		Label lvlLbl = new Label("Level: ");
		Label xpLbl = new Label("Experience: ");
		Label weaponLbl = new Label("Weapon: ");
		Label armorLbl = new Label("Armor: ");
		
		playerStaticVBox.getChildren().addAll(playerInfoLbl, hpLbl, lvlLbl, xpLbl, weaponLbl, armorLbl);
		
		VBox playerDynamicVBox = new VBox();
		playerDynamicVBox.setPadding(new Insets(5,5,5,5));
		
		// TODO: update the dynamic labels
		
		playerDynamicVBox.getChildren().addAll(DplayerInfoLbl, DhpLbl, DlvlLbl, DxpLbl, DweaponLbl, DarmorLbl);
		
		playerHBox.getChildren().addAll(playerStaticVBox, playerDynamicVBox);
		
		HBox buttonHBox = new HBox();
		buttonHBox.setPadding(new Insets(5,5,5,5));
		
		Button checkInvBtn = generateCheckInventoryButton();
		
		Button attackBtn = generateAttackButton();
		
		Button examineBtn = generateExamineButton();
		
		buttonHBox.getChildren().addAll(attackBtn, checkInvBtn, examineBtn);
		bigBox.getChildren().addAll(monsterHBox, playerHBox, buttonHBox);
		
		pane.getChildren().add(bigBox);
		
		/** This code is here to help handle the closeable combat window issue */
		Platform.setImplicitExit(false);

		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		
		
		Scene scene = new Scene(pane);
		this.stage.setTitle("Combat Window");
		this.stage.setScene(scene);
		this.stage.show();
	}

	private Button generateExamineButton() {
		Button out = new Button("Examine");
		
		out.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// only attack if the player is alive
				if (model.checkIsAlive()) {
					model.examineMonster();
				}
			}
			
		});
		return out;
	}

	private Button generateCheckInventoryButton() {
		Button button = new Button("Open Inventory");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					Stage ivStage = new Stage();
					InventoryView iv = new InventoryView(model.getPlayerItems(), model, "Player Combat Inventory", InventoryView.PLAYER, ivStage);
					model.addObserver(iv);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return button;
	}
	
	private Button generateAttackButton() {
		Button out = new Button("Attack");
		
		out.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// only attack if the player is alive
				if (model.checkIsAlive()) {
					model.attack();
				}
			}
			
		});
		return out;
	}
	
	@Override
	public void update(Observable o, Object a) {
		if (a instanceof Player) {
			
			if (model.checkHealthChanged(Model.PLAYER)) {
				
				DhpLbl.setText(model.getHealth(Model.PLAYER, Model.CURRENT) + " / " + model.getHealth(Model.PLAYER, Model.MAX));
			}
			if (model.checkArmorChanged()) {
				
				DarmorLbl.setText(model.getArmor().getName());
			}
			if (model.checkWeaponChanged()) {
				
				DweaponLbl.setText(model.getWeapon().getName());
			}
			if (model.checkLevelChanged()) {
				
				DlvlLbl.setText("" + model.getLevel());
			}
			if(model.checkExpChanged()) {
				
				DxpLbl.setText("" + model.getExp(Model.PLAYER));
			}
			
			if(!model.checkIsAlive()) {
				
				// remove the normal lock on closing the windows
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				    @Override
				    public void handle(WindowEvent event) {
				        
				    }
				});
			}
			
		} else if (a == null) {
			
			
		} else if (a instanceof Room) {
			if (((Room) a).getMonster() == null) {
				stage.close();
			}
		} else if (a instanceof Monster) {
			
			// the only time a monster is sent as a notification is for the start of combat!
			
			DmonsterInfoLbl.setText(((Monster) a).getName());
			DMhpLbl.setText(((Monster) a).getCurrentHealth() + "/" + ((Monster) a).getMaxHealth());
			
			DhpLbl.setText(model.getHealth(Model.PLAYER, Model.CURRENT) + "/" + model.getHealth(Model.PLAYER, Model.MAX));
			DlvlLbl.setText(model.getLevel() + "");
			DxpLbl.setText(model.getExp(Model.PLAYER) + "");
			if(model.getWeapon() != null) {
				DweaponLbl.setText(model.getWeapon().getName());
			}
			if(model.getArmor() != null) {
				DarmorLbl.setText(model.getArmor().getName());
			}
			
		}
	}
}
