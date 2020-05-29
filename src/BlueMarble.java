import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlueMarble {

	public static final int SCREEM_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static void main(String[] args) {
		BoardGUI a = new BoardGUI();

		
		
		
		
		//플레이어 수를 입력 받는 단계
		int num_player = 0;	//플레이어 수
		Player[] player_list = new Player[4]; //플레이어 리스트 저장
		try {
			Scanner scan = new Scanner(System.in);
			do{
				num_player = scan.nextInt();
				//만약 numPlayer가 유효범위 내가 아니면 경고창 띄우기 구문 추가하기
			}while( !(num_player >=1 && num_player <=4) );
			for(int i = 0; i < num_player; i++) {
				
				
			}
			
			
		}catch(Exception e) {
			
		}
		
		
		//플레이어들의 이름을 입력 받는 단계

	}

}
