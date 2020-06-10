import java.util.ArrayList;

public class Player {
	int ID;
	String name;
	int money;
	int position;
	int numThesis;
	ArrayList<String> chance;

	// ÁÂÇ¥ ÀÌ¹ÌÁö´Â ÀüºÎ Game class¿¡¼­ Ã³¸®
	Player(int _id, String _name) {
		ID = _id;
		name = _name;
		money = 1000;
		position = 0;
		numThesis = 0;
		chance = new ArrayList<String>();
	}

	public int nextPosition(int dice) {
		if (numThesis == 0 || dice == 6) {
			position += dice;
		}
	// ì¢Œí‘œ ?´ë¯¸ì???Š” ? „ë¶? Game class?—?„œ ì²˜ë¦¬
		return position;
	}

	public void setThesis() {
		numThesis += 3;
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
}
