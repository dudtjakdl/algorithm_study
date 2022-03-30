package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/9205
 * 걸린 시간 : 40분
*/
public class BOJ_9205_맥주_마시면서_걸어가기 {
	public static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;
	static List<Position> stores;
	static Position end;
	static String ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		// 테스트 케이스만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine()); // 편의점의 개수
			st = new StringTokenizer(br.readLine(), " ");
			// 상근이네 집 위치 (출발)
			Position start = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			stores = new ArrayList<>(); // 편의점 위치를 담을 리스트
			
			// 편의점 위치 리스트에 추가
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				Position temp = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				stores.add(temp);
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			// 페스티벌 위치 (도착)
			end = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			ans = "sad"; // 결과값 초기화
			
			visited = new boolean[n]; // 편의점 방문 체크 배열
			dfs(start); // 출발점부터 dfs 탐색 시작
			
			System.out.println(ans); // 결과 출력
		}
	
	}

	private static void dfs(Position current) { // current: 현재 위치
		if (isPossible(current, end)) {
			// 현재 위치에서 페스티벌로 갈 수 있으면 결과값 happy로 바꾸고 종료
			ans = "happy";
			return;
		}
		
		// 편의점으로 이동하기
		for (int i = 0; i < stores.size(); i++) {
			// 편의점으로 이동 가능하고, 방문하지 않았으면 방문체크하고 이동하기
			if(isPossible(current, stores.get(i)) && !visited[i]) {
				visited[i] = true;
				dfs(stores.get(i));
			}
		}
	}

	// a와 b 위치 사이를 이동할 수 있는지 여부를 반환
	private static boolean isPossible(Position a, Position b) {
		int dist = Math.abs(a.x-b.x) + Math.abs(a.y-b.y); // 두 위치의 거리
		
		return ((1.0 * dist / 50) <= 20);
	}
	
}
