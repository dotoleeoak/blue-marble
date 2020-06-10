import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	String name;
	int id;
	int balance;
	int position, positionX, positionY; 
	ArrayList<String> chance;
	int island;
	private Image piece = new ImageIcon(Main.class.getResource("images/piece0.png")).getImage();
	Player(int _id, String _name) {
		name = _name;
		id = _id;
		balance = 2000;
		position = 0;
		island = 0;
		chance = null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getPosition() {
		return position;
	}
	
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public Image getPiece() {
		return piece;
	}
	public void setPiece(Image piece) {
		this.piece = piece;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public ArrayList<String> getChance() {
		return chance;
	}
	public void setChance(ArrayList<String> chance) {
		this.chance = chance;
	}
	public int getIsland() {
		return island;
	}
	public void setIsland(int island) {
		this.island = island;
	}
	
	
}
