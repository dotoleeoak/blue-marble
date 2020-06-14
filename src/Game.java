import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends Thread {

	public int numPlayer;
	private int playerIdx;
	private int dice;
	private boolean moved;

	public CityManager cityManager = new CityManager();
	public PointManager pointManager = new PointManager();
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
			playerList.add(new Player(2, "Third", this));
		}
		if (numPlayer == 4) {
			playerList.add(new Player(3, "Fourth", this));
		}
		gameGUI = new GameGUI(this);
		gameGUI.setVisible(true);
		c.add(gameGUI);

		moved = false;
		controller = c;
	}

	@Override
	public void run() {
		try {
			while(true) {
				Player nowPlayer = playerList.get(playerIdx);
				dice = -1;
				while( dice == -1 ) { Thread.sleep(100); } 
				//invoke
				if(dice != -1){
					gameGUI.offRollingDice();
					gameGUI.onDiceNumber(dice);
				}
				for(int i = 0; i <dice; i++){
					move(nowPlayer);
				}
				gameGUI.offDiceNumber(dice);
				reachGround(nowPlayer);

				if(nowPlayer.getMoney() < 0)
					break;
				
				playerIdx++;
				playerIdx %= numPlayer;
			}
			// @ add game exit message
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(null, playerIdx + "th Player lost all your money and went bankrupt.");
		System.exit(0);

	}

	public void turn() {

	}

	public void reachGround(Player _nowPlayer){
		switch(_nowPlayer.getPosition()) {
			case 8:
				_nowPlayer.lab(); break;
			case 4: case 12:
				JOptionPane.showMessageDialog(null, "Chance acquired!");
				_nowPlayer.increChance(); break; 
			case 0:
				_nowPlayer.inStart(); break;
			default:
				inCity(_nowPlayer);
		}
	}

	public void inCity(Player player){
		int position = player.getPosition();
		int owner = cityManager.owner(position);
		int ID = player.getID();
		String cityName = cityManager.getName(position);
		
		if(owner == -1){
			//@ button for buy or not?
			int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you buy "+cityName+"?");
			if( decision == 0){
				if( player.buyCity(cityManager.getPrice(position)) ){
					cityManager.buyCity(position, ID);
				}
			}
		} else if(owner == ID) {
			//@ button for buy or not?
			int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you build a building on "+cityName+"?");
			if(decision == 0){
				if (  player.buyBuilding(cityManager.getPriceBuilding(position)) ){
					cityManager.buyBuilding(position, ID);
				}	
			}
		} else {
			//@ check chance
			int toll = -1;
			if( player.hasChance() ){
				int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you use chane?");
				player.popChance();
				if( decision == 0){
					int randomChance = new Random().nextInt(2);
					if (randomChance == 0) {  // 50%
						toll = cityManager.getToll(position) / 2;
						JOptionPane.showMessageDialog(null, "you need to pay just 50% toll. Player " + ID + " lose " + toll + " won");
					} else if (randomChance == 1) {	 // 200%
						toll = cityManager.getToll(position) * 2;
						JOptionPane.showMessageDialog(null, "you need to pay 200% toll. Player " + ID + " lose " + toll + " won");
					}
				}
			}
			if (toll == -1) {
				toll = cityManager.getToll(position);
				JOptionPane.showMessageDialog(null, "Player " + ID + " lose " + toll + " won");
			}
			playerList.get(owner).earnMoney(toll);
			player.payToll(toll);
		}
	}

	public void move(Player player){
		int ID = player.getID();
		Point prePoint = pointManager.getPlayerPoint(ID, player.getPosition());
		player.increPosition();
		Point nextPoint = pointManager.getPlayerPoint(ID, player.getPosition());
		for(int i = 0; i < 50; i++){
			Point interPoint = new Point((prePoint.x *(50-i) + nextPoint.x * i)/50, (prePoint.y *(50-i) + nextPoint.y * i)/50 );
			try {
				gameGUI.playerMove(player, interPoint);
				Thread.sleep(10);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		gameGUI.playerMove(player, nextPoint);
	}

	public void rollDice() {
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