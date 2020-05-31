import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class BoardGUI2 extends JFrame{
	
	String id = "test";
	String password = "12345678";
	JTextField textField1;
	JPasswordField textField2;
	
	public BoardGUI2(){
		
		setTitle("Blue Marble");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
		setSize(BlueMarble.SCREEM_WIDTH, BlueMarble.SCREEN_HEIGHT);
		setResizable(false);
		
		JButton login_b = new JButton("login");
		login_b.setActionCommand("Login");
		login_b.addActionListener(new ButtonClickListener());
		login_b.setSize(240,20);
		
		JPanel panel_id = new JPanel();
		panel_id.setLayout(new FlowLayout(FlowLayout.LEFT,10, 5));
		JPanel panel_password = new JPanel();
		panel_password.setLayout(new FlowLayout(FlowLayout.LEFT,10, 5));
		
		JLabel label_id = new JLabel("Id");
		label_id.setPreferredSize(new Dimension(80,10));
		JLabel label_password = new JLabel("Password");
		label_password.setPreferredSize(new Dimension(80,10));
		
		textField1 = new JTextField();
		textField1.setPreferredSize(new Dimension(160,20));
		textField2 = new JPasswordField();
		textField2.setPreferredSize(new Dimension(160,20));
		
		panel_id.add(label_id);
		panel_id.add(textField1);
		panel_password.add(label_password);
		panel_password.add(textField2);
		
		Container c = getContentPane();
		c.add(panel_id);
		c.add(panel_password);
		c.add(login_b);
		
		pack();	
		
		
		setVisible(true);
		
		
	}
	
	
	class ButtonClickListener implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			String tempId = textField1.getText();
			String tempPassword = textField2.getText();
			
			if(tempId.contentEquals(id) && tempPassword.contentEquals(password) ) {
				JOptionPane.showMessageDialog(null, "Succss");
			}else {
				JOptionPane.showMessageDialog(null, "Fail");
			}
		}
	}
	

	
}
