import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JaFrm extends JFrame {
    public JaFrm(String title) {
        setTitle(title); // 타이틀
        setSize(300, 200); // 크기
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 종료버튼 클릭시 완전종료
        JPanel pn = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("맑은고딕", Font.ITALIC, 24)); // 폰트
                g.drawString("안녕하세요", 10, 30); // 문자열 표시
                g.drawOval(10, 50, 200, 40); // 타원표시
                g.drawRect(10, 100, 200, 40); // 사각형표시
            }
        }; // 패널 추가
        add(pn);
        setVisible(true); // 실행시 보이게
    }

    public static void main(String[] args) {
        JaFrm frm = new JaFrm("오라클자바");
    }
}

// class MyPanel extends JPanel {

// // 그림을 그리는 곳

// @Override

// protected void paintComponent(Graphics g) {

// super.paintComponent(g);

// g.setFont(new Font("맑은고딕", Font.ITALIC, 24)); // 폰트

// g.drawString("안녕하세요", 10, 30); // 문자열 표시

// g.drawOval(10, 50, 200, 40); // 타원표시

// g.drawRect(10, 100, 200, 40); // 사각형표시

// }

// }
