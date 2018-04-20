package view;

import java.util.Observable;
import java.util.Observer;

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
import model.GenericItem;
import model.Model;
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
	
	public CombatView(Model model) {
		this.model = model;
		stage = new Stage();
		Pane pane = new Pane();
		
		VBox bigBox = new VBox();
		bigBox.setPadding(new Insets(5,5,5,5));
		
		HBox monsterHBox = new HBox();
		monsterHBox.setPadding(new Insets(5,5,5,5));
		
		VBox monsterStaticVBox = new VBox();
		monsterStaticVBox.setPadding(new Insets(5,5,5,5));
		
		Label monsterInfoLbl = new Label("Player Information: ");
		Label MhpLbl = new Label("HP: ");
		
		monsterStaticVBox.getChildren().addAll(monsterInfoLbl, MhpLbl);
		
		VBox monsterDynamicVBox = new VBox();
		monsterDynamicVBox.setPadding(new Insets(5,5,5,5));
		
		monsterDynamicVBox.getChildren().addAll(DmonsterInfoLbl, DMhpLbl);
		
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
		
		buttonHBox.getChildren().addAll(attackBtn, checkInvBtn);
		bigBox.getChildren().addAll(monsterHBox, playerHBox, buttonHBox);
		
		Scene scene = new Scene(pane);
		stage.setTitle("Combat Window");
		stage.setScene(scene);
		stage.show();
	}

	private Button generateCheckInventoryButton() {
		Button button = new Button("Open Inventory");
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					InventoryView iv = new InventoryView(model.getPlayerItems(), model, "Player Inventory", InventoryView.PLAYER);
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
				
				// TODO: fill handler
				
				
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
			
		} else if (a == null) {
			
			
		} else if (a instanceof Room && ((Room) a).checkMonstersChanged()) {
			stage.close();
		}
	}
}
