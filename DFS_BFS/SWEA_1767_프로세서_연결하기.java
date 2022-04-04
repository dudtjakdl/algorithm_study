package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
 * 걸린 시간 : 90분
 */
public class SWEA_1767_프로세서_연결하기 {
	public static class Core { // 코어의 위치 정보 객체
		int x, y; // 코어의 x, y 좌표

		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static List<Core> cores;
	static int ans, coreNum, maxCore;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		// 테스트케이스만큼 실행
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 멕시노스의 가로,세로 길이
			map = new int[N][N]; // 멕시노스의 초기상태 배열
			cores = new ArrayList<>(); // 연결해야할 코어의 객체를 담을 배열
			
			// 멕시노스 초기상태 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 입력칸이 코어이고, 가장자리에 해당하지 않으면 리스트에 담기
					if(i != 0 && i != N-1 && j != 0 && j != N-1 && map[i][j] == 1) {
						cores.add(new Core(i, j));
					}
				}
			}
			
			ans = 0; // 문제의 답 (최대한 코어를 많이 연결했을때 전선의 길이)
			maxCore = 0; // 최대한 많이 연결한 코어의 개수
			coreNum = cores.size(); // 연결해야할 코어의 개수
			dfs(0, 0, 0, copyMap(map));
			
			// 결과 출력
			System.out.println("#"+test_case+" "+ans);
		}

	}

	/**
	 * @param idx : 현재 고려하고 있는 코어 리스트의 인덱스
	 * @param length : 직전까지 연결한 전선의 길이
	 * @param cnt : 직전까지 연결한 코어의 개수
	 * @param copy : 직전의 맵 상태를 복사한 배열
	 */
	private static void dfs(int idx, int length, int cnt, int[][] copy) {
		if (idx == coreNum) { // 기저 조건: 모든 코어를 전부 고려했을 경우
			if (cnt > maxCore) {
				maxCore = cnt;
				ans = length;
			} 
			else if (cnt == maxCore) {
				ans = Math.min(ans, length);
			}
			
			return;
		}
		
		// 상하좌우로 전선 연결하기
		for (int i = 0; i < 4; i++) {
			int nr = cores.get(idx).x + deltas[i][0];
			int nc = cores.get(idx).y + deltas[i][1];
			
			// 경계를 넘지 않고 비어있는 칸이면 탐색방향 1칸씩 이동
			while (nr >= 0 && nr < N && nc >= 0 && nc < N && copy[nr][nc] == 0) {
				// 탐색방향이 가장자리에 해당하면 전선을 연결할 수 있다는 뜻
				if(nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
					nr = cores.get(idx).x + deltas[i][0];
					nc = cores.get(idx).y + deltas[i][1];
					int wireLen = 0; // 연결한 전선의 길이
					int[][] temp = copyMap(copy); // 멕시노스 상태배열 복사
					
					// 코어위치부터 가장자리까지 전선 연결하기
					while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						temp[nr][nc] = 2; // 배열값 2로 할당 (전선이라는 뜻)
						wireLen++; // 전선 길이 1증가
						
						nr += deltas[i][0];
						nc += deltas[i][1];
					}
					
					// 전선 연결 끝났으면 다음코어 고려하러 가기
					dfs(idx+1, length+wireLen, cnt+1, temp);
					break;
				}
				
				// 탐색방향 1칸씩 이동
				nr += deltas[i][0];
				nc += deltas[i][1];
			}
		}
		
		// 현재 코어는 연결하지 않고 넘어가기
		dfs(idx+1, length, cnt, copy);
		
	}

	// 매개변수로 받은 배열을 복사해서 반환하는 함수
	private static int[][] copyMap(int[][] origin) {
		int[][] copy = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}

}
