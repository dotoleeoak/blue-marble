public class Controller {
    BlueMarble frameMenu;
    Game frameGame;

    public int numPlayer;

    public Controller() {
        frameMenu = new BlueMarble(this);
        frameGame = new Game(this);
        frameMenu.setVisible(true);
        frameGame.setVisible(false);
    }

    public void showMenu() {
        frameMenu.setVisible(true);
        frameGame.setVisible(false);
    }

    public void showGame() {
        frameGame.setPlayer(numPlayer);
        frameGame.setVisible(true);
        frameMenu.setVisible(false);
    }
}
