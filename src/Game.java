import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends Thread {
    public int numPlayer;
    private int playerIdx;
    private int dice;
    // private boolean moved;

    public CityManager cityManager = new CityManager();
    public PointManager pointManager = new PointManager();
    public GameGUI gameGUI;
    ArrayList<Player> playerList;

    Main controller;

    Game(Main c, int numPlayer) {
        this.numPlayer = numPlayer;
        playerIdx = 0;
        playerList = new ArrayList<Player>();

        playerList.add(new Player(0, "First", this));
        playerList.add(new Player(1, "Second", this));
        if (numPlayer >= 3) {
            playerList.add(new Player(2, "Third", this));
        }
        if (numPlayer == 4) {
            playerList.add(new Player(3, "Fourth", this));
        }
        // moved = false;
        controller = c;
        gameGUI = new GameGUI(this);
        gameGUI.setVisible(true);
        controller.add(gameGUI);
    }

    @Override
    public void run() {
        try {
            while (true) {
				Player nowPlayer = playerList.get(playerIdx);
				if(nowPlayer.isInLab()){
					JOptionPane.showMessageDialog(null, playerIdx + "th Player is writing thesis. " +  nowPlayer.getThesis() + " page is remaing.");
                    nowPlayer.decreLab();
					playerIdx++;
					playerIdx %= numPlayer;
					continue;
				}
                dice = -1;
                while (dice == -1) {
                    Thread.sleep(100);
                }
                // invoke
                if (dice != -1) {
                    gameGUI.offRollingDice();
                    gameGUI.onDiceNumber(dice);
                }
                for (int i = 0; i < dice; i++) {
                    move(nowPlayer);
                }
                gameGUI.offDiceNumber(dice);
                reachGround(nowPlayer);

                if (nowPlayer.getMoney() < 0)
                    break;

                playerIdx++;
                playerIdx %= numPlayer;
            }
            // @ add game exit message
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, playerIdx + "th Player lost all your money and went bankrupt.");
        System.exit(0);

    }

    public void reachGround(Player player) {
        switch (player.getPosition()) {
            case 8:
                player.lab();
                break;
            case 4:
            case 12:
                JOptionPane.showMessageDialog(null, "Chance acquired!");
                player.increChance();
                break;
            case 0:
                player.inStart();
                break;
            default:
                inCity(player);
        }
    }

    public void inCity(Player player) {
        int position = player.getPosition();
        int owner = cityManager.owner(position);
        int ID = player.getID();
        String cityName = cityManager.getName(position);

        if (owner == -1) {
			int decision = JOptionPane.showConfirmDialog(new JPanel(), 
					"Will you buy " + cityName + "?");
            if (decision == 0) {
                if (player.buyCity(cityManager.getPrice(position))) {
                    cityManager.buyCity(position, ID);
                }
            }
        } else if (owner == ID) {
            int decision = JOptionPane.showConfirmDialog(new JPanel(),
                    "Will you build a building on " + cityName + "?");
            if (decision == 0) {
                if (player.buyBuilding(cityManager.getPriceBuilding(position))) {
                    cityManager.buyBuilding(position, ID);
                }
            }
        } else {
            int toll = -1;
            if (player.hasChance()) {
                int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you use chane?");
                player.popChance();
                if (decision == 0) {
                    int randomChance = new Random().nextInt(2);
                    if (randomChance == 0) {
                        toll = cityManager.getToll(position) / 2;
                        JOptionPane.showMessageDialog(null,
                                "you need to pay just 50% toll. Player " + ID + " lose " + toll + " won");
                    } else if (randomChance == 1) {
                        toll = cityManager.getToll(position) * 2;
                        JOptionPane.showMessageDialog(null,
                                "you need to pay 200% toll. Player " + ID + " lose " + toll + " won");
                    }
                }
            }
            if (toll == -1) {
                toll = cityManager.getToll(position);
                JOptionPane.showMessageDialog(null, "Player " + ID + " lose " + toll + " won");
            }
            playerList.get(owner).earnMoney(toll);
            player.payToll(toll);
        }
    }

    public void move(Player player) {
        int ID = player.getID();
        Point prePoint = pointManager.getPlayerPoint(ID, player.getPosition());
        player.increPosition();
        Point nextPoint = pointManager.getPlayerPoint(ID, player.getPosition());
        for (int i = 0; i < 50; i++) {
            Point interPoint = new Point((prePoint.x * (50 - i) + nextPoint.x * i) / 50,
                    (prePoint.y * (50 - i) + nextPoint.y * i) / 50);
            try {
                gameGUI.playerMove(player, interPoint);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        gameGUI.playerMove(player, nextPoint);
    }

    public void rollDice() {
        dice = new Random().nextInt(6) + 1;
    }

    public void close() {
        this.interrupt();
    }
}