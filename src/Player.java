import java.util.ArrayList;
// import java.awt.Image;
// import javax.swing.ImageIcon;

public class Player {
	int ID;
	String name;
	int money;
	boolean isIsolated;
	ArrayList<String> chance;

	// 좌표, 이미지는 전부 Game class에서 처리
	// Coordinate position;
	Player(int _id, String _name) {
		ID = _id;
		name = _name;
		money = 1000;
		isIsolated = false;
		chance = new ArrayList<String>();
	}

	public int rollDice() {
		return Main.random.nextInt() % 6 + 1;
	}

	public void buyCity() {

	}

	public void buyBuilding() {

	}
}
