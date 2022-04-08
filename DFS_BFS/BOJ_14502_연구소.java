package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/14502
 * 걸린 시간 : 50분
 */
public class BOJ_14502_연구소 {
	static int N, M, ans, emptyNum;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static List<int[]> empty, virus;
	static int[] wall;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
		map = new int[N][M]; // 지도 배열
		empty = new ArrayList<int[]>(); // 빈 칸의 위치를 저장하는 리스트
		virus = new ArrayList<int[]>(); // 바이러스의 위치를 저장하는 리스트
		
		// 지도 상태 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 빈 칸이면 리스트에 위치 저장
				if (map[i][j] == 0) {
					empty.add(new int[] {i, j});
				}
				// 바이러스이면 리스트에 위치 저장
				else if (map[i][j] == 2) {
					virus.add(new int[] {i, j});
				}
				
			}
		}
		
		ans = 0; // 안전 영역의 최대 크기
		emptyNum = empty.size(); // 빈 칸의 개수
		wall = new int[3]; // 벽 3개의 인덱스를 넣을 배열
		combination(0, 0); // 벽 3개 세울 조합 구하기
		
		// 결과 출력
		System.out.println(ans);
	}

	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			// 벽 3개의 위치를 모두 정했을 경우 벽 세우기
			
			// map에 벽 3개 세우기
			for (int i = 0; i < 3; i++) {
				int x = empty.get(wall[i])[0];
				int y = empty.get(wall[i])[1];		
				
				map[x][y] = 1;
			}
			
			bfs(); // 바이러스 퍼뜨리기
			
			// 벽 3개 빈 칸으로 돌려놓기
			for (int i = 0; i < 3; i++) {
				int x = empty.get(wall[i])[0];
				int y = empty.get(wall[i])[1];		
				
				map[x][y] = 0;
			}
			
			return;
		}
		
		// 벽 3개 조합 뽑기
		for (int i = start; i < emptyNum; i++) {
			wall[cnt] = i;
			combination(cnt+1, i+1); // 다음자리는 현재 뽑은 i의 다음수부터 시작하도록 전달
		}
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		int cnt = 0; // 바이러스를 퍼뜨린 칸의 개수
		
		// 바이러스 위치 queue에 넣기
		for (int i = 0; i < virus.size(); i++) {
			int x = virus.get(i)[0];
			int y = virus.get(i)[1];
			
			queue.offer(new int[] {x, y});
			visited[x][y] = true;
		}
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int curX = current[0];
			int curY = current[1];
			
			// 4방으로 바이러스 퍼뜨리기
			for (int i = 0; i < 4; i++) {
				int nr = curX+deltas[i][0];
				int nc = curY+deltas[i][1];
				
				// 경계 안넘는지 체크, 방문 안했는지 체크, 빈 칸인지 체크
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 0) {
					cnt++; // 바이러스를 퍼뜨린 칸의 개수 1 증가
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		// 안전 영역의 개수 = (초기 빈 칸의 개수)-(벽 3개)-(바이러스를 퍼뜨린 칸의 개수)
		int res = emptyNum-3-cnt; 
		
		// 최대값이면 갱신
		ans = Math.max(ans, res);
		
	}

}
