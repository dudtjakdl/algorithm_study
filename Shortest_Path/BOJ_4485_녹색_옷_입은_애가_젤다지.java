package Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/4485
 * 걸린 시간 : 30분
 * 리뷰 : 일반적인 그래프 문제처럼 간선이 주어진게 아니라 인접 방향이 4방이어서 처음에는 당황한 문제였다.
 * 하지만 각 칸을 정점이라 생각하고 4방을 인접 정점이라 정의해서, 다익스트라 알고리즘을 통해 인접방향의 최소비용을 계산해주었다.
 * 그리고 자신의 칸에 적힌 도둑루피의 값이 비용(가중치)에 해당하므로, 처음 시작할때 시작 위치의 비용을 먼저 더해준 상태에서
 * 경로 찾기를 진행해야한다는 것이 중요한 문제였다.
*/
public class BOJ_4485_녹색_옷_입은_애가_젤다지 {

	static class Vertex implements Comparable<Vertex>{
		int x, y, minCost; // 행과 열 위치, 출발지에서 자신으로의 최소비용

		public Vertex(int x, int y, int minDistance) {
			super();
			this.x = x;
			this.y = y;
			this.minCost = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minCost - o.minCost;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 0; // 테스트 케이스
		int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
		
		while(true) { // 테스트케이스 수만큼 실행
			int N = Integer.parseInt(br.readLine()); // 동굴의 크기 
			if(N == 0) break; // N이 0이면 프로그램 종료
			int[][] map = new int[N][N]; // 동굴의 각 칸을 입력할 배열
			
			// 도둑루피 크기 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] distance = new int[N][N]; // 출발지에서 자신으로 오는 최소비용
			boolean[][] visited = new boolean[N][N]; // 최소비용 확정여부
			PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
			
			for (int i = 0; i < N; i++) { // 최소비용 초기화
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			
			distance[0][0] = map[0][0]; // 출발위치의 최소비용 -> 출발 칸의 도둑루피값
			pQueue.offer(new Vertex(0, 0, distance[0][0]));
			
			while(!pQueue.isEmpty()) {
				Vertex current = pQueue.poll(); // 최소비용이 확정되지 않은 칸 중 최소비용의 칸
				
				if(visited[current.x][current.y]) continue; // 이미 최소비용이 확정된 칸은 pass
				
				visited[current.x][current.y] = true; // 현재 칸 최소비용 확정여부 체크
				
				// 상하좌우로 이동
				for (int i = 0; i < 4; i++) {
					int nr = current.x + deltas[i][0];
					int nc = current.y + deltas[i][1];
					
					// 경계체크 && 최소비용 확정여부 체크 && 이동칸으로의 이동이 현재 최소비용보다 작은지 체크
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] &&
							distance[nr][nc] > distance[current.x][current.y] + map[nr][nc]) {
						distance[nr][nc] = distance[current.x][current.y] + map[nr][nc];
						pQueue.offer(new Vertex(nr, nc, distance[nr][nc]));
					}
				}
			}
			
			System.out.println("Problem "+(++T)+": "+distance[N-1][N-1]); // 결과 출력 (가장 마지막 위치의 최소비용만 출력)
		}
		

	}

}
