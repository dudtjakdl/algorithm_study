package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1520
 * 걸린 시간 : 120분
*/
public class BOJ_1520_내리막_길 {
	static int N, M, ans;
	static int[][] map, dp;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 산의 세로 크기
		N = Integer.parseInt(st.nextToken()); // 산의 가로 크기
		map = new int[M][N]; // 산의 각 지점의 높이
		dp = new int[M][N]; // 현재 위치에서 제일 오른쪽 아래 위치까지 갈 수 있는 경로의 수를 저장한 동적 테이블
		
		// 산의 각 지점의 높이 입력, dp 배열 -1로 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		/* <dp배열 -1로 초기화하는 이유>
		 * 0으로 초기화하면 dfs 탐색을 할때 그 위치에 이미 방문했는데 가능한 경로가 0인 것인지,
		 * 아니면 아직 방문해보지 않아서 0인 것인지를 구분할 수가 없다.
		 * -1로 초기화하고 이미 방문한 위치를 0으로 바꾸면,
		 * 방문했는지 여부를 구분할 수가 있으므로 -1로 초기화 해주었다.
		 */
		
		dfs(0, 0); // dfs 탐색 시작
			
		// 결과 출력
		System.out.println(dp[0][0]);
	}

	private static int dfs(int curX, int curY) {
		// 현재 위치가 도착 위치이면 가능한 경로라는 뜻으로 1 반환
		if (curX == M-1 && curY == N-1) {
			return 1;
		}
		// 현재 위치를 방문했으면 이미 dp 배열에 가능한 경로의 수가 저장돼있으므로 더 이상 이동하지 않아도 됨
		// -> 현재 위치의 가능한 경로 수 반환
		else if (dp[curX][curY] != -1) {
			return dp[curX][curY];
		}
		
		dp[curX][curY] = 0; // 현재 위치의 dp 배열값 0으로 바꾸기 (방문했고, 현재 가능한 경로 수가 0이라는 뜻)
		
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nx = curX + deltas[i][0];
			int ny = curY + deltas[i][1];
			
			// 이동 위치가 경계를 넘지 않고, 내리막길일 경우
			if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[curX][curY] > map[nx][ny]) {
				dp[curX][curY] += dfs(nx, ny);
			}
		}
		
		// 현재 위치의 가능한 경로 수 반환
		return dp[curX][curY];
	}

}
