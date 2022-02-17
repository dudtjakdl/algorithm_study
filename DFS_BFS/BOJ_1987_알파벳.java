package DFS_BFS;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1987
 * 걸린 시간 : 30분
 * 리뷰 : 알파벳을 지나왔는지 체크하는 것을 어떻게 하는지가 중요한 문제였던 것 같다.
 * 대문자 알파벳의 개수는 26개이고 (char문자-char문자) 의 값은 정수값이 나오니,
 * (대문자 알파벳 -'A') 를 통해 해당 알파벳의 인덱스 값을 구했다.
 * 그리고 이 인덱스 값을 이용하여 알파벳 사용 체크를 통해 깊이 우선 탐색을 구현할 수 있었다.
*/
public class BOJ_1987_알파벳 {
	static int R, C;
	static char[][] map;
	static boolean[] isSelected;
	static boolean[][] visited;
	static int res; // 결과값
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우 순
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt(); // 보드의 세로 길이
		C = sc.nextInt(); // 보드의 가로 길이
		map = new char[R][C]; // 보드의 적힌 알파벳을 저장할 배열
		isSelected = new boolean[26]; // 대문자 알파벳 26개를 지나왔는지를 체크하는 배열
		visited = new boolean[R][C]; // 보드 각 칸을 방문했는지를 체크하는 배열
		
		// 보드 각 칸의 알파벳 입력
		for (int i = 0; i < R; i++) {
			String s = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		visited[0][0] = true; // 출발 위치 방문 체크
		isSelected[map[0][0]-'A'] = true; // 출발 위치 알파벳 사용 체크
		
		dfs(1, 0, 0); // 깊이 탐색 시작
		
		System.out.println(res); // 결과 출력
	}

	/**
	 * @param cnt : 현재까지 지나온 칸 수
	 * @param row : 현재 위치의 행
	 * @param col : 현재 위치의 열
	 */
	private static void dfs(int cnt, int row, int col) {
		
		// 상하좌우로 이동
		for (int i = 0; i < 4; i++) {
			int nr = row+deltas[i][0];
			int nc = col+deltas[i][1];
			
			// 경계를 넘는지와 이미 방문했는지 체크
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && visited[nr][nc] == false) { 
				char alpha = map[nr][nc]; // 이동방향에 적힌 알파벳
				
				if(!isSelected[alpha-'A']) { // 이동방향의 알파벳을 지나오지 않았으면 이동
					visited[nr][nc] = true; // 이동방향 방문 체크
					isSelected[alpha-'A'] = true; // 이동방향 알파벳 사용 체크
					
					dfs(cnt+1, nr, nc);
					
					visited[nr][nc] = false;
					isSelected[alpha-'A'] = false;
				}
			}
		}
		
		// 여기로 왔으면 더 이상 이동할 위치가 없어서 이동이 끝났다는 뜻
		res = Math.max(res, cnt); // 현재까지 이동한 횟수 최대값인지 체크
	}

}
