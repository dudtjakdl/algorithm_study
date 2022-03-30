package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

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
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				Position temp = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				stores.add(temp);
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			// 페스티벌 위치 (도착)
			end = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
//			cnt = 20; // 맥주의 개수 초기화
			ans = "sad";
			
			dfs(start, 20);
			
			System.out.println(ans);
		}
	
	}

	private static void dfs(Position current, int cnt) {
		if(isPossible(current, end, cnt)) {
			ans = "happy";
			return;
		}
		
		for (Position pos : stores) {
			if(isPossible(current, pos, cnt)) {
				dfs(pos, 20);
			}
		}
	}

	// a와 b 위치 사이를 이동할 수 있는지 여부를 반환
	private static boolean isPossible(Position a, Position b, int cnt) {
		int dist = Math.abs(a.x-b.x) + Math.abs(a.y-b.y); // 두 위치의 거리
		
		if (dist == 0) return false;
		
		return ((1.0 * dist / 50) > cnt);
	}
	
}
