// import java.util.ArrayList;

public class Player {
	int ID;
	String name;
	int money;
	int position;
	int numThesis;
	int numChance;
	// ArrayList<String> chance;

	Player(int _id, String _name) {
		ID = _id;
		name = _name;
		money = 1000;
		position = 0;
		numThesis = 0;
		numChance = 0;
		// chance = new ArrayList<String>();
	}

	public int nextPosition(int dice) {
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

	// ?‹¤ë¥? ?‚¬?žŒ ì¹? ê±¸ë ¸?„ ?•Œ
	public boolean payToll(int idxCity) {
		int toll = Game.cityManager.getToll(idxCity);
		if (money < toll) {
			return false;
		}
		money -= toll;
		return true;
	}


}
