package Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/11404
 * 걸린 시간 : 20분
 * 리뷰 : 입력으로 들어올 수 있는 최대 도시 개수는 100개 버스는 100000이므로,
 * 100x100000 보다 높은 값을 INF으로 정의해주어야 한다.
*/
public class BOJ_11404_플로이드 {
	static final int INF = 9999999;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 도시의 개수
		int m = Integer.parseInt(br.readLine()); // 버스의 개수
		int[][] adjMatrix = new int[n+1][n+1]; // 인접 행렬
		StringTokenizer st;

		// 버스 정보 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(adjMatrix[a][b] != 0) {
				adjMatrix[a][b] = Math.min(adjMatrix[a][b], c);
			}
			else adjMatrix[a][b] = c;
		}
		
		// 자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j && adjMatrix[i][j] == 0) {
					adjMatrix[i][j] = INF;
				}
			}
		}
		
		// 플로이드 와샬 알고리즘 적용
		for (int k = 1; k <= n; k++) { // 경유지
			for (int i = 1; i <= n; i++) { // 출발지
				for (int j = 1; j <= n; j++) { // 도착지
					if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
					}
				}
			}
		}
		
		// i에서 j로 갈 수 없는 경우 0으로 바꾸기
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (adjMatrix[i][j] == INF) {
					adjMatrix[i][j] = 0;
				}
			}
		}
		
		// 결과 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
