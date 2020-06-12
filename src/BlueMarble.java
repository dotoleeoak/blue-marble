import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BlueMarble extends JFrame {

	private Image screenImage;
	private Image background;
	private Image selectPanelBackgroundImage = new ImageIcon(Main.class.getResource("images/selectPanelBackground.png")).getImage();
	private Image selectPanelImage           = new ImageIcon(Main.class.getResource("images/MainMenu/selectPanel.png")).getImage();

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));

	enum Button { CLOSE, START, RULE, QUIT; }
	private JButton[] menuButtons;
	private JButton[] numPlayerButtons;

	Music backgroundMusic;

	private int mouseX, mouseY;
	private boolean isSelecting;

	Controller controller;

	BlueMarble(Controller c) {
		setUndecorated(true);
		setTitle("율전 마블");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		menuButtons = new JButton[4];
		for (int i = 0; i < 4; i++) {
			menuButtons[i] = new JButton();
		}
		menuButtons[Button.CLOSE.ordinal()].setName("close");
		menuButtons[Button.START.ordinal()].setName("start");
		menuButtons[Button.RULE.ordinal()].setName("rule");
		menuButtons[Button.QUIT.ordinal()].setName("quit");
		
		numPlayerButtons = new JButton[3];
		for (int i = 0; i < 3; i++) {
			numPlayerButtons[i] = new JButton();
		}
		
		isSelecting = false;
		controller = c;
		
		enterIntro();
	}

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenDraw((Graphics2D) screenImage.getGraphics());
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);

		if (isSelecting) {
			for (JButton button : menuButtons) {
				if (!button.getName().equals("close")) {
					button.setVisible(false);
				}
			}
			g.drawImage(selectPanelBackgroundImage, 0, 0, null);
			g.drawImage(selectPanelImage, 400, 260, null);
		}
		// if (isGaming) {
		// 	g.drawImage(boardImage, 0, 0, null);
		// 	game.screenDraw(g);
		// 	switch (numPlayer) {
		// 		case 4:
		// 			g.drawImage(charaterBackgroundImage_3, Main.SCREEN_WIDTH - 350, Main.SCREEN_HEIGHT - 100, null);
		// 		case 3:
		// 			g.drawImage(charaterBackgroundImage_2, 0, Main.SCREEN_HEIGHT - 100, null);
		// 		case 2:
		// 			g.drawImage(charaterBackgroundImage_1, Main.SCREEN_WIDTH - 350, 30, null);
		// 			g.drawImage(charaterBackgroundImage_0, 0, 30, null);
		// 	}
		// }
		paintComponents(g);
		this.repaint();
	}

	public void enterIntro() {
		if (backgroundMusic != null)
			backgroundMusic.close();
		backgroundMusic = new Music("introMusicNew.mp3", true);
		backgroundMusic.start();

		background = new ImageIcon(Main.class.getResource("images/introBackground.png")).getImage();

		/************* ADD BUTTONS (CLOSE, START, RULE, QUIT) *************/
		menuButtons[Button.CLOSE.ordinal()].setBounds(1245, 0, 30, 30);
		menuButtons[Button.START.ordinal()].setBounds(135, 350, 235, 75);
		menuButtons[Button.RULE.ordinal()].setBounds(135, 450, 235, 75);
		menuButtons[Button.QUIT.ordinal()].setBounds(135, 550, 235, 75);

		for (JButton button : menuButtons) {
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setIcon(new ImageIcon(Main.class.getResource("images/MainMenu/" + button.getName() + "Button.png")));
			button.setRolloverIcon(new ImageIcon(Main.class.getResource("images/MainMenu/" + button.getName() + "ButtonEntered.png")));
			button.setPressedIcon(new ImageIcon(Main.class.getResource("images/MainMenu/" + button.getName() + "ButtonPressed.png")));
			button.addMouseListener(new soundingMouseAdapter(button));
			add(button);
		}

		menuButtons[Button.START.ordinal()].addActionListener(new ActionListener() {
			// Set action of START button
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPlayer();
			}
		});

		menuButtons[Button.RULE.ordinal()].addActionListener(new ActionListener() {
			// Set action of RULE button
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show rules
			}
		});

		ActionListener close = new ActionListener() {
			// Save closing action for CLOSE and QUIT button
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(300);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		};

		menuButtons[Button.CLOSE.ordinal()].addActionListener(close);
		menuButtons[Button.QUIT.ordinal()].addActionListener(close);

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

		for (int i = 0; i < 3; i++) {
			int       numPlayer    = i + 2;
			JButton   button       = numPlayerButtons[i];
			ImageIcon image        = new ImageIcon(Main.class.getResource("images/MainMenu/number" + numPlayer + ".png"));
			ImageIcon imageEntered = new ImageIcon(Main.class.getResource("images/MainMenu/number" + numPlayer + "entered.png"));
			ImageIcon imagePressed = new ImageIcon(Main.class.getResource("images/MainMenu/number" + numPlayer + "pressed.png"));

			button.setBounds(460 + 140 * i, 360, 80, 80);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setIcon(image);
			button.setRolloverIcon(imageEntered);
			button.setPressedIcon(imagePressed);
			button.addMouseListener(new soundingMouseAdapter(button));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					isSelecting = false;
					controller.numPlayer = numPlayer;
					controller.showGame();
				}
			});
			add(button);
		}
	}
}


class soundingMouseAdapter extends MouseAdapter {
	JButton button;

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

	soundingMouseAdapter(JButton _button) {
		button = _button;
	}
}
