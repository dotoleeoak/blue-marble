import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image rollDiceButtonImage = new ImageIcon(Main.class.getResource("images/rollDiceButton.png")).getImage();

	public int numPlayer;

	ArrayList<Player> playerList = new ArrayList<Player>();

	Game(int _numPlayer) {
		this.numPlayer = _numPlayer;
		setPlayer();
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
