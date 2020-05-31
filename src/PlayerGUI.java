import java.awt.*;
import javax.swing.*;


public class PlayerGUI extends JPanel {

	public String name;
	public int id;
	public int have_money;
	
	public JLabel labelNameL;
	public JLabel labelMoneyL;
	public JLabel labelNameR;
	public JLabel labelMoneyR;
	
	public PlayerGUI(String _name, int id, int _x, int _y) {
		
		setLayout(new GridLayout(2,2));
		setVisible(true);
		setSize(300,100);
		setLocation(_x, _y);
		this.setBackground(Color.WHITE);
		
		labelNameL = new JLabel("�̸�");
		labelMoneyL = new JLabel("������");
		labelNameR = new JLabel("�̸�R");
		labelMoneyR = new JLabel("������R");
		
		this.add(labelNameL);
		this.add(labelNameR);
		this.add(labelMoneyL);
		this.add(labelMoneyR);
	}

}