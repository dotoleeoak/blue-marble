import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class BoardGUI extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image Background;
	
	public BoardGUI(){
		
		setTitle("Blue Marble");
		setLayout(null);
		setSize(BlueMarble.SCREEM_WIDTH, BlueMarble.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			File sourceimage = new File("images/background.jpg");
			Background = ImageIO.read(sourceimage);
		}catch(Exception e) {
			
		}
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("aa");
		label.setSize(100,100);
		label.setLocation(0,0);
		panel.add(label);
		this.add(panel);
		
		
		
		PlayerGUI a = new PlayerGUI("first", 0, 0,0);
		
		Container c = getContentPane();
		c.add(a);
		
		setVisible(true);
		
		
	}
	
	
	public void paint(Graphics g) {
		screenImage = createImage(BlueMarble.SCREEM_WIDTH, BlueMarble.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage,0,0,null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null);
		this.repaint();
	}
	
	

	
}
