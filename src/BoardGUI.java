import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BoardGUI extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image Background;
	
	public BoardGUI(){
		setTitle("Blue Marble");
		setSize(BlueMarble.SCREEM_WIDTH, BlueMarble.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		Background = new ImageIcon(BlueMarble.class.getResource("../../images/background.jpg").getImage();
	}
	
	
	public void paint(Graphics g) {
		screenImage = createImage(BlueMarble.SCREEM_WIDTH, BlueMarble.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage,0,0,null)
	}
	
	

	
}
