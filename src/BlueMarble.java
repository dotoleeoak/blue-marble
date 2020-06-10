import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BlueMarble extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image background;

	private ImageIcon exitButtonBasicImage    = new ImageIcon(Main.class.getResource("images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage  = new ImageIcon(Main.class.getResource("images/exitButtonEntered.png"));
	private ImageIcon startButtonBasicImage   = new ImageIcon(Main.class.getResource("images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("images/startButtonEntered.png"));
	private ImageIcon startButtonPressedImage = new ImageIcon(Main.class.getResource("images/startButtonPressed.png"));
	private ImageIcon ruleButtonBasicImage    = new ImageIcon(Main.class.getResource("images/ruleButtonBasic.png"));
	private ImageIcon ruleButtonEnteredImage  = new ImageIcon(Main.class.getResource("images/ruleButtonEntered.png"));
	private ImageIcon ruleButtonPressedImage  = new ImageIcon(Main.class.getResource("images/ruleButtonPressed.png"));
	private ImageIcon quitButtonBasicImage    = new ImageIcon(Main.class.getResource("images/quitButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage  = new ImageIcon(Main.class.getResource("images/quitButtonEntered.png"));
	private ImageIcon quitButtonPressedImage  = new ImageIcon(Main.class.getResource("images/quitButtonPressed.png"));

	private Image selectPanelBackgroundImage = new ImageIcon(Main.class.getResource("images/selectPanelBackground.png")).getImage();
	private Image selectPanelImage           = new ImageIcon(Main.class.getResource("images/selectPanel.png")).getImage();
	private ImageIcon[] numberImage          = new ImageIcon[3];
	private ImageIcon[] numberImageEntered   = new ImageIcon[3];
	private ImageIcon[] numberImagePressed   = new ImageIcon[3];

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));

	private JButton exitButton  = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton ruleButton  = new JButton(ruleButtonBasicImage);
	private JButton quitButton  = new JButton(quitButtonBasicImage);

	private JButton[] numberButton = new JButton[3];

	Music backgroundMusic;

	private int mouseX, mouseY;

	private boolean isSelecting = false;

	int numPlayer;

	public Game game;

	BlueMarble() {
		setUndecorated(true);
		setTitle("���� ����");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		for (int i = 0; i < 3; i++) {
			numberImage       [i] = new ImageIcon(Main.class.getResource("images/MainMenu/number" + (i + 2) + ".png"));
			numberImageEntered[i] = new ImageIcon(Main.class.getResource("images/MainMenu/number" + (i + 2) + "entered.png"));
			numberImagePressed[i] = new ImageIcon(Main.class.getResource("images/MainMenu/number" + (i + 2) + "pressed.png"));
			numberButton      [i] = new JButton(numberImage[i]);
		}
		
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
		paintComponents(g);
		this.repaint();
	}

	public static MouseAdapter createMouseAdapter(JButton button) {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
			}
		};
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
		exitButton.setRolloverIcon(exitButtonEnteredImage);
		exitButton.addMouseListener(createMouseAdapter(exitButton));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					Thread.sleep(300);
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
		startButton.setRolloverIcon(startButtonEnteredImage);
		startButton.setPressedIcon(startButtonPressedImage);
		startButton.addMouseListener(createMouseAdapter(startButton));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				selectPlayer();
			}
		});
		add(startButton);

		ruleButton.setBounds(100, 450, 350, 85);
		ruleButton.setBorderPainted(false);
		ruleButton.setContentAreaFilled(false);
		ruleButton.setFocusPainted(false);
		ruleButton.setRolloverIcon(ruleButtonEnteredImage);
		ruleButton.setPressedIcon(ruleButtonPressedImage);
		ruleButton.addMouseListener(createMouseAdapter(ruleButton));
		ruleButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// WIP
			}
		});
		add(ruleButton);

		quitButton.setBounds(100, 550, 350, 85);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.setRolloverIcon(quitButtonEnteredImage);
		quitButton.setPressedIcon(quitButtonPressedImage);
		quitButton.addMouseListener(createMouseAdapter(quitButton));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					Thread.sleep(300);
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

		numberButton[0].setBounds(460, 360, 80, 80);
		numberButton[1].setBounds(610, 360, 80, 80);
		numberButton[2].setBounds(760, 360, 80, 80);

		for (int i = 0; i < 3; i++) {
			int       numPlayer    = i + 2;
			JButton   button       = numberButton[i];
			ImageIcon image        = numberImage[i];
			ImageIcon imageEntered = numberImageEntered[i];
			ImageIcon imagePressed = numberImagePressed[i];

			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setIcon(image);
			button.setRolloverIcon(imageEntered);
			button.setPressedIcon(imagePressed);
			button.addMouseListener(createMouseAdapter(button));
			button.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					gameStart(numPlayer);
				}
			});
			add(button);
		}
	}

	public void gameStart(int numPlayer) {
		this.setVisible(false);
		new Game(numPlayer).setVisible(true);
	}
}
