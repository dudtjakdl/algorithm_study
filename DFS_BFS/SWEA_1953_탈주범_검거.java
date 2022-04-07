package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
 * 걸린 시간 : 100분
 */
public class SWEA_1953_탈주범_검거 {
	public static class Tunnel {
		int x, y; // 터널의 위치 좌표
		int type; // 터널 구조물 타입 번호
		
		public Tunnel(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	static int N, M, R, C, L, ans;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우 순서
	static int[][] tunnelType = { // 터널 타입별 이동가능한 방향 
			{},
			{0,1,2,3},
			{0,1},
			{2,3},
			{0,3},
			{1,3},
			{1,2},
			{0,2}
	};
	static int[][] connect = {{1,2,5,6},{1,2,4,7},{1,3,4,5},{1,3,6,7}}; // 각각 상하좌우로 이동할때 이동가능한 터널타입 번호
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
			M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 세로 위치
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			map = new int[N][M]; // 지하 터널 지도 배열
			
			// 지도 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 1; // 문제의 답 1로 초기화 (맨홀에서 무조건 위치할 수 있으니)
			bfs();
			
			// 결과 출력
			System.out.println("#"+test_case+" "+ans);
		}
		
	}

	private static void bfs() {
		Queue<Tunnel> queue = new LinkedList<Tunnel>();
		boolean[][] visited = new boolean[N][M];
		int time = 0; // 경과된 시간
		
		visited[R][C] = true; // 맨홀 위치 방문체크
		queue.offer(new Tunnel(R, C, map[R][C])); // 맨홀 위치 시작위치로 queue에 넣기
		
		// 시간이 L이 될때까지 bfs탐색 수행
		while (++time != L) {
			int size = queue.size();

			while(--size >= 0) {
				Tunnel current = queue.poll();
				int type = current.type;
				
				// 현재 위치에 해당하는 터널 타입별 이동가능한 방향으로만 이동 
				for (int i : tunnelType[type]) {
					int nr = current.x + deltas[i][0];
					int nc = current.y + deltas[i][1];
					
					// 경계 체크, 방문여부 체크, 이동방향이 터널인지 체크
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 0) {
						// 이동방향에 있는 터널과 현재 터널이 이어지는지 체크
						for (int j : connect[i]) {
							// 이어지면 문제의 답 1 증가시키고 방문체크 및 queue에 넣기
							if(map[nr][nc] == j) {
								ans++;
								visited[nr][nc] = true;
								queue.offer(new Tunnel(nr, nc, map[nr][nc]));
								break;
							}
						}
					}
				}
			}
			
		}

	}

}
