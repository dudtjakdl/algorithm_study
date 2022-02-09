package Implementation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh
 * 걸린 시간 : 40분
 * 리뷰 : 사다리 배열 길기 때문에 BufferedReader로 입력을 받아왔다.
 * 그리고 사다리를 타는 경우의 수를 줄이기 위해 시작지점이 아니라 도착지점(2)을 먼저 찾고,
 * 도착지점부터 거꾸로 위로 사다리를 타서 출발점을 찾아주었다.
*/
public class SWEA_1210_Ladder1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test_case=1; test_case<=10; test_case++) {
			int T = Integer.parseInt(br.readLine()); // 테스트 케이스 번호
			int[][] map = new int[100][100]; // 사다리 배열
			
			// 사다리 입력
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int targetCol = -1; // 현재 사다리의 열
			
			// 맨 아래쪽 행에서 도착지점(2) 찾기
			for(int j=0; j<100; j++) {
				if(map[99][j] == 2) {
					// 도착지점(2)의 열 인덱스로 초기화
					targetCol = j;
					break;
				}
			}
			
			// 도착지점의 위부터 사다리 타기 시작
			for(int i=98; i>=0; i--) {
				if(targetCol-1 >= 0 && map[i][targetCol-1] == 1) { // 왼쪽에 사다리가 있는지 체크
					// 왼쪽으로 0을 만나기 전까지 사다리 타기
					targetCol--;
					while(targetCol-1 >= 0 && map[i][targetCol-1] == 1) {
						targetCol--;
					}
				}
				else if(targetCol+1 < 100 && map[i][targetCol+1] == 1) { // 오른쪽에 사다리가 있는지 체크
					// 오른쪽으로 0을 만나기 전까지 사다리 타기
					targetCol++;
					while(targetCol+1 < 100 && map[i][targetCol+1] == 1) {
						targetCol++;
					}
				}
			}
			
			System.out.println("#" + T + " " + targetCol);
		}
	}

}
