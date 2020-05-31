import java.awt.BorderLayout;
 
import javax.swing.JButton;
import javax.swing.JFrame;
 
class BorderLay extends JFrame{
    
 
    public BorderLay() {
        super("Border Layout Test");
        this.init();//화면을 구성하는 메소드
        
        this.setSize(BlueMarble.SCREEM_WIDTH, BlueMarble.SCREEN_HEIGHT);
        //화면의 가로 세로 크기
        setVisible(true);
        //화면에 표시하는 기능 이걸 안쓰면 표시안됨
    }
    public void init(){
        this.add(new JButton("Center"));
        this.add(new JButton("West"),BorderLayout.LINE_START);
        this.add(new JButton("East"),BorderLayout.LINE_END);
        this.add(new JButton("North"),BorderLayout.PAGE_START);
        this.add(new JButton("South"),BorderLayout.PAGE_END);
    }
}
public class BorderLayoutTest {
    public static void main(String[] args) {
        new BorderLay();
    }
}
