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
		boolean[] buildedBuilnding = new boolean[2];
	
		City(int price, String _cityName) {
			this.ownerID = -1;  // -1 means nobody 
			this.price = price;
			this.toll = 0;
			cityName = _cityName;
			buildedBuilnding[0] = false;
			buildedBuilnding[1] = false;
		}
	}

	ArrayList<City> arrayCity;

	CityManager() {
		arrayCity = new ArrayList<City>();
		arrayCity.add(new City(0, "시작" ));    // ����
		arrayCity.add(new City(100, "수성관"));  // ������
		arrayCity.add(new City(120, "벤젠고리과"));  // ����������
		arrayCity.add(new City(150, "복지회관"));  // ����ȸ��
		arrayCity.add(new City(0, "찬스"));    // ����
		arrayCity.add(new City(200, "학생회관" ));  // �л�ȸ��
		arrayCity.add(new City(250, "제1공학관" ));  // ��1���а�
		arrayCity.add(new City(350, "제2공학과"));  // ��2���а�
		arrayCity.add(new City(0, "Lab"));    // Lab
		arrayCity.add(new City(400, "종합연구동"));  // ���տ�����
		arrayCity.add(new City(500, "산학협력센터"));  // �������¼���
		arrayCity.add(new City(600, "반도체관"));  // �ݵ�ü��
		arrayCity.add(new City(0, "찬스"));    // ����
		arrayCity.add(new City(800, "약학관"));  // ���а�
		arrayCity.add(new City(1000, "의학관")); // ���а�
		arrayCity.add(new City(1500, "디도")); // ��
	}
	public String getName(int _position){
		return arrayCity.get(_position).cityName;
	}
	public int owner(int idx) {
		return arrayCity.get(idx).ownerID;
	}
	public boolean builed(int _position, int _buildingSort){
		return arrayCity.get(_position).buildedBuilnding[_buildingSort];
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

	public void buyCity(int idx, int ID) {
		City city = arrayCity.get(idx);
		city.ownerID = ID;
		city.toll += city.price;
	}

	public void buyBuilding(int idx, int ID) {
		City city = arrayCity.get(idx);
		// one time, only one buy
		if( city.buildedBuilnding[0] == false){
			city.toll += city.price / 2;
			city.buildedBuilnding[0] = true;
			
		}else if(city.buildedBuilnding[1] == false ){
			city.toll += city.price / 2;
			city.buildedBuilnding[1] = true;
		}
		//city.ownerID = ID;
	}
}

