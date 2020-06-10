import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JFrame {

	private int mouseX, mouseY;

	private int numPlayer;
	private Image screenImage;
	private Graphics screenGraphic;

	private Image 		background						= new ImageIcon(Main.class.getResource("images/background.png")).getImage();
	private ImageIcon exitButtonBasicImage    	= new ImageIcon(Main.class.getResource("images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage  	= new ImageIcon(Main.class.getResource("images/exitButtonEntered.png"));
	private Image 		rollDiceButtonImage 			= new ImageIcon(Main.class.getResource("images/rollDiceButton.png")).getImage();
	private Image 		boardImage 						= new ImageIcon(Main.class.getResource("images/boardGroup.png")).getImage();
	private Image 		charaterBackgroundImage_0 	= new ImageIcon(Main.class.getResource("images/charaterBackground.png")).getImage();
	private Image 		charaterBackgroundImage_1 	= new ImageIcon(Main.class.getResource("images/charaterBackground.png")).getImage();
	private Image 		charaterBackgroundImage_2 	= new ImageIcon(Main.class.getResource("images/charaterBackground.png")).getImage();
	private Image 		charaterBackgroundImage_3 	= new ImageIcon(Main.class.getResource("images/charaterBackground.png")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));
	private JButton exitButton  = new JButton(exitButtonBasicImage);

	public Game(int numPlayer) {
		this.numPlayer = numPlayer;

		setUndecorated(true);
		setTitle("���� ����");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setRolloverIcon(exitButtonEnteredImage);
		exitButton.addMouseListener(BlueMarble.createMouseAdapter(exitButton));
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

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(boardImage, 154, 26, null);

		switch (numPlayer) {
			case 4:
				g.drawImage(charaterBackgroundImage_3, Main.SCREEN_WIDTH - 350, Main.SCREEN_HEIGHT - 100, null);
			case 3:
				g.drawImage(charaterBackgroundImage_2, 0, Main.SCREEN_HEIGHT - 100, null);
			case 2:
				g.drawImage(charaterBackgroundImage_1, Main.SCREEN_WIDTH - 350, 30, null);
				g.drawImage(charaterBackgroundImage_0, 0, 30, null);
		}

		paintComponents(g);
		this.repaint();
	}
}
