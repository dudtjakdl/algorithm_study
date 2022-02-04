package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
 * 걸린 시간 : 60분
 * 리뷰 : 우선 문제의 요구조건이 많아서 하나하나 차례대로 어떻게 구현해야할지 생각하였다.
 * 2차원 행렬 내에서 경계값을 검사하기 위해 deltas를 만들었고, 입력대로 게임을 진행시키기 위해
 * switch-case로 입력을 제어하였다. 이동을 시작하면 바로 방향을 바꾸는 작업과, 한칸씩 이동 시켜야 한다는
 * 조건을 빼먹어 조금 시간이 걸렸다. 시뮬레이션 문제는 조건을 하나하나 만족했는지 다시 확인하는게 중요한 것 같다.
*/
public class SWEA_1873_상호의_배틀필드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine()); // 테스트 케이스 개수
		int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; //위,아래,왼쪽,오른쪽
		
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int H = Integer.parseInt(st.nextToken()); // 게임 맵 높이
			int W = Integer.parseInt(st.nextToken()); // 게임 맵 너비
			char[][] map = new char[H][W];
			
			for(int i=0; i<H; i++) {
				map[i] = bf.readLine().toCharArray();
			}
			
			int N = Integer.parseInt(bf.readLine()); // 입력의 개수
			char[] inputs = new char[N];
			
			inputs = bf.readLine().toCharArray();
			int dir = 0; // 전차의 현재 방향 (0: 위 1: 아래 2: 왼쪽 3: 오른쪽)
			int I = 0; // 전차의 현재 행 위치
			int J = 0; // 전차의 현재 열 위치
			
			// 전차의 현재위치와 방향 탐색
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j] == '^') {
						dir = 0;
						I = i;
						J = j;
						break;
					}
					else if (map[i][j] == 'v'){
						dir = 1;
						I = i;
						J = j;
						break;
					}
					else if (map[i][j] == '<'){
						dir = 2;
						I = i;
						J = j;
						break;
					}
					else if (map[i][j] == '>'){
						dir = 3;
						I = i;
						J = j;
						break;
					}
				}
			}
			
			// 입력대로 실행
			for(char input : inputs) {
				switch(input) {
				case 'U':
					dir = 0;
					map[I][J] = '^';
					//이동 위치가 경계를 넘지 않고 평지인지 확인
					if(I+deltas[dir][0] >= 0 && map[I+deltas[dir][0]][J] == '.') {
						map[I][J] = '.'; // 현재 위치 평지로 바꾸기
						I = I+deltas[dir][0]; // 현재 위치 변경
						map[I][J] = '^'; // 현재 지도 문자 변경
					}
					break;
				case 'D':
					dir = 1;
					map[I][J] = 'v';
					//이동 위치가 경계를 넘지 않고 평지인지 확인
					if(I+deltas[dir][0] < H && map[I+deltas[dir][0]][J] == '.') {
						map[I][J] = '.'; // 현재 위치 평지로 바꾸기
						I = I+deltas[dir][0]; // 현재 위치 변경
						map[I][J] = 'v'; // 현재 지도 문자 변경
					}
					break;
				case 'L':
					dir = 2;
					map[I][J] = '<';
					//이동 위치가 경계를 넘지 않고 평지인지 확인
					if(J+deltas[dir][1] >= 0 && map[I][J+deltas[dir][1]] == '.') {
						map[I][J] = '.'; // 현재 위치 평지로 바꾸기
						J = J+deltas[dir][1]; // 현재 위치 변경
						map[I][J] = '<'; // 현재 지도 문자 변경
					}
					break;
				case 'R':
					dir = 3;
					map[I][J] = '>';
					//이동 위치가 경계를 넘지 않고 평지인지 확인
					if(J+deltas[dir][1] < W && map[I][J+deltas[dir][1]] == '.') {
						map[I][J] = '.'; // 현재 위치 평지로 바꾸기
						J = J+deltas[dir][1]; // 현재 위치 변경
						map[I][J] = '>'; // 현재 지도 문자 변경
					}
					break;
				case 'S':
					int r = I; // 포탄의 현재 행 위치
					int c = J; // 포탄의 현재 열 위치
					// 포탄 이동 위치가 경계를 넘는지 확인
					while(r+deltas[dir][0] >= 0 && r+deltas[dir][0] < H && c+deltas[dir][1] >= 0 && c+deltas[dir][1] < W) {
						r += deltas[dir][0];
						c += deltas[dir][1];
						if(map[r][c] == '*') { // 포탄이 벽돌 벽과 만나면
							map[r][c] = '.'; // 벽돌 벽 평지로 바꾸기
							break; // 포탄 이동 종료
						}
						else if(map[r][c] == '#') { // 포탄이 강철 벽과 만나면
							break; // 포탄 이동 종료
						}
					}
					break;
				default:
					System.out.println("잘못된 입력");
					break;
				}
						
			}

			System.out.printf("#%d ", test_case);
			
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		
		}
		

	}

}
