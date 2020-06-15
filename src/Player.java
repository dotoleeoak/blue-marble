
/*
Work of each team member
Choi jaemin(2019314992) : Modify class structure to use properly in Game and City class
Seo Kangmin(2019310155) : Designed Class structure, added getter and setter, code modification for lab
*/

public class Player {
	private int ID;
	private int money;
	private int position;
	private int numThesis;
	private int numChance;
	// ArrayList<String> chance;

	Player(int _id) {
		ID = _id;
		money = 1500;
		position = 0;
		numThesis = 0;
		numChance = 0;
	}

	public void increPosition() {
		position++;
		if (position == 16) {
			position = 0;
			money += 750;
		}
		// position %= 16;
	}

	public int getID() {
		return ID;
	}

	public void lab() {
		numThesis += 3;
	}

	public boolean isInLab() {
		return numThesis > 0;
	}

	public int getRemainingThesis() {
		return numThesis;
	}

	public void writeThesis() {
		numThesis--;
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

	// public void inStart() {
	// money += 200;
	// }

	public void earnMoney(int diff) {
		money += diff;
	}

	public void payToll(int _toll) {
		money -= _toll;
	}

	public boolean buyCity(int _price) {
		if (money < _price) {
			return false;
		}
		money -= _price;
		return true;
	}

	public boolean canBuyBuilding(int _price) {
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
