class Coordinate {
	public int x;
	public int y;
	Coordinate(int _x, int _y){
		this.x = _x;
		this.y = _y;
	}
}

class CoordinateManager {
	Coordinate buildingCoordinate[][];
	Coordinate playerCoordinate[][];

	CoordinateManager() {
		// read coordinates
	}

	Coordinate getPlayerCoordinate(int playerNum, int position) {
		return playerCoordinate[playerNum][position];
	}
	Coordinate getBuildlingCoordinate(int buildingNum, int position) {
		return buildingCoordinate[buildingNum][position];
	}
}