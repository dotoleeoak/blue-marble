// import java.util.ArrayList;
import java.awt.Point;

import javax.swing.JOptionPane;

public class Player extends Thread{
	private int ID;
	private String name;
	private int money;
	private int position;
	private int numThesis;
	private int numChance;
	private int live = 1;
	private PointManager coordinateManager;
	private Game game;
	private Point nowPoint;
	// ArrayList<String> chance;

	Player(int _id, String _name, Game _game) {
		ID = _id;
		name = _name;
		money = 1000;
		position = 0;
		numThesis = 0;
		numChance = 0;
		game = _game;
		// chance = new ArrayList<String>();
	}

	public void increPosition(){
		position++;
		position %= 16;
	}

	public int getID(){
		return ID;
	}

	public void lab() {
		numThesis += 3;
	}

	public boolean isInLab() {
		return numThesis > 0;
	}

	public void increChance() {
		numChance += 1;
	}

	public int getChance() {
		return numChance;
	}

	public int getMoney() {
		return money;
	}

	public int getPosition() {
		return position;
	}

	public void inStart(){
		money += 200;
	}

	public void earnMoney(int diff) {
		money += diff;
	}

	public void payToll(int _toll) {
		// if (money < _toll) {
		// 	return false;
		// }
		money -= _toll;
		// return money < 0;
	}

	public boolean buyCity(int _price) {
		if (money < _price) {
			return false;
		}
		money -= _price;
		return true;
	}

	public boolean buyBuilding(int _price) {
		if (money < _price) {
			return false;
		}
		money -= _price;
		return true;
	}

	public int nextPosition(int dice) {
		if (numThesis == 0 || dice == 6) {
			position = (position + dice) % 16;
		}
		return position;
	}
	
	public boolean hasChance() {
		return numChance > 0;
	}

	public void popChance() {
		numChance--;
	}
}
