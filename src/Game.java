import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private Image screenImage;
	private Image background = new ImageIcon(Main.class.getResource("images/Board/board.png")).getImage();
	private ImageIcon[] imagePlayer;
	// private Image rollDiceButtonImage = new ImageIcon(Main.class.getResource("images/rollDiceButton.png")).getImage();

	private JButton rollDiceButton;
	private JLabel[] labelPlayer;

	int numPlayer;
	int playerIdx;

	public static CityManager cityManager = new CityManager();
	public static CoordinateManager coordinateManager = new CoordinateManager();

	// ArrayList<Coordinate> coordinateSet = new ArrayList<Coordinate>();
	ArrayList<Player> playerList;

	Main controller;

	Game(Main c) {
		setLayout(null);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBackground(Color.CYAN);

		imagePlayer = new ImageIcon[4];
		for (int i = 0; i < 4; i++) {
			imagePlayer[i] = new ImageIcon(Main.class.getResource("images/Board/player" + i + ".png"));
		}

		controller = c;
	}

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenDraw((Graphics2D) screenImage.getGraphics());
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		repaint();
	}

	public void init(int numPlayer) {
		this.numPlayer = numPlayer;
		playerIdx = 1;
		playerList = new ArrayList<Player>();
		labelPlayer = new JLabel[numPlayer];
		
		switch (numPlayer) {
			case 4:
			playerList.add(new Player(3, "Fourth"));
			case 3:
			playerList.add(new Player(2, "Third"));
			case 2:
			playerList.add(new Player(1, "Second"));
			playerList.add(new Player(0, "First"));
		}

		/********* SHOW PLAYER ICONS *********/
		for (Player player : playerList) {
			JLabel label = labelPlayer[player.ID];
			Coordinate pos = coordinateManager.getPlayerCoordinate(player.ID, player.position);
			label = new JLabel(imagePlayer[player.ID]);
			label.setBounds(pos.x - 5, pos.y - 15, 30, 55);
			add(label);
		}
	
		rollDiceButton = new JButton();
		rollDiceButton.setBounds(540, 240, 200, 176);
		rollDiceButton.setBorderPainted(false);
		rollDiceButton.setContentAreaFilled(false);
		rollDiceButton.setFocusPainted(false);
		rollDiceButton.setIcon(new ImageIcon(Main.class.getResource("images/Board/rollDiceButton.png")));
		rollDiceButton.setRolloverIcon(new ImageIcon(Main.class.getResource("images/Board/rollDiceButtonEntered.png")));
		rollDiceButton.setPressedIcon(new ImageIcon(Main.class.getResource("images/Board/rollDiceButtonPressed.png")));
		rollDiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turn();
			}
		});
		add(rollDiceButton);
	}
	
	int rollDice() {
		return new Random().nextInt() % 6 + 1;
	}
	
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
	public boolean turn() {
		System.out.println("turn() called.");
		// Player player = playerList.get(playerIdx);
		// int currPos = player.position;
		// int dice = rollDice();
		// int nextPos = player.nextPosition(dice);
		// Coordinate playerCoord = coordinateManager.getPlayerCoordinate(playerIdx, nextPos);
		
		// /* wait for dice roll button */
		
		// /* (animation) rolling dice */
		
		// /* print dice number */
		
		// /* (animation) moving character */
		
		// int owner = cityManager.owner(nextPos);
		
		// if (currPos == 8) {
		// 	player.lab();
		// } else if (currPos == 4 || currPos == 12) {
		// 	player.winChance();
		// } else if (owner == -1) {
		// 	int decision = JOptionPane.showConfirmDialog(this, "부지를 구매하시겠어요?");
		// 	if (decision == 1) {
		// 		cityManager.buyCity(nextPos, player.ID);
		// 	}
		// } else if (owner == player.ID) {
		// 	int decision = JOptionPane.showConfirmDialog(this, "건물을 지으시겠어요?");
		// 	if (decision == 1) {
		// 		cityManager.buyBuilding(nextPos, player.ID);
		// 	}
		// } else {
		// 	/* use chance */
		// 	if (player.hasChance()) {
		// 		player.popChance();
				
		// 		if (new Random().nextInt() % 2 == 0) {
		// 			/* some notification (pop-up) here */
		// 			// toll free chance
		// 			JOptionPane.showMessageDialog(this, "찬스 카드에서 통행료 면제 효과 발동!");
		// 		} else {
		// 			// toll x2 chance
		// 			JOptionPane.showMessageDialog(this, "찬스 카드에서 바가지 효과 발동!");
		// 			if (!player.payToll(nextPos)) {
		// 				/* some animation here */
		// 				return false;
		// 			} else if(!player.payToll(nextPos)){
		// 				return false;
		// 			}
		// 		}
		// 	} else if (!player.payToll(nextPos)) {
		// 		/* some animation here */
		// 		return false;
		// 	}
			
		// 	playerList.get(owner).earnMoney(cityManager.getToll(nextPos));
			
		// 	// 현재 건물 거래가 힘듦 (암튼 힘듦)
		// 	// int decision = JOptionPane.showConfirmDialog(this, "건물을 인수하시겠어요?");
		// 	// if (decision == 1) {
		// 		// }
		// }
		
		return true;
	}
	
	public void startGame() {
		// 파산하면 while 종료
		// while (turn()) {
		// 	playerIdx = (playerIdx + 1) % numPlayer;
		// }
		// try {
		// 	Thread.sleep(3000);
		// } catch(InterruptedException e) {
		// 	e.printStackTrace();
		// }
		// JOptionPane.showMessageDialog(this, ""+playerIdx+"번 플레이어 파산!");

		// int nextPos = playerList.get(currentPlayer).nextPosition(rollDice());
		// Coordinate coord = coordinateManager.getPlayerCoordinate(currentPlayer, nextPos);		
		
		
	}
}
