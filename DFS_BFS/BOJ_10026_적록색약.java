package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/10026
 * 걸린 시간 : 30분
 * 리뷰 : 처음 입력받은 원본 그림으로 dfs를 통해 구역 개수를 구해주고,
 * 원본 그림의 G를 전부 R로 바꿔서 적록색약인 사람의 시점으로 한번 더 dfs로 구역의 개수를 구해주면 되는 문제였다.
*/
public class BOJ_10026_적록색약 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	static int res1, res2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 그리드의 크기
		map = new char[N][N]; // N×N 크기로 초기화
		
		// 그림 입력
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[N][N]; // 방문 체크 배열 초기화
		
		// map의 처음부터 끝까지 접근 -> 평범한 사람이 봤을때 구역 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) { 
					bfs(i, j, map[i][j]); // 방문하지 않았으면 현재 위치를 시작으로 너비 우선 탐색 시작
					res1++; // 구역의 개수 1 증가
				}
			}
		}
		
		// map의 'G'를 전부 'R'로 바꾸기 -> 적록 구분을 하지 못하게 하기 위함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'G') map[i][j] = 'R';
			}
		}
		
		visited = new boolean[N][N]; // 방문 체크 배열 초기화
		
		// 위와 똑같은 과정 반복 -> 적록색약인 사람이 봤을때 구역 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) { 
					bfs(i, j, map[i][j]);
					res2++;
				}
			}
		}
		
		System.out.println(res1+" "+res2); // 결과 출력
	}

	/**
	 * @param startRow : 탐색 시작 위치 (행)
	 * @param startCol : 탐색 시작 위치 (열)
 	 * @param target : 탐색 타겟 알파벳 (R,G,B)
	 */
	private static void bfs(int startRow, int startCol, int target) {
		Queue<int[]> queue = new LinkedList<int[]>(); // 탐색 순서를 관리할 queue
		
		queue.offer(new int[] {startRow, startCol}); // 시작 위치 queue에 넣기
		visited[startRow][startCol] = true; // 시작 위치 방문 체크
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int curRow = temp[0]; // 현재 위치 (행)
			int curCol = temp[1]; // 현재 위치 (열)
			
			// 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				int nr = curRow+deltas[i][0];
				int nc = curCol+deltas[i][1];
				
				// 이동 위치 경계체크 && 방문 했는지 체크 && target 알파벳과 같은지 체크
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == target) {
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}

}
