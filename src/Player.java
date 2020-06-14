// import java.util.ArrayList;
import java.awt.Point;

public class Player extends Thread{
	int ID;
	String name;
	int money;
	int position;
	int numThesis;
	int numChance;
	int live = 1;
	PointManager coordinateManager;
	Game game;
	Point nowPoint;
	// ArrayList<String> chance;

	Player(int _id, String _name, Game _game) {
		ID = _id;
		name = _name;
		money = 2000;
		position = 0;
		numThesis = 0;
		numChance = 0;
		game = _game;
		// chance = new ArrayList<String>();
	}

	public void lab() {
		numThesis += 3;
	}

	public void winChance() {
		numChance += 1;
	}
	public void inStart(){
		money += 200;
	}

	public void earnMoney(int diff) {
		money += diff;
	}

	public boolean payToll(int _toll) {
		if (money < _toll) {
			return false;
		}
		money -= _toll;
		return true;
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

	/* public int nextPosition(int dice) {
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

	

	

	

	// ?���? ?��?�� �? 걸렸?�� ?��
	
 */

}
