
/*
Work of each team member
Park Jaesung(2019314505) : make prototype of button, button action, image and imageicon. and write paint() and screenDraw().
Choi jaemin(2019314992) : Apply MVC structure & Make code efficient (delete repetition)
Seo Kangmin(2019310155) : Reduced repeated code for button setting, interaction with Main controller
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	private Image background = new ImageIcon(Main.class.getResource("images/introBackground.png")).getImage();
	private Image selectPanelBackgroundImage = new ImageIcon(Main.class.getResource("images/selectPanelBackground.png"))
			.getImage();
	private Image selectPanelImage = new ImageIcon(Main.class.getResource("images/MainMenu/selectPanel.png"))
			.getImage();
	private Image ruleImage = new ImageIcon(Main.class.getResource("images/MainMenu/rulePanel.png")).getImage();

	enum Button {
		CLOSE, START, RULE, QUIT;
	}

	private JButton[] menuButtons;
	private JButton[] numPlayerButtons;
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));

	Music backgroundMusic;

	private int mouseX, mouseY;
	private boolean isSelecting;
	private boolean rulePopUp = false;

	Main controller;

	Menu(Main c) {
		setLayout(null);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBackground(Color.CYAN);

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
		Image screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenDraw((Graphics2D) screenImage.getGraphics());
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (rulePopUp) {
			g.drawImage(ruleImage, 540, 25, null);
		}
		if (isSelecting) {
			g.drawImage(selectPanelBackgroundImage, 0, 30, null);
			g.drawImage(selectPanelImage, 440, 260, null);
		}

		paintComponents(g);
		repaint();
	}

	public void enterIntro() {
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
			button.setRolloverIcon(
					new ImageIcon(Main.class.getResource("images/MainMenu/" + button.getName() + "ButtonEntered.png")));
			button.setPressedIcon(
					new ImageIcon(Main.class.getResource("images/MainMenu/" + button.getName() + "ButtonPressed.png")));
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
				rulePopUp = !rulePopUp;
			}
		});

		class closeActionListener implements ActionListener {
			// Action for CLOSE and QUIT button
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		}
		;

		menuButtons[Button.CLOSE.ordinal()].addActionListener(new closeActionListener());
		menuButtons[Button.QUIT.ordinal()].addActionListener(new closeActionListener());

		/******************** ADD menuBar ********************/
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

		if (backgroundMusic != null)
			backgroundMusic.close();
		backgroundMusic = new Music("introMusicNew.mp3", true);
		backgroundMusic.start();
	}

	public void selectPlayer() {
		rulePopUp = false;
		isSelecting = true;
		for (JButton button : menuButtons) {
			if (!button.getName().equals("close")) {
				button.setVisible(false);
			}
		}

		for (int i = 0; i < 3; i++) {
			int numPlayer = i + 2;
			JButton button = numPlayerButtons[i];
			ImageIcon image = new ImageIcon(Main.class.getResource("images/MainMenu/number" + numPlayer + ".png"));
			ImageIcon imageEntered = new ImageIcon(
					Main.class.getResource("images/MainMenu/number" + numPlayer + "entered.png"));
			ImageIcon imagePressed = new ImageIcon(
					Main.class.getResource("images/MainMenu/number" + numPlayer + "pressed.png"));

			button.setBounds(480 + 120 * i, 360, 80, 80);
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
					controller.startGame(numPlayer);
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

	soundingMouseAdapter(JButton button) {
		this.button = button;
	}
}
