import java.util.ArrayList;

// enum cities {
// 	START, SOOSUNG, BENZENE, BOKJI, 
// 	CHANCE_1, STUDENT, ENGINEER_1, ENGINEER_2, 
// 	LAB, JONGHAP, SANHAK, SEMICONDUCTOR, 
// 	CHANCE_2, PHARMACY, MEDICINE, LIBRARY;
// }

class CityManager {

	class City {
		int ownerID;
		int price;
		int toll;
		String name;

		City(int price, String name) {
			this.ownerID = -1; // -1 means nobody
			this.price = price;
			this.toll = 0;
			this.name = name;
		}
	}

	ArrayList<City> arrayCity;

	CityManager() {
		arrayCity = new ArrayList<City>();
		arrayCity.add(new City(0, "½ÃÀÛ")); // ï¿½ï¿½ï¿½ï¿½
		arrayCity.add(new City(100, "¼ö¼º°ü")); // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		arrayCity.add(new City(120, "º¥Á¨°í¸®°ü")); // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		arrayCity.add(new City(150, "º¹ÁöÈ¸°ü")); // ï¿½ï¿½ï¿½ï¿½È¸ï¿½ï¿½
		arrayCity.add(new City(0, "Âù½º")); // ï¿½ï¿½ï¿½ï¿½
		arrayCity.add(new City(200, "ÇÐ»ýÈ¸°ü")); // ï¿½Ð»ï¿½È¸ï¿½ï¿½
		arrayCity.add(new City(250, "Á¦1°øÇÐ°ü")); // ï¿½ï¿½1ï¿½ï¿½ï¿½Ð°ï¿½
		arrayCity.add(new City(350, "Á¦2°øÇÐ°ü")); // ï¿½ï¿½2ï¿½ï¿½ï¿½Ð°ï¿½
		arrayCity.add(new City(0, "Lab")); // Lab
		arrayCity.add(new City(400, "Á¾ÇÕ¿¬±¸µ¿")); // ï¿½ï¿½ï¿½Õ¿ï¿½ï¿½ï¿½ï¿½ï¿½
		arrayCity.add(new City(500, "»êÇÐÇù·Â¼¾ÅÍ")); // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Â¼ï¿½ï¿½ï¿½
		arrayCity.add(new City(600, "¹ÝµµÃ¼°ü")); // ï¿½Ýµï¿½Ã¼ï¿½ï¿½
		arrayCity.add(new City(0, "Âù½º")); // ï¿½ï¿½ï¿½ï¿½
		arrayCity.add(new City(800, "¾àÇÐ°ü")); // ï¿½ï¿½ï¿½Ð°ï¿½
		arrayCity.add(new City(1000, "ÀÇÇÐ°ü")); // ï¿½ï¿½ï¿½Ð°ï¿½
		arrayCity.add(new City(1500, "µðµµ")); // ï¿½ï¿½
	}

	public int owner(int idx) {
		return arrayCity.get(idx).ownerID;
	}

	public int getPrice(int idx) {
		return arrayCity.get(idx).price;
	}

	public int getPriceBuilding(int idx) {
		return arrayCity.get(idx).price / 2;
	}

	public int getToll(int idx) {
		return arrayCity.get(idx).toll;
	}

	public String getName(int idx) {
		return arrayCity.get(idx).name;
	}

	public void buyCity(int idx, int ID) {
		City city = arrayCity.get(idx);
		city.ownerID = ID;
		city.toll += city.price;
	}

	public void buyBuilding(int idx, int ID) {
		City city = arrayCity.get(idx);
		city.ownerID = ID;
		city.toll += city.price / 2;
	}
}
