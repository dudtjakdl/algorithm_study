package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
 * 걸린 시간 : 100분
 */
public class SWEA_1249_보급로 {
	public static class Position {
		int x, y; // 현재 위치 좌표

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, ans;
	static int[][] map, dp;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방 탐색
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 지도의 가로세로 크기
			map = new int[N][N]; // 지도 배열
			
			// 지도 정보 입력
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j)-'0';
				}
			}
			
			dp = new int[N][N]; // 각 위치까지 도달하는 최소 시간 저장하는 동적 테이블
			
			// dp 배열 가장 큰 값으로 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			dp[0][0] = 0; // 시작위치 시간 0으로 초기화
			
			bfs();
		
			// 결과 출력
			System.out.println("#"+test_case+" "+dp[N-1][N-1]);
		}
	}

	private static void bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(0, 0)); // 시작위치 queue에 넣기
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = current.x+deltas[i][0];
				int nc = current.y+deltas[i][1];
				
				// 경계 넘는지 체크
				if (nr >= 0 && nr < N && nc >= 0 && nc < N ) {
					// 최소시간으로 갱신되는 이동만 queue에 넣고 dp테이블 갱신하기
					if (dp[nr][nc] > dp[current.x][current.y] + map[nr][nc]) {
						dp[nr][nc] = dp[current.x][current.y] + map[nr][nc];
						queue.offer(new Position(nr, nc));
					}
				}
			}
		}
		
	}

}
