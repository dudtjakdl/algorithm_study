package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/19236
 * 걸린 시간 : 150분
 * 리뷰 : 물고기가 각각 이동하면 물고기 위치 좌표가 계속 변하므로 Fish 객체배열로 물고기 위치 정보를 관리해주었다.
 * 상어가 이동할 수 있는 경우의 수가 여러가지이므로 이 경우를 모두 탐색하는
 * dfs 탐색을 이용하여 상어가 먹을 수 있는 물고기 번호의 합을 모두 구해 최댓값을 구해주었다.
 * 여기서 중요한 것이 dfs 탐색으로 모든 경우를 탐색할 때마다 map과 fishes 배열은 항상 변하므로 이를 계속 복사해주었다.
 */
public class BOJ_19236_청소년_상어 {
	static class Fish {
		int x, y; // 위치 좌표
		int dir; // 방향
		int sum; // 먹은 물고기 번호의 합
		
		public Fish(int x, int y, int dir, int sum) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.sum = sum;
		}
	}
	
	static int ans;
	static int[][] deltas = {{0,0},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[4][4]; // 4x4 공간 배열
		Fish[] fishes = new Fish[17]; // 물고기 정보 배열 0: 상어, 1~16: 1번~16번 물고기
		
		// 물고기 정보 입력
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int no = Integer.parseInt(st.nextToken()); // 물고기 번호
				int dir = Integer.parseInt(st.nextToken()); // 물고기 방향
				map[i][j] = no;
				fishes[no] = new Fish(i, j, dir, 0);
			}
		}
		
		Fish shark = new Fish(0, 0, fishes[map[0][0]].dir, 0); // 상어 인스턴스 초기화
		fishes[0] = shark; // 0번째 물고기에 상어 추가
		fishes[map[0][0]] = null; // (0,0) 위치에 있던 물고기 잡아먹힘 표시
		fishes[0].sum += map[0][0]; // 상어가 잡아먹은 물고기 번호 더하기
		map[0][0] = 0; // 맵의 (0,0) 위치에 상어 표시
		
		ans = 0; // 문제의 답 0으로 초기화
		
		move(fishes, map); // 물고기 이동
		
		System.out.println(ans); // 결과 출력
	}
	
	private static Fish[] copyFishes(Fish[] origin) {
		Fish[] copy = new Fish[17];
		
		// clone으로 복사하면 배열 안의 Fish 객체는 복사가 되지 않음!!
//		copy = origin.clone();
		
		// 0번부터 16번 객체 복사
		for (int i = 0; i <= 16; i++) {
			Fish temp = origin[i];
			if (temp == null) copy[i] = null;
			else copy[i] = new Fish(temp.x, temp.y, temp.dir, temp.sum);
		}
		
		return copy;
	}
	
	private static int[][] copyMap(int[][] origin) {
		int[][] copy = new int[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}

	// 상어가 이동하면서 물고기를 먹는 함수
	private static void eat(Fish[] fishes, int[][] map) {
		Fish shark = fishes[0];
		
		int x = shark.x;
		int y = shark.y;
		int cnt = 0; // 상어가 이동할 수 있는 칸의 개수
		
		while (true) {
			int dx = x + deltas[shark.dir][0];
			int dy = y + deltas[shark.dir][1];
			
			// 이동방향이 경계를 벗어났으면 break;
			if (dx < 0 || dx >= 4 || dy < 0 || dy >= 4) break;
			
			// 이동방향에 물고기가 있으면 이동 가능
			if (map[dx][dy] != -1) {
				// 물고기 먹기
				Fish[] tempFishes = copyFishes(fishes); // fishes 배열 복사
				int[][] tempMap = copyMap(map); // map 배열 복사
				
				// 상어 정보 업데이트
				tempFishes[0].sum += map[dx][dy]; // 먹은 물고기번호 더하기
				tempFishes[0].dir = fishes[map[dx][dy]].dir; // 상어 방향 바꾸기
				tempFishes[0].x = dx;
				tempFishes[0].y = dy;
				tempFishes[map[dx][dy]] = null; // 먹힌 물고기 정보는 null로 바꾸기
				
				// 맵 정보 업데이트
				tempMap[shark.x][shark.y] = -1; // 초기 상어위치 빈칸으로 바꾸기
				tempMap[dx][dy] = 0; // 이동방향 위치를 상어로 바꾸기
				
				move(tempFishes, tempMap); // 물고기 이동
				
				cnt++;
			}
			
			x = dx;
			y = dy;
		}
		
		// 상어가 이동할 수 없으면 지금까지 먹은 물고기 번호의 합 계산
		if (cnt == 0) {
			ans = Math.max(ans, fishes[0].sum);
		}
	}

	// 맵의 물고기가 모두 이동하는 함수
	private static void move(Fish[] fishes, int[][] map) {
		// 1번~16번 물고기 모두 이동
		for (int i = 1; i <= 16; i++) {
			// i번째 물고기가 이미 잡아먹혔으면 pass
			if (fishes[i] == null) continue;
			
			int x = fishes[i].x;
			int y = fishes[i].y;
			int dir = fishes[i].dir;
			
			// 이동할 수 있을 때까지 방향을 45도 반시계 방향으로 바꾸기
			for (int j = 0; j < 8; j++) {
				int chDir = dir + j; // 바뀐 방향
				if (chDir > 8) chDir = chDir%8;
				int dx = x + deltas[chDir][0];
				int dy = y + deltas[chDir][1];

				// 이동방향이 경계를 벗어나지 않고, 이동방향에 상어가 없을 경우
				if (dx >= 0 && dx < 4 && dy >= 0 && dy < 4 && map[dx][dy] != 0) {
					// 이동방향이 빈칸일 경우 -> 바로 이동
					if (map[dx][dy] == -1) {
						// 맵 정보 업데이트
						map[x][y] = -1; // 원래 물고기 위치는 빈칸으로
						map[dx][dy] = i; // 이동 위치에 물고기 번호 저장
					}
					// 이동방향에 다른 물고기가 있을 경우 -> swap
					else {
						// 맵 정보 업데이트
						int temp = map[dx][dy];
						map[dx][dy] = i;
						map[x][y] = temp;
						// swap한 물고기 정보 업데이트
						fishes[temp].x = x;
						fishes[temp].y = y;
					}
					// 물고기 정보 업데이트
					fishes[i].x = dx;
					fishes[i].y = dy;
					fishes[i].dir = chDir;
					
					break;
				}
			}

		}
		
		eat(fishes, map); // 상어 이동하기
	}

}
