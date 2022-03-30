package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/17144
 * 걸린 시간 : 70분
*/
public class BOJ_17144_미세먼지_안녕 {
	static int R, C, T;
	static int[][] map, temp;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방 탐색
	static int[] air1, air2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 집의 행 크기
		C = Integer.parseInt(st.nextToken()); // 집의 열 크기
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C]; // 방의 상태 배열
		temp = new int[R][C]; // 미세먼지 증감량을 저장할 임시 배열
		
		// 방의 상태 입력
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 공기청정기 위치 좌표 입력
				if(map[i][j] == -1) {
					air1 = new int[] {i-1, j}; // 위쪽 공기청정기
					air2 = new int[] {i, j}; // 아래쪽 공기청정기
				}
			}
		}
		
		// T초 만큼 반복
		for (int i = 0; i < T; i++) {
			diffuse(); // 확산 실행
			purify(); // 정화 실행
		}
		
		int ans = 0; // 방에 남아있는 미세먼지의 양
		
		// 미세먼지 양 합산하기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1) {
					ans += map[i][j];
				}
			}
		}
		
		// 결과 출력
		System.out.println(ans);
		
	}

	private static void purify() {
		// 위쪽 공기청정기 순환
		int curX = air1[0]-1;
		int curY = air1[1];
		
		int[][] deltas1 = {{-1,0},{0,1},{1,0},{0,-1}}; // 상우하좌
		outer: for (int i = 0; i < 4; i++) {

			while(true) {
				int nr = curX + deltas1[i][0];
				int nc = curY + deltas1[i][1];
				
				if(nr < 0 || nr >= air2[0] || nc < 0 || nc >= C) break;
				if(map[nr][nc] == -1) {
					map[curX][curY] = 0;
					break outer;
				}
				
				map[curX][curY] = map[nr][nc];
				curX = nr;
				curY = nc;
			}
		}
		
		// 아래쪽 공기청정기 순환
		curX = air2[0]+1;
		curY = air2[1];
		
		int[][] deltas2 = {{1,0},{0,1},{-1,0},{0,-1}}; // 하우상좌
		outer: for (int i = 0; i < 4; i++) {

			while(true) {
				int nr = curX + deltas2[i][0];
				int nc = curY + deltas2[i][1];
				
				if(nr < air2[0] || nr >= R || nc < 0 || nc >= C) break;
				if(map[nr][nc] == -1) {
					map[curX][curY] = 0;
					break outer;
				}
				
				map[curX][curY] = map[nr][nc];
				curX = nr;
				curY = nc;
			}
		}
	}

	private static void diffuse() {
		// 각 칸의 미세먼지 확산시키기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] != 0 && map[r][c] != -1) {
					int val = map[r][c] / 5; // 확산시킬 미세먼지 양
					
					// 4방으로 확산시키기
					for (int i = 0; i < 4; i++) {
						int nr = r+deltas[i][0];
						int nc = c+deltas[i][1];
						
						if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
							temp[nr][nc] += val;
							temp[r][c] -= val;
						}
					}
				}
			}
		}
		
		// temp에 저장해둔 미세먼지 증감량 map에 반영하기
		// temp 0으로 다시 초기화
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] += temp[r][c];
				temp[r][c] = 0;
			}
		}
		
	}

}
