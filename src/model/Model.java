package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * @author William Bullock
 * @version 0.1
 * Written: April 11th, 2018
 * 
 * This is the over-arching Model class for interaction with the model.
 * It is basically how I imagine some APIs to be like.
 */
public class Model extends Observable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<GenericItem> items = new ArrayList<GenericItem>();
	
	public Model() {
		loadNewGame();
	}
	//TODO: Add a constructor to load a game? Might not need as we can load the object from a .bin save

	private void loadNewGame() {
		
		loadItems();
		
		loadRooms();
		
		loadPlayer();
		
	}

	private void loadPlayer() {
		// TODO Auto-generated method stub
		
	}

	private void loadRooms() {
		// TODO Auto-generated method stub
		
	}

	private void loadItems() {
		// TODO Auto-generated method stub
		
	}

}
