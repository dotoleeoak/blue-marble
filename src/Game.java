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
				while( dice == -1 ){
					Thread.sleep(500);
				} //invoke
				if(dice != -1){
					gameGUI.offRollingDice();
					gameGUI.onDiceNumber(dice);
				}
				for(int i = 0; i <dice; i++){
					move(nowPlayer);
				}
				gameGUI.offDiceNumber(dice);

				if (reachGround(nowPlayer) == false){
					gamestep = -1;
				}
				
				playerIdx++;
				playerIdx %= numPlayer;
			}while(gamestep != -1);
			// @ add game exit message
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean reachGround(Player _nowPlayer){
		if(_nowPlayer.position == 8){
			inLab(_nowPlayer);
			return true;
		}else if(_nowPlayer.position == 4 || _nowPlayer.position == 12){
			inChance(_nowPlayer);
			return true;
		}else if(_nowPlayer.position == 0){
			inStart(_nowPlayer);
			return true;
		}else{
			return inCity(_nowPlayer, cityManager);
		}
	}

	public void inLab(Player _nowPlayer){
		_nowPlayer.lab();
	}

	public void inChance(Player _nowPlayer){
		_nowPlayer.getChance();
	}
	public void inStart(Player _nowPlayer){
		_nowPlayer.inStart();
	}
	public boolean inCity(Player _nowPlayer, CityManager _cityManager){
		
		String cityName = _cityManager.getName(_nowPlayer.position);
		if(_cityManager.owner(_nowPlayer.position) == -1){
			//@ button for buy or not?
			int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you buy "+cityName+"?");
			if( decision == 0){
				if( _nowPlayer.buyCity(_cityManager.getPrice(_nowPlayer.position)) ){
					cityManager.buyCity(_nowPlayer.position, _nowPlayer.ID);
				}
			}
		}else{
			//@ check chance
			if( _nowPlayer.numChance > 0 ){
				int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you use chane?");
				_nowPlayer.numChance--;
				if( decision == 0){
					int randomChance = new Random().nextInt(2);
					if(randomChance == 0){ // 50%
						JOptionPane.showMessageDialog(null, "you need to pay just 50% toll. Player " + _nowPlayer.ID + " lose " + _cityManager.getToll(_nowPlayer.position)/2 + " won");
						//owner earn 
						playerList.get(_cityManager.owner(_nowPlayer.position)).earnMoney( _cityManager.getToll(_nowPlayer.position)/2 );
						//mover paid
						return _nowPlayer.payToll(_cityManager.getToll(_nowPlayer.position)/2);
					}else{	//200%
						JOptionPane.showMessageDialog(null, "you need to pay just 200% toll. Player " + _nowPlayer.ID + " lose " + _cityManager.getToll(_nowPlayer.position)*2 + " won");
						//owner earn 
						playerList.get(_cityManager.owner(_nowPlayer.position)).earnMoney( _cityManager.getToll(_nowPlayer.position)*2 );
						//mover paid
						return _nowPlayer.payToll(_cityManager.getToll(_nowPlayer.position)*2);
					}
				}
			}
			//default
			JOptionPane.showMessageDialog(null, "Player " + _nowPlayer.ID + " lose " + _cityManager.getToll(_nowPlayer.position) + " won");
			//owner earn 
			playerList.get(_cityManager.owner(_nowPlayer.position)).earnMoney( _cityManager.getToll(_nowPlayer.position) );
			//mover paid
			return _nowPlayer.payToll(_cityManager.getToll(_nowPlayer.position));
		}

		if( _cityManager.owner(_nowPlayer.position) == _nowPlayer.ID ){
			//@ button for buy or not?
			int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you build a building on "+cityName+"?");
			if(decision == 0){
				if (  _nowPlayer.buyBuilding(_cityManager.getPriceBuilding(_nowPlayer.position)) ){
					cityManager.buyBuilding(_nowPlayer.position, _nowPlayer.ID);
				}	
			}
		}
		return true;
	}

	public void move(Player _nowPlayer){
		Point prePoint = coordinateManager.getPlayerPoint(_nowPlayer.ID, _nowPlayer.position);
		_nowPlayer.position++;
		_nowPlayer.position %= 16;
		Point nextPoint = coordinateManager.getPlayerPoint(_nowPlayer.ID, _nowPlayer.position);
		for(int i = 0; i < 50; i++){
			Point interPoint = new Point((prePoint.x *(50-i) + nextPoint.x * i)/50, (prePoint.y *(50-i) + nextPoint.y * i)/50 );
			try {
				gameGUI.playerMove(_nowPlayer, interPoint);
				Thread.sleep(10);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		gameGUI.playerMove(_nowPlayer, nextPoint);
	}

	public void rollDice() {
		dice = new Random().nextInt(6) + 1;
	}

	public void rollOffDice(){
		try{
			gameGUI.onDiceNumber(dice);
			Thread.sleep(1000);
			gameGUI.offDiceNumber(dice);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void close() {
		this.interrupt();
	}

	
	/*
	 * Every time rollDiceButton is pressed, call this method. This method defines
	 * things to do in each turn.
	 */
	

}
