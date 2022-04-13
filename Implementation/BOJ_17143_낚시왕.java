package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/17143
 * 걸린 시간 : 180분
 * 리뷰 : 계속 틀렸다고 나왔는데 아무리 봐도 잘못된 점을 찾지 못했었다.
 * 알고보니 이미 다른 상어가 위치한 경우와 위치하지 않은 경우를 if-else로 분기를 나눠서 처리를 해주었는데,
 * 위치하지 않은 경우에서는 상어 객체에서 dir를 갱신해주었고, 위치한 경우에서는 dir를 갱신해주지 않았다..
 * 이렇게 공통적으로 갱신해주어야 하는 경우는 따로 위에 빼서 꼭 잊지말고 해줘야겠다.
*/
public class BOJ_17143_낚시왕 {
	public static class Shark { // 상어 객체
		int speed; // 속력
		int dir; // 이동 방향
		int size; // 크기
		
		public Shark(int speed, int dir, int size) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	static int R, C, M, ans;
	static int[][] map, temp;
	static int[][] deltas = {{0,0},{-1,0},{1,0},{0,1},{0,-1}}; // 상하우좌
	static Shark[] shark;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 격자판의 세로 크기
		C = Integer.parseInt(st.nextToken()); // 격자판의 가로 크기
		M = Integer.parseInt(st.nextToken()); // 상어의 수
		map = new int[R+1][C+1]; // 격자판 배열
		shark = new Shark[10001]; // 크기에 따른 상어 객체를 저장할 배열
		
		// 상어 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = z; // 격자판에 상어 크기 저장
			shark[z] =  new Shark(s, d, z); // 크기에 해당하는 인덱스에 상어 객체 저장
		}
		
		int curCol = 0; // 낚시왕의 현재 열 위치
		
		// 오른쪽으로 한칸씩 이동하면서 끝 열이 될때까지 반복
		while (++curCol <= C) {
			// 현재 열에서 땅과 제일 가까운 상어 잡기
			for (int i = 1; i <= R; i++) {
				// 현재 위치에 상어가 있을 경우
				if(map[i][curCol] != 0) {
					ans += map[i][curCol]; // 잡은 상어 크기 더하기
					map[i][curCol] = 0; // 현재 위치 빈칸으로 바꾸기
					break;
				}
			}
			
			temp = new int[R+1][C+1]; // 상어 이동 후 상태를 저장할 임시 배열
			
			// 격자판의 모든 상어 이동하기
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					// 현재 위치에 상어가 있을 경우
					if(map[i][j] != 0) {
						move(i, j, shark[map[i][j]]); // 현재 위치의 상어 이동하기
					}
				}
			}

			copy(); // 임시 배열을 map에 반영시키기
		}

		// 결과 출력
		System.out.println(ans);
	}

	// temp를 map에 복사하는 함수
	private static void copy() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	// (curRow, curCol) 위치의 상어를 이동시키는 함수
	private static void move(int curRow, int curCol, Shark current) {
		int speed = current.speed; // 속력
		int dir = current.dir; // 이동 방향
		int size = current.size; // 상어 크기
		
		int row = curRow; // 도착 위치 행
		int col = curCol; // 도착 위치 열
		
		// 속력만큼 상어 한칸씩 이동
		while (--speed >= 0) {
			int nr = row + deltas[dir][0];
			int nc = col + deltas[dir][1];
			
			// 이동 위치가 경계를 벗어났을 경우
			if (nr < 1 || nr > R || nc < 1 || nc > C) {
				dir = changeDir(dir); // 방향 바꾸기
				// 이동 위치 바뀐 방향으로 다시 구하기
				nr = row + deltas[dir][0];
				nc = col + deltas[dir][1];
			}
			
			row = nr;
			col = nc;
		}
		
		current.dir = dir; // 상어 객체 방향 갱신
		// 도착 위치에 상어 크기 저장 -> 무조건 크기가 큰 값으로 저장 
		temp[row][col] = Math.max(size, temp[row][col]);
	}

	// 방향을 바꾸고 그 인덱스를 반환하는 함수
	private static int changeDir(int dir) {
		switch (dir) {
		case 1:
			dir = 2;
			break;
		case 2:
			dir = 1;
			break;
		case 3:
			dir = 4;
			break;
		case 4:
			dir = 3;
			break;
		}
		
		return dir;
	}

}
