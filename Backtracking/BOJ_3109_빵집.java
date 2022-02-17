package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/3109
 * 걸린 시간 : 100분
 * 리뷰 : 깊이 탐색으로 모든 경우의 수를 다 해보면 시간초과로 틀리는 문제이다.
 * 그러므로 백트래킹 기법을 생각해서, 무조건 실패하는 뒤의 선택지는 가지치기를 통해
 * 똑같은 시도를 반복하지 않도록 해주는 것이 핵심 키포인트인 문제였다.
 * 또한 최대한 파이프라인을 많이 놓는 횟수를 구하는 것이기 때문에 그리디적인 선택으로
 * 오른쪽위->오른쪽->오른쪽아래 순으로 탐색을 해줘야하는 것도 중요한 사항이었던 문제이다.
*/
public class BOJ_3109_빵집 {

	static int R, C;
	static char[][] map;
	static int[][] deltas = {{-1,1},{0,1},{1,1}}; // 오른쪽위,오른쪽,오른쪽아래 이동 좌표
	static boolean[][] visited;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 전체 행 수
		C = Integer.parseInt(st.nextToken()); // 전체 열 수
		map = new char[R][C]; // 빵집 근처의 모습을 저장하는 배열
		visited = new boolean[R][C]; // 파이프가 이미 놓여져있는지 체크하는 배열
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				// 건물('x')이면 파이프 설치 못하게 체크 배열 true로 바꾸기
				if(s.charAt(j) == 'x') visited[i][j] = true;
				map[i][j] = s.charAt(j);
			}
		}
		
		// 깊이 탐색 시작
		for(int i=0; i<R; i++) {
			visited[i][0] = true;
			setPipe(i, 0);
		}
		
		System.out.println(res);
	}
	
	// 다음 열에 가스관을 설치하는 함수
	private static boolean setPipe(int row, int col) {
		if(col == C-1) { // 마지막 열에 도착(빵집에 도착)했으면 파이프라인 놓기 성공
			res++; // 파이프라인 개수 1 증가
			return true;
		}

		// 오른쪽위,오른쪽,오른쪽아래에 가스관을 설치할 수 있는지 체크
		for(int i=0; i<3; i++) {
			int nr = row + deltas[i][0];
			int nc = col + deltas[i][1];
			
			// 경계체크 및 방문체크
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && visited[nr][nc] == false) {
				visited[nr][nc] = true; // 가스관 설치 방문 체크
				if(setPipe(nr, nc)) { // 파이프라인 만들기 성공이면 다른 경우는 안해보고 끝내기
					return true;
				};
				
				// 방문처리 해제는 해주지 않아도 됨 -> 이미 실패했던 뒤의 선택지는 무조건 실패이니
				// 같은 상황을 반복하지 않도록 되돌려놓지 않는다.
				// visited[nr][nc] = false;
			}
		}
		// 3방향 모두 가스관을 놓지 못하면 파이프라인 놓기 실패
		return false;
	}
	
	

}
