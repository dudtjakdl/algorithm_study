package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1600
 * 걸린 시간 : 150분
 * 리뷰 : 처음에는 dfs 탐색으로 접근을 했는데 시간초과가 떴다. dfs는 하나의 경우의 수를 파고들어
 * 도착점에 도착할 때까지 계속 재귀호출을 하지만, bfs탐색을 하면 이동횟수가 적은 순서부터 탐색을 하므로,
 * 이 문제는 무조건 bfs로 풀어야 한다는걸 깨달았다.
 * 하지만 그래도 계속 틀렸다고 떴는데, 말의 이동방법을 총 몇번 했는지에 따라 방문체크를 다르게 해주어야 한다는걸 간과하였다.
 * 그래서 방문체크 배열을 복사하는 방법으로 고쳐보았지만 메모리 초과가 뜨고..
 * 다른 사람의 풀이를 보고 방문체크 배열을 3차원으로 선언하여 풀이한 방법을 참고하였다.
*/
public class BOJ_1600_말이_되고픈_원숭이 {
	public static class Position {
		// x: 행 좌표 y: 열 좌표
		// k: 말의 이동방법을 총 몇번 했는지 cnt: 총 이동횟수
		int x, y, k, cnt;

		public Position(int x, int y, int k, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}
	}
	
	static int K, W, H, res;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] deltas1 = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	static int[][] deltas2 = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}}; // 말 이동방법
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 말의 이동방법으로 이동할 수 있는 횟수
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken()); // 격자판의 가로길이
		H = Integer.parseInt(st.nextToken()); // 격자판의 세로길이
		map = new int[H][W]; // 격자판 배열
		
		// 격자판 값 입력
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = -1; // 동작수의 최솟값 초기화
		// 말의 이동방법을 총 몇번 했는지에 따라 방문체크 다르게 해줘야함 -> 3차원으로 방문체크 배열 선언
		visited = new boolean[K+1][H][W]; 
		bfs(); // bfs 탐색 시작
		
		// 결과 출력
		if(H == 1 && W == 1) System.out.println(0); // 출발점과 도착점이 같으면 0 출력
		else System.out.println(res);
	}

	
	private static void bfs() {
		Queue<Position> queue = new LinkedList<>();
		visited[0][0][0] = true; // 시작위치 방문체크
		queue.add(new Position(0, 0, 0, 0)); // 시작위치 queue에 넣기
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
				
			// 상하좌우로 이동
			for (int i = 0; i < 4; i++) {
				int nr = current.x + deltas1[i][0];
				int nc = current.y + deltas1[i][1];
					
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[current.k][nr][nc] && map[nr][nc] == 0) {
					if(nr == H-1 && nc == W-1) { 
						// 이동위치가 도착점이면 총 이동횟수 저장 후 bfs 탐색 종료
						res = current.cnt+1;
						return;
					}
					
					visited[current.k][nr][nc] = true; // 이동위치 방문체크
					queue.add(new Position(nr, nc, current.k, current.cnt+1));
				}
			}
			
			// 현재까지 말 이동방법 횟수가 K보다 작을경우
			if (current.k < K) {
				// 말 이동방법으로 이동
				for (int i = 0; i < 8; i++) {
					int nr = current.x + deltas2[i][0];
					int nc = current.y + deltas2[i][1];
						
					if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[current.k+1][nr][nc] && map[nr][nc] == 0) {
						if(nr == H-1 && nc == W-1) {
							// 이동위치가 도착점이면 총 이동횟수 저장 후 bfs 탐색 종료
							res = current.cnt+1;
							return;
						}
						
						visited[current.k+1][nr][nc] = true; // 이동위치 방문체크
						queue.add(new Position(nr, nc, current.k+1, current.cnt+1));
					}
				}
			}

		}
		
	}

}
