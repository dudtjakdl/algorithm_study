package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1194
 * 걸린 시간 : 90분
 * 리뷰 : 현재 가지고 있는 키의 상태(조합)를 나타내주기 위해 비트마스킹을 사용하였다.
 * 비트마스킹을 통해 키의 획득 및 조회를 쉽게 구현할 수 있었다.
 * 그리고 그 키 비트열값에 따라 방문체크를 따로 해주어야 하므로 3차원 형태의 방문체크 배열을 사용하였다.
*/
public class BOJ_1194_달이_차오른다_가자 {
	public static class Position {
		int x, y; // 현재 위치 좌표
		int cnt; // 이동 횟수
		int key; // 가지고 있는 키 비트열
		
		public Position(int x, int y, int cnt, int key) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}
		
	}
	
	static int N, M, ans;
	static char[][] map;
	static boolean[][][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방 탐색
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기
		map = new char[N][M]; // 미로 배열
		visited = new boolean [64][N][M]; // 가지고 있는 열쇠 조합마다 방문체크 해줄 배열
		
		int startRow = 0; // 시작위치 행
		int startCol = 0; // 시작위치 열
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				// 시작위치 좌표 저장
				if(map[i][j] == '0') {
					startRow = i;
					startCol = j;
					map[i][j] = '.'; // 빈 칸으로 바꾸기
				}
			}
		}
		
		ans = -1; // 결과값 -1로 초기화
		bfs(startRow, startCol);
		
		System.out.println(ans);
	}

	private static void bfs(int startRow, int startCol) {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(startRow, startCol, 0, 0)); // 시작위치 queue에 넣기
		visited[0][startRow][startCol] = true; // 시작위치 방문체크
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			
			// 상하좌우 이동할 수 있으면 queue에 넣기
			for (int i = 0; i < 4; i++) {
				int nr = current.x+deltas[i][0];
				int nc = current.y+deltas[i][1];
				
				// 경계 안넘는지 체크, 이미 방문했는지 체크, 벽이 아닌지 체크
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[current.key][nr][nc] && map[nr][nc] != '#') {
					// 출구이면 이동횟수 결과값에 저장하고 bfs 바로 종료
					if (map[nr][nc] == '1') {
						ans = current.cnt + 1;
						return;
					}
					// 빈 칸이면 방문체크하고 이동
					else if (map[nr][nc] == '.') {
						visited[current.key][nr][nc] = true;
						queue.offer(new Position(nr, nc, current.cnt+1, current.key));
					}
					// 열쇠이면 열쇠 획득 처리하고 이동
					else if (map[nr][nc] == 'a' || map[nr][nc] == 'b' || map[nr][nc] == 'c' 
							|| map[nr][nc] == 'd' || map[nr][nc] == 'e' || map[nr][nc] == 'f') {

						int idx = map[nr][nc] - 'a'; // 비트마스킹 처리할 자리수
						int flag = current.key | 1 << idx; // 열쇠 조합 비트열
						
						visited[current.key][nr][nc] = true;
						visited[flag][nr][nc] = true;
						queue.offer(new Position(nr, nc, current.cnt+1, flag));
					}
					// 문이면 해당 열쇠를 가지고 있으면 이동
					else if (map[nr][nc] == 'A' || map[nr][nc] == 'B' || map[nr][nc] == 'C' 
							|| map[nr][nc] == 'D' || map[nr][nc] == 'E' || map[nr][nc] == 'F') {
					
						int idx = map[nr][nc] - 'A'; // 비트마스킹 처리할 자리수
						
						if ((current.key & 1 << idx) != 0) {
							// idx번째 열쇠를 가지고 있으면 이동하기
							visited[current.key][nr][nc] = true;
							queue.offer(new Position(nr, nc, current.cnt+1, current.key));
						}
					}
				}
			}
		}
	}
}
