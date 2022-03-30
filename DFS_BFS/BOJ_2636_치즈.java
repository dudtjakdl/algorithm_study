package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2636
 * 걸린 시간 : 50분
 * 리뷰 : 외부 공기에 해당하는 칸을 기준으로 탐색을 시작하여 치즈를 만나면 그 치즈를 녹여주면 되는 문제였다.
 * 여기서 중요한 점은 치즈를 녹일 수 있다 해서 바로 0으로 바꾸면 안되고 (미리 녹이면 탐색에 영향을 미치기 때문)
 * 한 타임 탐색이 끝난 후 한꺼번에 녹여주어야 한다.
 * 그래서 일단 치즈를 녹일 수 있으면 값을 2로 바꿔두었다가 한 타임 dfs가 끝난 후 2를 0으로 바꿔주었다.
*/
public class BOJ_2636_치즈 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방 탐색
	static int time, cnt; // time: 치즈가 모두 녹는데 걸리는 시간, cnt: 현재 남아있는 치즈 조각의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 판의 세로 길이
		M = Integer.parseInt(st.nextToken()); // 판의 가로 길이
		map = new int[N][M]; // 사각형 모양 판
		
		// 판의 값 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cnt++; // 치즈조각의 개수 1 증가
			}
		}
		
		int preCnt = cnt; // 1시간 전에 남아있던 치즈조각 개수
				
		while(true) {
			if(check()) {
				visited = new boolean[N][M]; // 방문체크 배열 초기화
				visited[0][0] = true; // 시작위치 방문체크
				dfs(0, 0); // (0,0)부터 깊이우선탐색 시작
				preCnt = cnt; // 치즈조각 개수 임시 저장
				time++; // 1시간 증가
			}
			else break;
		}
		
		// 결과 출력
		System.out.println(time);
		System.out.println(preCnt);
	}

	// 치즈를 녹이고 치즈가 전부 녹았는지 아닌지 여부를 반환하는 함수
	private static boolean check() {
		// 녹을 수 있는 치즈 전부 녹이기 (배열 값 2를 0으로 바꾸기)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 2) {
					map[i][j] = 0;
					cnt--; // 남은 치즈조각 개수 1 감소
				}
			}
		}
		
		// 남은 치즈조각이 0개면 false 반환
		if(cnt == 0) return false;
		// 치즈조각이 남았으면 true 반환
		else return true;
	}

	private static void dfs(int curRow, int curCol) {
		if(map[curRow][curCol] == 1) {
			// 현재 위치가 치즈조각이면 2로 바꾸기 (한시간 후에 녹을 치즈라는 의미)
			map[curRow][curCol] = 2;
			return;
		}
		
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nr = curRow + deltas[i][0];
			int nc = curCol + deltas[i][1];
			
			if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true; // 다음 방문 위치 방문체크
				dfs(nr, nc);
			}
		}
		
	}
	
	

}
