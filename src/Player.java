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
		money = 1000;
		position = 0;
		numThesis = 0;
		numChance = 0;
		game = _game;
		// chance = new ArrayList<String>();
	}


	/* public int nextPosition(int dice) {
		if (numThesis == 0 || dice == 6) {
			position = (position + dice) % 16;
		}
		return position;
	}

	public void lab() {
		numThesis += 3;
	}

	public void winChance() {
		numChance += 1;
	}

	public boolean hasChance() {
		return numChance > 0;
	}

	public void popChance() {
		numChance--;
	}

	public void earnMoney(int diff) {
		money += diff;
	}

	public boolean buyCity(int idxCity) {
		int price = Game.cityManager.getPrice(idxCity);
		if (money < price) {
			return false;
		}
		money -= price;
		Game.cityManager.buyCity(idxCity, ID);
		return true;
	}

	public boolean buyBuilding(int idxCity) {
		int price = Game.cityManager.getPriceBuilding(idxCity);
		if (money < price) {
			return false;
		}
		money -= price;
		Game.cityManager.buyBuilding(idxCity, ID);
		return true;
	}

	// ?���? ?��?�� �? 걸렸?�� ?��
	public boolean payToll(int idxCity) {
		int toll = Game.cityManager.getToll(idxCity);
		if (money < toll) {
			return false;
		}
		money -= toll;
		return true;
	}
 */

}
