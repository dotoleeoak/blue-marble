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
		String cityName;
		boolean[] buildingBuilt = new boolean[2];

		City(int price, String _cityName) {
			this.ownerID = -1; // -1 means nobody
			this.price = price;
			this.toll = 0;
			cityName = _cityName;
			buildingBuilt[0] = false;
			buildingBuilt[1] = false;
		}
	}

	ArrayList<City> arrayCity;

	CityManager() {
		arrayCity = new ArrayList<City>();
		arrayCity.add(new City(0, "시작"));
		arrayCity.add(new City(100, "수성관"));
		arrayCity.add(new City(120, "벤젠고리관"));
		arrayCity.add(new City(150, "복지회관"));
		arrayCity.add(new City(0, "찬스"));
		arrayCity.add(new City(200, "학생회관"));
		arrayCity.add(new City(250, "제1공학관"));
		arrayCity.add(new City(350, "제2공학관"));
		arrayCity.add(new City(0, "Lab"));
		arrayCity.add(new City(400, "종합연구동"));
		arrayCity.add(new City(500, "산학협력센터"));
		arrayCity.add(new City(600, "반도체관"));
		arrayCity.add(new City(0, "찬스"));
		arrayCity.add(new City(800, "약학관"));
		arrayCity.add(new City(1000, "의학관"));
		arrayCity.add(new City(1500, "디도"));
	}

	public String getName(int _position) {
		return arrayCity.get(_position).cityName;
	}

	public int owner(int idx) {
		return arrayCity.get(idx).ownerID;
	}

	public boolean builded(int _position, int _buildingSort) {
		return arrayCity.get(_position).buildingBuilt[_buildingSort];
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
		city.toll += city.price * 1.5;
	}

	public void buyBuilding(int idx) {
		City city = arrayCity.get(idx);
		// one time, only one buy
		if (city.buildingBuilt[0] == false) {
			city.toll += city.price * 1.5;
			city.buildingBuilt[0] = true;

		} else if (city.buildingBuilt[1] == false) {
			city.toll += city.price * 1.5;
			city.buildingBuilt[1] = true;
		}
	}
	public boolean isBuildlingFull(int idx) {
		return arrayCity.get(idx).buildingBuilt[1];
	}
}
