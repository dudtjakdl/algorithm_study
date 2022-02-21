package DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1260
 * 걸린 시간 : 10분
 * 리뷰 : 원래풀이는 간선의 정보를 표현하기 위해 hashMap을 이용하여 인접 정점을 리스트로 관리하였는데,
 * 좀 더 보편적인 방법인 인접 행렬을 사용하여 간선 정보를 저장해보았다.
*/
public class BOJ_1260_DFS와_BFS_다른풀이 {
	static int N, M, V;
	static int[][] map;
	static boolean[] visited; // 정점 방문체크를 위한 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점의 개수
		M = sc.nextInt(); // 간선의 개수
		V = sc.nextInt(); // 탐색을 시작할 정점의 번호
		map = new int[N+1][N+1]; // 정점 인접 행렬
		
		// 간선 연결 -> 양방향이라 하였으므로 두 정점을 서로 연결
		for(int i=0; i<M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			map[node1][node2] = 1;
			map[node2][node1] = 1;
		}
		
		// dfs 시작
		visited = new boolean[N+1];
		dfs(V);
		
		System.out.println();
		
		// bfs 시작
		visited = new boolean[N+1];
		bfs();
	}
	
	// 깊이 우선 탐색
	static void dfs(int current) { // current: 현재 방문하고 있는 정점 번호
		visited[current] = true; // 방문함 체크
		System.out.print(current + " ");
		
		// 현재 방문하고 있는 정점에 연결된 정점을 방문
		for(int i=1; i<=N; i++) {
			if(!visited[i] && map[current][i] != 0) {
				dfs(i);
			}
		}
	}
	
	// 너비 우선 탐색
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.offer(V); // 최초로 탐색을 시작할 정점 번호 넣기
		visited[V] = true; // 방문함 체크

		while(!queue.isEmpty()) {
			int current = queue.poll(); // 현재 방문하고 있는 정점 번호
			System.out.print(current + " ");
			
			// 현재 방문하고 있는 정점에 연결되어 있는 정점을 queue에 넣기
			for(int i=1; i<=N; i++) {
				if(!visited[i] && map[current][i] != 0) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

}
