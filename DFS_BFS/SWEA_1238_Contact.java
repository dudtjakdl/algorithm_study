package DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
 * 걸린 시간 : 30분
 * 리뷰 : 백준 7576번 문제처럼 bfs 탐색을 수행하면서 같은 너비에 있는 정점들을 동시방문처리 해야한다는 점이 비슷한 문제였다.
 * 정점 번호의 최소와 최대값이 정해져있어 간선의 정보를 저장하기 위해 인접 행렬을 사용하였다.
 * 하지만 간선의 개수가 최대 간선 개수보다 훨씬 적어서 메모리 낭비를 방지하기 위해 행렬보다는 인접 리스트를 만드는게 더 좋은 방법인 것 같다.
 */
public class SWEA_1238_Contact {
	static int N;
	static int[][] map;
	static int res; // 가장 마지막에 연락을 받게 되는 사람 중 번호가 가장 큰 사람 (결과값)
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int test_case=1; test_case<=10; test_case++) {
			N = sc.nextInt()/2; // 간선의 개수
			int start = sc.nextInt(); // 시작점
			
			map = new int[101][101]; // 인접 행렬 초기화
			
			// 비상연락망 연결 (정점 연결)
			for(int i=0; i<N; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				map[from][to] = 1;
			}

			bfs(start); // 시작점부터 너비 우선 탐색 시작
			
			System.out.println("#"+test_case+" "+res); // 결과 출력
		}

	}

	/**
	 * @param start : 탐색을 시작할 사람의 번호
	 */
	private static void bfs(int start) { // 너비 우선 탐색
		Queue<Integer> queue = new LinkedList<>(); // 탐색 순서를 관리할 queue
		boolean[] visited = new boolean[101]; // 방문 체크 배열
		
		queue.offer(start); // 맨 처음 시작점 queue에 넣기
		visited[start] = true; // 시작점 방문 체크
		
		while(!queue.isEmpty()) {
			int size = queue.size(); // 현재 너비에서 방문할 정점의 수
			res = queue.peek(); // 현재 너비에서 가장 큰 번호를 찾기위한 초기화

			// 현재 너비에 방문해야하는 정점 수 만큼만 방문 
			while(--size>=0) {
				int temp = queue.poll();
				res = Math.max(res, temp); // 현재 방문한 정점 번호가 가장 큰지 아닌지 체크
				
				// 인접한 정점 queue에 넣기
				for (int i = 1; i <= 100; i++) {
					if(!visited[i] && map[temp][i] == 1) {
						queue.offer(i);
						visited[i] = true;
					}
				}
			}
		}
	}

}
