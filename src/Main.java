import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame{
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	Menu frameMenu;
	Game frameGame;

	Main() {
		setTitle("율전 마블");
		setUndecorated(true);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		/* TODO: Implement menuBar here */

		frameMenu = new Menu(this);
		frameGame = new Game(this);
		frameMenu.setVisible(true);
		frameGame.setVisible(false);
		add(frameMenu);
		add(frameGame);
		frameMenu.repaint();
	}

	public void showGame(int numPlayer) {
		frameMenu.setVisible(false);
		frameGame.setVisible(true);
		frameGame.init(numPlayer);
		// frameGame.startGame();
	}

	public void showMenu() {
		frameGame.setVisible(false);
		frameMenu.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
