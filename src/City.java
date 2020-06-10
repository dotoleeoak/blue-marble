import java.util.ArrayList;

class CityManager {
	// enum cities {
	// 	SOOSUNG, BENZENE, BOKJI, STUDENT, 
	// 	ENGINEER_1, ENGINEER_2, JONGHAP, SANHAK, 
	// 	SEMICONDUCTOR, PHARMACY, MEDICINE, LIBRARY;
	// }
	
	class City {
		int ownerID;
		int price;
		int toll;
	
		City(int price) {
			this.ownerID = -1;  // -1 means nobody 
			this.price = price;
			this.toll = 0;
		}
	}

	ArrayList<City> arrayCity;

	CityManager() {
		arrayCity = new ArrayList<City>();
		arrayCity.add(new City(100));  // 수성관
		arrayCity.add(new City(120));  // 벤젠고리관
		arrayCity.add(new City(150));  // 복지회관
		arrayCity.add(new City(200));  // 학생회관
		arrayCity.add(new City(250));  // 제1공학관
		arrayCity.add(new City(350));  // 제2공학관
		arrayCity.add(new City(400));  // 종합연구동
		arrayCity.add(new City(500));  // 산학협력센터
		arrayCity.add(new City(600));  // 반도체관
		arrayCity.add(new City(800));  // 약학관
		arrayCity.add(new City(1000)); // 의학관
		arrayCity.add(new City(1500)); // 디도
	}

	public int owner(int i) {
		return arrayCity.get(i).ownerID;
	}

	public int getPrice(int i) {
		return arrayCity.get(i).price;
	}

	public int getPriceBuilding(int i) {
		return arrayCity.get(i).price / 2;
	}

	public void buyCity(int i, int ID) {
		City city = arrayCity.get(i);
		city.ownerID = ID;
		city.toll += city.price;
	}

	public void buyBuilding(int i, int ID) {
		City city = arrayCity.get(i);
		city.ownerID = ID;
		city.toll += city.price / 2;
	}
}

