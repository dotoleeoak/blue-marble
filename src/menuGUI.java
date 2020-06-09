import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class menuGUI extends JFrame {
    public menuGUI() {
        setTitle("율전 마블");
        setSize(BlueMarble.SCREEN_WIDTH, BlueMarble.SCREEN_HEIGHT);
        // setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null); // place window on the center of screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set "menu_background.png" as background
        // http://blog.naver.com/PostView.nhn?blogId=javaking75&logNo=140173265821
        JPanel background = new JPanel(new FlowLayout()) {
            public void paintComponent(Graphics g) {
                // Resize image to fit window size
                Dimension d = getSize();
                ImageIcon icon = new ImageIcon("images/menu_background.png");
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
            }
        };

        // Add menu buttons (게임 시작, 설정, 나가기)
        ImageIcon icon1 = new ImageIcon("images/button1.png");
        ImageIcon icon2 = new ImageIcon("images/button2.png");
        ImageIcon icon3 = new ImageIcon("images/button3.png");
        JLabel button1 = new JLabel(icon1);
        JLabel button2 = new JLabel(icon2);
        JLabel button3 = new JLabel(icon3);
        button1.setBounds(80, 240, 240, 80);
        button2.setBounds(80, 320, 180, 50);
        button3.setBounds(80, 400, 180, 50);
        background.add(button1);
        background.add(button2);
        background.add(button3);
        add(background);

        // JScrollPane scrollPane = new JScrollPane(background);
        // setContentPane(scrollPane);
        // button1.setOpaque(false);
        // button1.setContentAreaFilled(false);
        // button1.setMargin(new Insets(0, 0, 0, 0));

        // button1.setBorder(null);
        // button1.setBorderPainted(false);
        // button1.setMargin(new Insets(0, 0, 0, 0));
        // button1.setFocusPainted(false);

        // button1.setBorder(null);
        // button1.setBorderPainted(false);
        // button1.setFocusable(false);
        // EmptyBorder a = new EmptyBorder(0, 0, 0, 0);
        // button1.setBorder(a);
        // button1.setMargin(new Insets(0, 0, 0, 0));
        // button1.setContentAreaFilled(false);
        // button1.setIcon(icon1);

        setVisible(true);
    }
}
