import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame{
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
		if(game != null){
			game.close();
		}
		frameMenu.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
