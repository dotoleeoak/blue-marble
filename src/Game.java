import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Game extends JFrame {

	private Image rollDiceButtonImage = new ImageIcon(Main.class.getResource("images/rollDiceButton.png")).getImage();

	public int numPlayer;
	// private int currentPlayer;
	private int playerIdx;

	public static CityManager cityManager = new CityManager();
	public static CoordinateManager coordinateManager = new CoordinateManager();

	ArrayList<Coordinate> coordinateSet = new ArrayList<Coordinate>();
	ArrayList<Player> playerList = new ArrayList<Player>();

	Game(int _numPlayer) {
		this.numPlayer = _numPlayer;
		// setCoordinate();
		setPlayer();
		playerIdx = 1;
	}

	public void setPlayer() {
		switch (numPlayer) {
			case 2:
				playerList.add(new Player(0, "First"));
				playerList.add(new Player(1, "Second"));
			case 3:
				playerList.add(new Player(2, "Third"));
			case 4:
				playerList.add(new Player(3, "Fourth"));
		}
		for (int i = 0; i < playerList.size(); i++) {
			// TODO: Use coordinate manager (player)
			// playerList.get(i).setPosition(coordinateSet.get(0));
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

	int rollDice() {
		return new Random().nextInt() % 6 + 1;
	}

	public boolean turn() {
		Player player = playerList.get(playerIdx);
		int currPos = player.position;
		int dice = rollDice();
		int nextPos = player.nextPosition(dice);
		Coordinate playerCoord = coordinateManager.getPlayerCoordinate(playerIdx, nextPos);

		/* wait for dice roll button */

		/* (animation) rolling dice */

		/* print dice number */
		
		/* (animation) moving character */

		int owner = cityManager.owner(nextPos);

		if (currPos == 8) {
			player.lab();
		} else if (currPos == 4 || currPos == 12) {
			player.winChance();
		} else if (owner == -1) {
			int decision = JOptionPane.showConfirmDialog(this, "부지를 구매하시겠어요?");
			if (decision == 1) {
				cityManager.buyCity(nextPos, player.ID);
			}
		} else if (owner == player.ID) {
			int decision = JOptionPane.showConfirmDialog(this, "건물을 지으시겠어요?");
			if (decision == 1) {
				cityManager.buyBuilding(nextPos, player.ID);
			}
		} else {
			/* use chance */
			if (player.hasChance()) {
				player.popChance();

				if (new Random().nextInt() % 2 == 0) {
					/* some notification (pop-up) here */
					// toll free chance
					JOptionPane.showMessageDialog(this, "찬스 카드에서 통행료 면제 효과 발동!");
				} else {
					// toll x2 chance
					JOptionPane.showMessageDialog(this, "찬스 카드에서 바가지 효과 발동!");
					if (!player.payToll(nextPos)) {
						/* some animation here */
						return false;
					} else if(!player.payToll(nextPos)){
						return false;
					}
				}
			} else if (!player.payToll(nextPos)) {
				/* some animation here */
				return false;
			}
			
			playerList.get(owner).earnMoney(cityManager.getToll(nextPos));
			
			// 현재 건물 거래가 힘듦 (암튼 힘듦)
			// int decision = JOptionPane.showConfirmDialog(this, "건물을 인수하시겠어요?");
			// if (decision == 1) {
			// }
		}

		return true;
	}
	
	// @Override
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
		 *  2-1. 구매 시, 돈 차감 및 빌딩 추가s
		 *  2-2. 구매 안하면, 그냥 pass 
		 * 
		 *  if-3) 소유한 사람 타인
		 *  1. 돈 차감
		 *  2-1. 돈 부족 시, 게임 종료 및 파산
		 *  3. (시간 남으면) 인수 희망 시 가능
		 */

		// 파산하면 while 종료
		while (turn()) {
			playerIdx = (playerIdx + 1) % 4;
		}
		JOptionPane.showMessageDialog(this, ""+playerIdx+"번 플레이어 파산!");

		// int nextPos = playerList.get(currentPlayer).nextPosition(rollDice());
		// Coordinate coord = coordinateManager.getPlayerCoordinate(currentPlayer, nextPos);		
		
		
	}
	
	/* public void close() {
		this.interrupt();
	} */
}
