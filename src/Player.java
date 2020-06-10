import java.util.ArrayList;

public class Player {
	int ID;
	String name;
	int money;
	int position;
	int numThesis;
	ArrayList<String> chance;

	// 좌표 이미지는 전부 Game class에서 처리
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
