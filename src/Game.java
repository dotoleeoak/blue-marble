import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Game extends Thread {

	public int numPlayer;
	private int playerIdx;
	private int dice;
	private boolean moved;
	public int gamestep;

	public CityManager cityManager = new CityManager();
	public PointManager coordinateManager = new PointManager();
	public GameGUI gameGUI;
	ArrayList<Player> playerList;

	Main controller;

	Game(Main c, int _numPlayer) {
		this.numPlayer = _numPlayer;
		playerIdx = 0;
		playerList = new ArrayList<Player>();
		// coordinatePlayer = new Coordinate[numPlayer];

		playerList.add(new Player(0, "First", this));
		playerList.add(new Player(1, "Second", this));
		if (numPlayer >= 3) {
			playerList.add(new Player(3, "Fourth", this));
		}
		if (numPlayer == 4) {
			playerList.add(new Player(2, "Third", this));
		}


		gameGUI = new GameGUI(this);
		gameGUI.setVisible(true);
		c.add(gameGUI);

		moved = false;
		controller = c;
		gamestep = 0;
	}

	@Override
	public void run() {
		try {
			do {
				Player nowPlayer = playerList.get(playerIdx);
				dice = -1;
				gameGUI.trueReadyRolling();
				while( dice == -1 ){
					Thread.sleep(1000);
				} //invoke
				System.out.println("!");
				gameGUI.falseReadyRolling();
				for(int i = 0; i <dice; i++){
					move(nowPlayer);
				}
				


				playerIdx++;
				playerIdx %= 4;
			}while(gamestep != -1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void move(Player _nowPlayer){
		Point prePoint = coordinateManager.getPlayerPoint(_nowPlayer.ID, _nowPlayer.position);
		_nowPlayer.position++;
		_nowPlayer.position %= 16;
		Point nextPoint = coordinateManager.getPlayerPoint(_nowPlayer.ID, _nowPlayer.position);
		for(int i = 0; i < 50; i++){
			Point interPoint = new Point( (nextPoint.x*i - (50-i)*prePoint.x)/50 ,(nextPoint.y*i - (50-i)*prePoint.y)/50);
			try {
				gameGUI.playerMove(_nowPlayer, interPoint);
				Thread.sleep(20);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	void rollDice() {
		dice = new Random().nextInt(6) + 1;
	}

	public void close() {
		this.interrupt();
	}

	
	/*
	 * Every time rollDiceButton is pressed, call this method. This method defines
	 * things to do in each turn.
	 */
	

}
