import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGUI extends JPanel {
    private Image screenImage;
	private Image background = new ImageIcon(Main.class.getResource("images/Board/board.png")).getImage();
	private ImageIcon buildingIcon = new ImageIcon(Main.class.getResource("images/Board/building.png"));
	private Image buildingImages = new ImageIcon(Main.class.getResource("images/Board/building.png")).getImage();
	private ImageIcon[] imagePlayer;

	private JButton rollDiceButton;
	private JLabel rollingDice;
	public JLabel[] playerLabel;
	private JLabel[] diceNumber;

	private boolean readyRolling = false;
	Game game;
	
	CityManager cityManager;
	PointManager coordinateManager;
	int numPlayer;
	ArrayList<Player> playerList;
    
    GameGUI(Game _game){
		game = _game;
		cityManager = game.cityManager;
		coordinateManager = game.coordinateManager;
		numPlayer = game.numPlayer;
		playerList = game.playerList;
		setLayout(null);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBackground(Color.CYAN);
        imagePlayer = new ImageIcon[4];
		for (int i = 0; i < 4; i++) {
			imagePlayer[i] = new ImageIcon(Main.class.getResource("images/Board/player" + i + ".png"));
		}

		diceNumber = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			diceNumber[i] = new JLabel(new ImageIcon(Main.class.getResource("images/Board/dice" + (i + 1) + ".png")));
			diceNumber[i].setBounds(540, 240, 200, 220);
			diceNumber[i].setVisible(false);
			add(diceNumber[i]);
		}

		/********* SHOW PLAYER ICONS *********/
		playerLabel = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			Point point = coordinateManager.getPlayerPoint(i, 0);
			playerLabel[i] = new JLabel(imagePlayer[i]);
			playerLabel[i].setLocation(point.x, point.y);
			playerLabel[i].setSize(30, 30);
			playerLabel[i].setVisible(false);
			add(playerLabel[i]);
		}
		for (int i = 0; i < numPlayer; i++) {
			playerLabel[i].setVisible(true);
		} 

		/* buildingImages = new Image[16][2];
		for (int i = 0; i < 16; i++) {
			for(int j = 0; j < 2; j++){
				Point point = coordinateManager.getBuildingPoint(i,j);
				buildingImages[i][j] = buildingIcon.getImage();
				buildingImages[i][j].setSize(29,44);
				buildingImages[i][j].setLocation(point.x, point.y);
				buildingImages[i][j].setVisible(false);
			}
		add(buildingImages[i][j]); */


		rollDiceButton = new JButton();
		rollDiceButton.setBounds(540, 240, 200, 176);
		rollDiceButton.setBorderPainted(false);
		rollDiceButton.setContentAreaFilled(false);
		rollDiceButton.setFocusPainted(false);
		rollDiceButton.setIcon(new ImageIcon(Main.class.getResource("images/Board/rollDiceButton.png")));
		rollDiceButton.setRolloverIcon(new ImageIcon(Main.class.getResource("images/Board/rollDiceButtonEntered.png")));
		rollDiceButton.setPressedIcon(new ImageIcon(Main.class.getResource("images/Board/rollDiceButtonPressed.png")));
		rollDiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(readyRolling){
					game.rollDice();
				}
			}
		});
		add(rollDiceButton);

		rollingDice = new JLabel();
		rollingDice.setBounds(540, 220, 200, 200);
		rollingDice.setIcon(new ImageIcon(Main.class.getResource("images/rollingDice_3.gif")));
		rollingDice.setVisible(false);
		add(rollingDice);
    }

    @Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenDraw((Graphics2D) screenImage.getGraphics());
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 2; j++){
				if( cityManager.builed(i,j)  ){
					g.drawImage(buildingImages, coordinateManager.getBuildingPoint(i,j).x, coordinateManager.getBuildingPoint(i,j).y,null);
				}
			}
		}
		paintComponents(g);
		writeMoney(g);
		repaint();
    }
    
    public void writeMoney(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		for (int i = 0; i < numPlayer; i++) {
			g.drawString("Player" + i + "'s property: " + playerList.get(i).money, 20 + (Main.SCREEN_WIDTH-400) * (i%2), 40 + (Main.SCREEN_HEIGHT-60) * (i/2));
		}
	}
	public void trueReadyRolling(){
		readyRolling = true;
	}

	public void falseReadyRolling(){
		readyRolling = true;
	}

	public void playerMove(Player _nowPlayer, Point _interPoint){
		JLabel label = playerLabel[_nowPlayer.ID];
		label.setLocation(_interPoint);

	}
}