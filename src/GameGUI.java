
/*
Work of each team member
Park Jaesung(2019314505) : write writeMoney(), paint() and screenDraw() for drawing. prototype of button and image
Choi jaemin(2019314992) : Implement player moving animation
Seo Kangmin(2019310155) : Multithreading with Game, improved code readability, removed unnecessary functions
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameGUI extends JPanel {
	private Image screenImage;
	private Image background = new ImageIcon(Main.class.getResource("images/Board/board.png")).getImage();
	private Image buildImage = new ImageIcon(Main.class.getResource("images/Board/building.png")).getImage();
	private Image flag0Image = new ImageIcon(Main.class.getResource("images/Board/flag0.png")).getImage();
	private Image flag1Image = new ImageIcon(Main.class.getResource("images/Board/flag1.png")).getImage();
	private Image flag2Image = new ImageIcon(Main.class.getResource("images/Board/flag2.png")).getImage();
	private Image flag3Image = new ImageIcon(Main.class.getResource("images/Board/flag3.png")).getImage();
	private Image rollingDice = new ImageIcon(Main.class.getResource("images/rollingDice_3.gif")).getImage();
	private ImageIcon[] imagePlayer;

	private JButton closeButton;
	private JButton rollDiceButton;
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));

	// private JLabel rollingDice;
	public JLabel[] playerLabel;
	private JLabel[] diceNumber;

	public boolean rollDice = false;
	Game game;

	CityManager cityManager;
	PointManager pointManager;
	int numPlayer;
	ArrayList<Player> playerList;
	private int mouseX, mouseY;

	GameGUI(Game _game) {
		game = _game;
		cityManager = game.cityManager;
		pointManager = game.pointManager;
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
			Point point = pointManager.getPlayerPoint(i, 0);
			playerLabel[i] = new JLabel(imagePlayer[i]);
			playerLabel[i].setLocation(point.x, point.y);
			playerLabel[i].setSize(30, 30);
			playerLabel[i].setVisible(false);
			add(playerLabel[i]);
		}
		for (int i = 0; i < numPlayer; i++) {
			playerLabel[i].setVisible(true);
		}

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
				new Thread(new Runnable() {
					public void run() {
						onRollingDice();
						try {
							Thread.sleep(600);
						} catch (Exception e) {
							e.printStackTrace();
						}
						game.rollDice();
					}
				}).start();
			}
		});
		add(rollDiceButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		setComponentZOrder(menuBar, 1);

		closeButton = new JButton();
		closeButton.setBounds(1245, 0, 30, 30);
		closeButton.setBorderPainted(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setIcon(new ImageIcon(Main.class.getResource("images/MainMenu/closeButton.png")));
		closeButton.setRolloverIcon(new ImageIcon(Main.class.getResource("images/MainMenu/closeButtonEntered.png")));
		closeButton.setPressedIcon(new ImageIcon(Main.class.getResource("images/MainMenu/closeButtonPressed.png")));
		closeButton.addMouseListener(new soundingMouseAdapter(closeButton));
		add(closeButton);
		setComponentZOrder(closeButton, 0);

		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
	}

	public void onRollingDice() {
		rollDice = true;
		rollDiceButton.setVisible(false);
	}

	public void offRollingDice() {
		rollDice = false;
	}

	public void onDiceNumber(int _diceNum) {
		diceNumber[_diceNum - 1].setVisible(true);

	}

	public void offDiceNumber(int _diceNum) {
		diceNumber[_diceNum - 1].setVisible(false);
		rollDiceButton.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenDraw((Graphics2D) screenImage.getGraphics());
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 2; j++) {
				if (cityManager.builded(i, j)) {
					g.drawImage(buildImage, pointManager.getBuildingPoint(i, j).x,
							pointManager.getBuildingPoint(i, j).y, null);
				}
			}
		}
		for (int i = 0; i < 16; i++) {
			if (cityManager.owner(i) == 0) {
				g.drawImage(flag0Image, pointManager.getflagPoint(i).x, pointManager.getflagPoint(i).y, null);
			} else if (cityManager.owner(i) == 1) {
				g.drawImage(flag1Image, pointManager.getflagPoint(i).x, pointManager.getflagPoint(i).y, null);
			} else if (cityManager.owner(i) == 2) {
				g.drawImage(flag2Image, pointManager.getflagPoint(i).x, pointManager.getflagPoint(i).y, null);
			} else if (cityManager.owner(i) == 3) {
				g.drawImage(flag3Image, pointManager.getflagPoint(i).x, pointManager.getflagPoint(i).y, null);
			}
		}
		if (rollDice)
			g.drawImage(rollingDice, 540, 220, null);
		paintComponents(g);
		writeMoney(g);
		repaint();
	}

	public void writeMoney(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		for (int i = 0; i < numPlayer; i++) {
			g.drawString("Player #" + i + "'s balance: " + playerList.get(i).getMoney(),
					20 + (Main.SCREEN_WIDTH - 400) * (i % 2), 60 + (Main.SCREEN_HEIGHT - 120) * (i / 2));
			g.drawString("Chance: " + playerList.get(i).getChance(), 20 + (Main.SCREEN_WIDTH - 400) * (i % 2),
					90 + (Main.SCREEN_HEIGHT - 120) * (i / 2));
		}
	}

	public void playerMove(Player _nowPlayer, Point _interPoint) {
		JLabel label = playerLabel[_nowPlayer.getID()];
		label.setLocation(_interPoint);

	}
}