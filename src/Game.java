import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image rollDiceButtonImage = new ImageIcon(Main.class.getResource("images/rollDiceButton.png")).getImage();

	public int numPlayer;

	ArrayList<Coordinate> coordinateSet = new ArrayList<Coordinate>();
	ArrayList<Player> playerList = new ArrayList<Player>();

	Game(int _numPlayer) {
		this.numPlayer = _numPlayer;
		// setCoordinate();
		setPlayer();
	}

	public void setPlayer() {
		switch (numPlayer) {
			case 2:
				playerList.add(new Player(0));
				playerList.add(new Player(1));
			case 3:
				playerList.add(new Player(2));
			case 4:
				playerList.add(new Player(3));
		}
		for (int i = 0; i < playerList.size(); i++) {
			// TODO: Use coordinate manager (player)
			playerList.get(i).setPosition(coordinateSet.get(0));
		}
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(rollDiceButtonImage, 612, 350, null);
		for (int i = 0; i < playerList.size(); i++) {
			// TODO: Use coordinate manager (player)
			// g.drawImage(playerList.get(i).getPiece(), playerList.get(i).getPositionX(),
			// 		playerList.get(i).getPositionY(), null);
		}
	}
	
	@Override
	public void run() {
		/*  각 차례에 할 것
		 *  1. 주사위 버튼 대기
		 *  2. 주사위 던지기
		 *  3. 주사위 숫자 저장
		 *  4. 플레이어 이동 (한 칸씩)
		 *     4-1. 한 칸씩 이동하며 에니메이션(?)
		 *  5. 건물 소유 여부 확인
		 *  
		 *  if-1) 소유한 사람 없음
		 *  1. 건물 구매 여부 질문
		 *  2-1. 구매 시, 돈 차감 및 소유주 지정
		 *  2-2. 구매 안하면, 그냥 pass
		 * 
		 *  if-2) 소유한 사람 본인
		 *  1. 빌딩 구매 여부 질문
		 *  2-1. 구매 시, 돈 차감 및 빌딩 추가
		 *  2-2. 구매 안하면, 그냥 pass 
		 * 
		 *  if-3) 소유한 사람 타인
		 *  1. 돈 차감
		 *  2-1. 돈 부족 시, 게임 종료 및 파산
		 */
	}
	
	public void close() {
		this.interrupt();
	}
}
