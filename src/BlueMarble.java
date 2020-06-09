import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BlueMarble extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image background;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("images/quitButtonBasic.png"));
	private ImageIcon ruleButtonEnteredImage = new ImageIcon(Main.class.getResource("images/ruleButtonEntered.png"));
	private ImageIcon ruleButtonBasicImage = new ImageIcon(Main.class.getResource("images/ruleButtonBasic.png"));

	private Image selectPanelBackgroundImage = new ImageIcon(Main.class.getResource("images/selectPanelBackground.png"))
			.getImage();
	private Image selectPanelImage = new ImageIcon(Main.class.getResource("images/selectPanel.png")).getImage();
	private ImageIcon number2BasicImage = new ImageIcon(Main.class.getResource("images/number2.png"));
	private ImageIcon number3BasicImage = new ImageIcon(Main.class.getResource("images/number3.png"));
	private ImageIcon number4BasicImage = new ImageIcon(Main.class.getResource("images/number4.png"));

	private Image boardImage = new ImageIcon(Main.class.getResource("images/boardGroup.png")).getImage();

	private Image charaterBackgroundImage_0 = new ImageIcon(Main.class.getResource("images/charaterBackground.png"))
			.getImage();
	private Image charaterBackgroundImage_1 = new ImageIcon(Main.class.getResource("images/charaterBackground.png"))
			.getImage();
	private Image charaterBackgroundImage_2 = new ImageIcon(Main.class.getResource("images/charaterBackground.png"))
			.getImage();
	private Image charaterBackgroundImage_3 = new ImageIcon(Main.class.getResource("images/charaterBackground.png"))
			.getImage();

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton ruleButton = new JButton(ruleButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);

	private JButton number2Button = new JButton(number2BasicImage);
	private JButton number3Button = new JButton(number3BasicImage);
	private JButton number4Button = new JButton(number4BasicImage);

	Music backgroundMusic;

	private int mouseX, mouseY;

	private boolean isSelecting = false;
	private boolean isGaming = false;

	int numPlayer;

	public static Game game;

	BlueMarble() {

		setUndecorated(true);
		setTitle("율전 마블");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		enterIntro();
	}

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);

		if (isSelecting) {
			g.drawImage(selectPanelBackgroundImage, 0, 0, null);
			g.drawImage(selectPanelImage, 400, 260, null);
		}
		if (isGaming) {
			g.drawImage(boardImage, 154, 26, null);
			game.screenDraw(g);
			switch (numPlayer) {
				case 4:
					g.drawImage(charaterBackgroundImage_3, Main.SCREEN_WIDTH - 350, Main.SCREEN_HEIGHT - 100, null);
				case 3:
					g.drawImage(charaterBackgroundImage_2, 0, Main.SCREEN_HEIGHT - 100, null);
				case 2:
					g.drawImage(charaterBackgroundImage_1, Main.SCREEN_WIDTH - 350, 30, null);
					g.drawImage(charaterBackgroundImage_0, 0, 30, null);
			}
		}
		paintComponents(g);
		this.repaint();
	}

	public void enterIntro() {
		if (backgroundMusic != null)
			backgroundMusic.close();
		backgroundMusic = new Music("introMusic.mp3", true);
		backgroundMusic.start();

		background = new ImageIcon(Main.class.getResource("images/introBackground.png")).getImage();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(100, 350, 350, 85);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectPlayer();
			}
		});
		add(startButton);

		ruleButton.setBounds(100, 450, 350, 85);
		ruleButton.setBorderPainted(false);
		ruleButton.setContentAreaFilled(false);
		ruleButton.setFocusPainted(false);
		ruleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ruleButton.setIcon(ruleButtonEnteredImage);
				ruleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ruleButton.setIcon(ruleButtonBasicImage);
				ruleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
			}
		});
		add(ruleButton);

		quitButton.setBounds(100, 550, 350, 85);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

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
	}

	public void selectPlayer() {
		isSelecting = true;
		startButton.setVisible(false);
		quitButton.setVisible(false);
		ruleButton.setVisible(false);

		number2Button.setBounds(460, 360, 80, 80);
		number2Button.setBorderPainted(false);
		number2Button.setContentAreaFilled(false);
		number2Button.setFocusPainted(false);
		number2Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				number2Button.setIcon(number2BasicImage);
				number2Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				number2Button.setIcon(number2BasicImage);
				number2Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(2);
			}
		});
		add(number2Button);

		number3Button.setBounds(610, 360, 80, 80);
		number3Button.setBorderPainted(false);
		number3Button.setContentAreaFilled(false);
		number3Button.setFocusPainted(false);
		number3Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				number3Button.setIcon(number3BasicImage);
				number3Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				number3Button.setIcon(number3BasicImage);
				number3Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(3);
			}
		});
		add(number3Button);

		number4Button.setBounds(760, 360, 80, 80);
		number4Button.setBorderPainted(false);
		number4Button.setContentAreaFilled(false);
		number4Button.setFocusPainted(false);
		number4Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				number4Button.setIcon(number4BasicImage);
				number4Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				number4Button.setIcon(number4BasicImage);
				number4Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(4);
			}
		});
		add(number4Button);

		// gameStart();
	}

	public void gameStart(int numPlayer) {
		this.numPlayer = numPlayer;
		isSelecting = false;
		isGaming = true;
		number2Button.setVisible(false);
		number3Button.setVisible(false);
		number4Button.setVisible(false);
		background = new ImageIcon(Main.class.getResource("images/background.png")).getImage();
		game = new Game(numPlayer);
		game.start();
	}

}
