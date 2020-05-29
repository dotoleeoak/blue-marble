
public class Player {
	String name;
	int id;
	int have_money;
	int position; 
	String[] chance = new String[10];   
	//int completeNumber
	int island;
	Player(int _id, String _name) {
		name = _name;
		id = _id;
		have_money = 2000;
		position = -1;
		island = 0;
		for(int i = 0; i < 10; i++) {
			chance[i] = "";
		}
		// completeNumber = 0;
	}
}
