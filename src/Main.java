
/*
Work of each team member
Park Jaesung(2019314505) : setting SCREEN_WIDTH and SCREEN_HEIGHT.
Choi jaemin(2019314992) : Apply MVC structure (-> Implement Controller logic)
Seo Kangmin(2019310155) : separated Menu panel and GameGUI panel, improved Panel transition
*/
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	Menu frameMenu;
	Game game;
	GameGUI gameGUI;

	Main() {
		setTitle("marble");
		setUndecorated(true);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		/* TODO: Implement menuBar here */
		frameMenu = new Menu(this);
		frameMenu.setVisible(true);
		add(frameMenu);
		frameMenu.repaint();
	}

	public void startGame(int numPlayer) {
		frameMenu.setVisible(false);
		game = new Game(this, numPlayer);
		game.start();
	}

	public void showMenu() {
		if (game != null) {
			game.close();
		}
		frameMenu.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
