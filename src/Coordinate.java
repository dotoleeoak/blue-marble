class Coordinate {
<<<<<<< HEAD
   public int x;
   public int y;
   Coordinate(int _x, int _y){
      this.x = _x;
      this.y = _y;
   }
=======
	public int x;
	public int y;
	Coordinate(int _x, int _y){
		this.x = _x;
		this.y = _y;
	}
>>>>>>> branch 'PJS' of https://github.com/dotoleeoak/BlueMarble.git
}

class CoordinateManager {
<<<<<<< HEAD
   Coordinate buildingCoordinate[][];
   Coordinate playerCoordinate[][] = new Coordinate[4][16];

   CoordinateManager() {
	   playerCoordinate[0][0].x = 666;
	   playerCoordinate[0][0].y = 531;
	   playerCoordinate[0][1].x = 535;
	   playerCoordinate[0][1].y = 511;
	   playerCoordinate[0][2].x = 445;
	   playerCoordinate[0][2].y = 459;
	   playerCoordinate[0][3].x = 368;
	   playerCoordinate[0][3].y = 408;
	   playerCoordinate[0][4].x = 283;
	   playerCoordinate[0][4].y = 343;
	   playerCoordinate[0][5].x = 324;
	   playerCoordinate[0][5].y = 264;
	   playerCoordinate[0][6].x = 403;
	   playerCoordinate[0][6].y = 212;
	   playerCoordinate[0][7].x = 483;
	   playerCoordinate[0][7].y = 161;
	   playerCoordinate[0][8].x = 604;
	   playerCoordinate[0][8].y = 108;
	   playerCoordinate[0][9].x = 732;
	   playerCoordinate[0][9].y = 135;
	   playerCoordinate[0][10].x = 816;
	   playerCoordinate[0][10].y = 186;
	   playerCoordinate[0][11].x = 895;
	   playerCoordinate[0][11].y = 235;
	   playerCoordinate[0][12].x = 980;
	   playerCoordinate[0][12].y = 299;
	   playerCoordinate[0][13].x = 943;
	   playerCoordinate[0][13].y = 378;
	   playerCoordinate[0][14].x = 860;
	   playerCoordinate[0][14].y = 430;
	   playerCoordinate[0][15].x = 744;
	   playerCoordinate[0][15].y = 482;
	   
	   playerCoordinate[1][0].x = 624;
	   playerCoordinate[1][0].y = 548;
	   playerCoordinate[1][1].x = 516;
	   playerCoordinate[1][1].y = 499;
	   playerCoordinate[1][2].x = 428;
	   playerCoordinate[1][2].y = 447;
	   playerCoordinate[1][3].x = 351;
	   playerCoordinate[1][3].y = 397;
	   playerCoordinate[1][4].x = 251;
	   playerCoordinate[1][4].y = 326;
	   playerCoordinate[1][5].x = 343;
	   playerCoordinate[1][5].y = 252;
	   playerCoordinate[1][6].x = 420;
	   playerCoordinate[1][6].y = 202;
	   playerCoordinate[1][7].x = 502;
	   playerCoordinate[1][7].y = 151;
	   playerCoordinate[1][8].x = 634;
	   playerCoordinate[1][8].y = 87;
	   playerCoordinate[1][9].x = 749;
	   playerCoordinate[1][9].y = 146;
	   playerCoordinate[1][10].x = 833;
	   playerCoordinate[1][10].y = 198;
	   playerCoordinate[1][11].x = 913;
	   playerCoordinate[1][11].y = 247;
	   playerCoordinate[1][12].x = 1007;
	   playerCoordinate[1][12].y = 318;
	   playerCoordinate[1][13].x = 927;
	   playerCoordinate[1][13].y = 390;
	   playerCoordinate[1][14].x = 843;
	   playerCoordinate[1][14].y = 442;
	   playerCoordinate[1][15].x = 758;
	   playerCoordinate[1][15].y = 493;
	   
	   playerCoordinate[2][0].x = 625;
	   playerCoordinate[2][0].y = 505;
	   playerCoordinate[2][1].x = 498;
	   playerCoordinate[2][1].y = 488;
	   playerCoordinate[2][2].x = 412;
	   playerCoordinate[2][2].y = 436;
	   playerCoordinate[2][3].x = 334;
	   playerCoordinate[2][3].y = 387;
	   playerCoordinate[2][4].x = 307;
	   playerCoordinate[2][4].y = 318;
	   playerCoordinate[2][5].x = 361;
	   playerCoordinate[2][5].y = 244;
	   playerCoordinate[2][6].x = 437;
	   playerCoordinate[2][6].y = 193;
	   playerCoordinate[2][7].x = 519;
	   playerCoordinate[2][7].y = 144;
	   playerCoordinate[2][8].x = 633;
	   playerCoordinate[2][8].y = 126;
	   playerCoordinate[2][9].x = 766;
	   playerCoordinate[2][9].y = 160;
	   playerCoordinate[2][10].x = 850;
	   playerCoordinate[2][10].y = 210;
	   playerCoordinate[2][11].x = 930;
	   playerCoordinate[2][11].y = 261;
	   playerCoordinate[2][12].x = 952;
	   playerCoordinate[2][12].y = 318;
	   playerCoordinate[2][13].x = 912;
	   playerCoordinate[2][13].y = 400;
	   playerCoordinate[2][14].x = 825;
	   playerCoordinate[2][14].y = 453;
	   playerCoordinate[2][15].x = 741;
	   playerCoordinate[2][15].y = 505;
	   
	   playerCoordinate[3][0].x = 590;
	   playerCoordinate[3][0].y = 528;
	   playerCoordinate[3][1].x = 479;
	   playerCoordinate[3][1].y = 476;
	   playerCoordinate[3][3].x = 395;
	   playerCoordinate[3][3].y = 425;
	   playerCoordinate[3][3].x = 316;
	   playerCoordinate[3][3].y = 373;
	   playerCoordinate[3][4].x = 283;
	   playerCoordinate[3][4].y = 301;
	   playerCoordinate[3][5].x = 377;
	   playerCoordinate[3][5].y = 235;
	   playerCoordinate[3][6].x = 454;
	   playerCoordinate[3][6].y = 181;
	   playerCoordinate[3][7].x = 536;
	   playerCoordinate[3][7].y = 134;
	   playerCoordinate[3][8].x = 662;
	   playerCoordinate[3][8].y = 105;
	   playerCoordinate[3][9].x = 783;
	   playerCoordinate[3][9].y = 173;
	   playerCoordinate[3][10].x = 867;
	   playerCoordinate[3][10].y = 219;
	   playerCoordinate[3][11].x = 947;
	   playerCoordinate[3][11].y = 273;
	   playerCoordinate[3][12].x = 980;
	   playerCoordinate[3][12].y = 335;
	   playerCoordinate[3][13].x = 895;
	   playerCoordinate[3][13].y = 413;
	   playerCoordinate[3][14].x = 808;
	   playerCoordinate[3][14].y = 462;
	   playerCoordinate[3][15].x = 724;
	   playerCoordinate[3][15].y = 516;
	   
   }

   Coordinate getPlayerCoordinate(int playerNum, int position) {
      return playerCoordinate[playerNum][position];
   }
   Coordinate getBuildlingCoordinate(int buildingNum, int position) {
      return buildingCoordinate[buildingNum][position];
   }
=======
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
>>>>>>> branch 'PJS' of https://github.com/dotoleeoak/BlueMarble.git
}