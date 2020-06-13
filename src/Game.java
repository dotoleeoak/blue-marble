import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private Image screenImage;
	private Image background = new ImageIcon(Main.class.getResource("images/Board/board.png")).getImage();
	private ImageIcon buildingIcon = new ImageIcon(Main.class.getResource("images/Board/building.png"));
	private ImageIcon[] imagePlayer;

	private JButton rollDiceButton;
	private JLabel rollingDice;
	private JLabel[] playerLabel;
	private JLabel[] diceNumber;

	// private Coordinate[] coordinatePlayer;

	private int numPlayer;
	private int playerIdx;
	private int dice;
	private boolean moved;

	public static CityManager cityManager = new CityManager();
	public static PointManager coordinateManager = new PointManager();

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

		diceNumber = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			diceNumber[i] = new JLabel(new ImageIcon(Main.class.getResource("images/Board/dice" + (i + 1) + ".png")));
			diceNumber[i].setBounds(540, 240, 200, 220);
			diceNumber[i].setVisible(false);
			add(diceNumber[i]);
		}

		/********* SHOW PLAYER ICONS *********/
		playerLabel = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			Point point = coordinateManager.getPlayerPoint(i, 0);
			playerLabel[i] = new JLabel(imagePlayer[i]);
			playerLabel[i].setLocation(point.x, point.y);
			playerLabel[i].setSize(30, 30);
			playerLabel[i].setVisible(false);
			add(playerLabel[i]);
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
				boolean flag = turn();
				if (!flag) {
					stopGame();
				}
			}
		});
		add(rollDiceButton);

		rollingDice = new JLabel();
		rollingDice.setBounds(540, 220, 200, 200);
		rollingDice.setIcon(new ImageIcon(Main.class.getResource("images/rollingDice_3.gif")));
		rollingDice.setVisible(false);
		add(rollingDice);

		moved = false;
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
		playerIdx = 0;
		playerList = new ArrayList<Player>();
		// coordinatePlayer = new Coordinate[numPlayer];

		playerList.add(new Player(0, "First"));
		playerList.add(new Player(1, "Second"));
		if (numPlayer >= 3) {
			playerList.add(new Player(3, "Fourth"));
		}
		if (numPlayer == 4) {
			playerList.add(new Player(2, "Third"));
		}

		for (int i = 0; i < numPlayer; i++) {
			playerLabel[i].setVisible(true);
		}
	}

	int rollDice() {
		return new Random().nextInt(6) + 1;
	}

	/*
	 * 각 차례에 할 것
	 * 
	 * 1. 주사위 버튼 대기
	 * 
	 * 2. 주사위 던지기
	 * 
	 * 3. 주사위 숫자 저장
	 * 
	 * 4. 플레이어 이동 (한 칸씩) [한 칸씩 이동하며 에니메이션(?)]
	 * 
	 * 5. 건물 소유 여부 확인
	 * 
	 * if-1) 소유한 사람 없음 1. 건물 구매 여부 질문 2-1. 구매 시, 돈 차감 및 소유주 지정 2-2. 구매 안하면, 그냥 pass
	 * 
	 * if-2) 소유한 사람 본인 1. 빌딩 구매 여부 질문 2-1. 구매 시, 돈 차감 및 빌딩 추가s 2-2. 구매 안하면, 그냥 pass
	 * 
	 * if-3) 소유한 사람 타인 1. 돈 차감 2-1. 돈 부족 시, 게임 종료 및 파산 3. (시간 남으면) 인수 희망 시 가능
	 * 
	 */

	public boolean turn() {
		System.out.println("turn() called.");
		rollDiceButton.setVisible(false);
		rollingDice.setVisible(true);

		Player player = playerList.get(playerIdx);
		dice = rollDice();
		// boolean diceRolled = false;

		Timer showDiceNumber = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// diceRolled = true;
				rollingDice.setVisible(false);
				diceNumber[dice - 1].setVisible(true);
			}
		});

		JLabel label = playerLabel[playerIdx];

		showDiceNumber.setRepeats(false);
		showDiceNumber.start();

		player.position = (player.position + 1) % 16;

		Timer movePlayer = new Timer(20, new ActionListener() {
			Point pointCurr = label.getLocation();
			Point pointDest = coordinateManager.getPlayerPoint(player.ID, player.position);
			int dx = (pointDest.x - pointCurr.x) / 20;
			int dy = (pointDest.y - pointCurr.y) / 20;
			int count = 1;

			@Override
			public void actionPerformed(ActionEvent e) {
				Point currPoint = label.getLocation();
				if (Math.abs(currPoint.x - pointDest.x) < Math.abs(dx)
						|| Math.abs(currPoint.y - pointDest.y) < Math.abs(dy)) {
					/* TODO: Implement moving more than once */
					// System.out.println("stopping action...");
					label.setLocation(pointDest.x, pointDest.y);

					if (count == dice) {
						((Timer) e.getSource()).stop();
						diceNumber[dice - 1].setVisible(false);
						moved = true;
						// rollDiceButton.setVisible(true);
					} else {
						pointCurr = pointDest;
						player.position = (player.position + 1) % 16;
						pointDest = coordinateManager.getPlayerPoint(player.ID, player.position);
						dx = (pointDest.x - pointCurr.x) / 20;
						dy = (pointDest.y - pointCurr.y) / 20;
						count++;
					}
				}
				label.setLocation(currPoint.x + dx, currPoint.y + dy);
			}
		});

		movePlayer.setInitialDelay(2000);
		movePlayer.start();
		// for (moveRemain = dice; moveRemain > 0; moveRemain--) {
		// System.out.println("pointCurr = (" + pointCurr.x + ", " + pointCurr.y + ")");
		// System.out.println("pointDest = (" + pointDest.x + ", " + pointDest.y + ")");
		// System.out.println("dx = " + dx + ", dy = " + dy);

		// Point currPoint = label.getLocation();
		// System.out.println("x = " + currPoint.x + ", y = " + currPoint.y);
		// System.out.println("-------------------------------------------");

		// System.out.println("moving terminated.");

		// rollingDice.setVisible(false);
		// rollDiceButton.setVisible(true);

		// playerLabel[0].setBounds(40, 40, 30, 30);
		// for (int i = 0; i < 50; i++) {
		// coordinatePlayer[playerIdx].x += 1;
		// playerLabel[playerIdx].setBounds(coordinatePlayer[playerIdx].x,
		// coordinatePlayer[playerIdx].y, 30, 30);
		// }

		// Player player = playerList.get(playerIdx);
		// int dice = rollDice();

		// /* wait for dice roll button */

		// /* (animation) rolling dice */

		// /* print dice number */

		// /* (animation) moving character */

		try {
			synchronized (this) {
				while (!moved) {
					this.wait();
				}
				moved = false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int owner = cityManager.owner(player.position);

		if (player.position == 8) {
			player.lab();
		} else if (player.position == 4 || player.position == 12) {
			player.winChance();
		} else if (owner == -1) {
			int decision = JOptionPane.showConfirmDialog(this, "부지를 구매하시겠어요?");
			if (decision == 1) {
				cityManager.buyCity(player.position, player.ID);
			}
		} else if (owner == player.ID) {
			int decision = JOptionPane.showConfirmDialog(this, "건물을 지으시겠어요?");
			if (decision == 1) {
				cityManager.buyBuilding(player.position, player.ID);
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
					if (!player.payToll(player.position)) {
						/* some animation here */
						return false;
					} else if (!player.payToll(player.position)) {
						return false;
					}
				}
			} else if (!player.payToll(player.position)) {
				/* some animation here */
				return false;
			}

			playerList.get(owner).earnMoney(cityManager.getToll(player.position));

			// 현재 건물 거래가 힘듦 (암튼 힘듦)
			// int decision = JOptionPane.showConfirmDialog(this, "건물을 인수하시겠어요?");
			// if (decision == 1) {
			// }
		}

		playerIdx = (playerIdx + 1) % numPlayer;
		return true;
	}

	public void startGame() {
		// 파산하면 while 종료
		// while (turn()) {
		// playerIdx = (playerIdx + 1) % numPlayer;
		// }
		// try {
		// Thread.sleep(3000);
		// } catch(InterruptedException e) {
		// e.printStackTrace();
		// }
		// JOptionPane.showMessageDialog(this, ""+playerIdx+"번 플레이어 파산!");

		// int player.position = playerList.get(currentPlayer).nextPosition(rollDice());
		// Coordinate coord = coordinateManager.getPlayerPoint(currentPlayer,
		// player.position);

	}

	public void stopGame() {

	}
}
