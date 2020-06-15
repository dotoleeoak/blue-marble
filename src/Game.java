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
        Player nowPlayer = playerList.get(playerIdx);
        try {
            while (true) {
                nowPlayer = playerList.get(playerIdx);
                if (nowPlayer.isInLab()) {
                    JOptionPane.showMessageDialog(null, nowPlayer.getID() + " Player is writing thesis.\n"
                            + nowPlayer.getRemainingThesis() + " page remaing.");
                    nowPlayer.writeThesis();
                    playerIdx++;
                    playerIdx %= numPlayer;
                    continue;
                }
                dice = -1;
                while (dice == -1) {
                    Thread.sleep(100);
                }
                gameGUI.offRollingDice();
                gameGUI.onDiceNumber(dice);
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
        JOptionPane.showMessageDialog(null, nowPlayer.getID() + " Player lost all money!");
        System.exit(0);

    }

    public void reachGround(Player player) {
        switch (player.getPosition()) {
            case 8:
                JOptionPane.showMessageDialog(null,
                        "Player is locked in by professor!\nHas to write 3 thesis to escape");
                player.lab();
                break;
            case 4:
            case 12:
                JOptionPane.showMessageDialog(null, "Chance acquired!");
                player.increChance();
                break;
            case 0:
                // player.inStart();
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
            if (player.getMoney() < cityManager.getPrice(position)) {
                JOptionPane.showMessageDialog(null,
                        "You don't have enough money to buy this place.\ncost : " + cityManager.getPrice(position));
                return;
            }
            int decision = JOptionPane.showConfirmDialog(new JPanel(),
                    "Will you buy " + cityName + "?\ncost : " + cityManager.getPrice(position));
            if (decision == 0) {
                if (player.buyCity(cityManager.getPrice(position))) {
                    cityManager.buyCity(position, ID);
                }
            }
        } else if (owner == ID) {
            if (cityManager.isBuildlingFull(position)) {
                return;
            }
            if (player.getMoney() < cityManager.getPrice(position)) {
                JOptionPane.showMessageDialog(null, "You don't have enough money to build a building.\ncost : "
                        + cityManager.getPriceBuilding(position));
                return;
            }
            int decision = JOptionPane.showConfirmDialog(new JPanel(),
                    "Will you build a building on " + cityName + "?");
            if (decision == 0) {
                if (player.canBuyBuilding(cityManager.getPriceBuilding(position))) {
                    cityManager.buyBuilding(position);
                }
            }
        } else {
            int toll = -1;
            if (player.hasChance()) {
                int decision = JOptionPane.showConfirmDialog(new JPanel(), "Will you use chance?");
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
        for (int i = 0; i < 20; i++) {
            Point interPoint = new Point((prePoint.x * (20 - i) + nextPoint.x * i) / 20,
                    (prePoint.y * (20 - i) + nextPoint.y * i) / 20);
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
        // dice = 4;
    }

    public void close() {
        this.interrupt();
    }
}
