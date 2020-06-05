import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image rollDiceButtonImage = new ImageIcon(Main.class.getResource("images/rollDiceButton.png")).getImage();

	public int numPlayer;

	ArrayList<Coordinate> coordinateSet = new ArrayList<Coordinate>();
	ArrayList<Player> playerList = new ArrayList<Player>();

	Game(int _numPlayer) {
		this.numPlayer = _numPlayer;
		setCoordinate();
		setPlayer();
	}

	private void setCoordinate() {
		coordinateSet.add(new Coordinate(650, 563));
		coordinateSet.add(new Coordinate(200, 100));
		coordinateSet.add(new Coordinate(300, 100));
		coordinateSet.add(new Coordinate(300, 200));
		coordinateSet.add(new Coordinate(300, 300));
	}

	public void setPlayer() {
		switch (numPlayer) {
		case 2:
			playerList.add(new Player(0, "first"));
			playerList.add(new Player(0, "second"));
			break;
		case 3:
			playerList.add(new Player(0, "first"));
			playerList.add(new Player(1, "second"));
			playerList.add(new Player(2, "third"));
			break;
		case 4:
			playerList.add(new Player(0, "first"));
			playerList.add(new Player(1, "second"));
			playerList.add(new Player(2, "third"));
			playerList.add(new Player(3, "fourth"));
			break;
		}
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setPositionX(coordinateSet.get(0).x);
			playerList.get(i).setPositionY(coordinateSet.get(0).y);
		}

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(rollDiceButtonImage, 612, 350, null);
		for (int i = 0; i < playerList.size(); i++) {
			g.drawImage(playerList.get(i).getPiece(), playerList.get(i).getPositionX(),
					playerList.get(i).getPositionY(), null);
		}
	}
	
	@Override
	public void run() {
		
	}
	
	public void close() {
		this.interrupt();
	}
}
