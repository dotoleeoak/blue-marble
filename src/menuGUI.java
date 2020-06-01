import java.awt.*;
import javax.swing.*;

public class menuGUI extends JFrame {
    public menuGUI() {
        setTitle("율전 마블");
        setSize(BlueMarble.SCREEN_WIDTH, BlueMarble.SCREEN_HEIGHT);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null); // place window on the center of screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set "menu_background.png" as background
        // http://blog.naver.com/PostView.nhn?blogId=javaking75&logNo=140173265821
        @SuppressWarnings("serial")
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                // Resize image to fit window size
                Dimension d = getSize();
                ImageIcon icon = new ImageIcon("image/menu_background.png");
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
            }
        };
        JScrollPane scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);

        // Add menu buttons (게임 시작, 설정, 나가기)
        ImageIcon icon1 = new ImageIcon("image/button1.png");
        ImageIcon icon2 = new ImageIcon("image/button2.png");
        ImageIcon icon3 = new ImageIcon("image/button3.png");
        JButton button1 = new JButton(icon1);
        JButton button2 = new JButton(icon2);
        JButton button3 = new JButton(icon3);
        button1.setBounds(80, 240, 180, 50);
        button2.setBounds(80, 320, 180, 50);
        button3.setBounds(80, 400, 180, 50);
        add(button1);
        add(button2);
        add(button3);

        setVisible(true);
    }
}
