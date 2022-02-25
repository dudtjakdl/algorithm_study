package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 정올
 * 문제 링크 : http://jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=3030
 * 걸린 시간 : 40분
 * 리뷰 : 최소 비용을 구하는 문제라 다익스트라로 풀어야 하는 생각이 들었지만,
 * 다익스트라는 정점을 한번 방문함을 보장하지 않고 시작위치로 돌아오게 할 수 있는 것도 아니므로 적용할 수 없다.
 * 즉, dfs와 간단한 백트래킹 조건을 이용하여 풀 수 있는 문제였다.
 * 처음에는 시간초과가 나서 가지치기 조건을 추가해주었지만 그래도 틀린 결과로 나와서 당황했다.
 * 하지만 문제 조건을 보니 이동할 수 없는 경우엔 비용이 0이라는 조건이 있어 0일 경우에는
 * 이동할 수 없도록 조건을 추가해주었더니 해결할 수 있었다.
 * 해밀턴 경로 : 모든 정점 혹은 꼭지점을 한 번씩만 지나는 경로
 * 해밀턴 순환 : 시작점과 끝점이 같은 해밀턴 경로
 */
public class JUNGOL_1681_해밀턴_순환회로 {
	static int N;
	static int[][] map;
	static int res;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 배달해야 하는 장소의 수
		map = new int[N+1][N+1]; // 장소와 장소를 이동하는 비용 저장
		StringTokenizer st;
		
		// 이동 비용 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		res = Integer.MAX_VALUE; // 최소 비용 (결과값)
		visited = new boolean[N+1]; // 방문 체크 배열
		
		dfs(1, 0, 0); // 회사를 출발위치로 하여 깊이 우선 탐색 시작
		
		System.out.println(res); // 결과 출력
	}

	/**
	 * @param from : 출발 장소 (map의 행 인덱스)
	 * @param cnt : 현재까지 총 이동 횟수
	 * @param sum : 현재까지 총 이동 비용
	 */
	private static void dfs(int from, int cnt, int sum) { // 깊이 우선 탐색
		if(cnt == N-1) { // 배달할 장소를 모두 들렀을 경우
			// 회사로 돌아가는 비용이 0이면 return
			if(map[from][1] == 0) return;
			
			sum += map[from][1]; // 총 비용에 회사로 돌아가는 비용 더해주기
			res = Math.min(res, sum); // 총 비용이 최소값인지 체크
			return;
		}
		
		// 가지치기 -> 이동 비용이 이미 최소비용보다 같거나 크면 더 이상의 이동은 유망하지 않음
		if(sum >= res) return;
		
		for (int i = 2; i <= N; i++) {
			// 이미 방문했거나 이동 비용이 0이면 pass
			if(visited[i] || map[from][i] == 0) continue;
			
			visited[i] = true; // 방문 체크
			dfs(i ,cnt+1, sum+map[from][i]); // i번 장소로 이동
			visited[i] = false; // 방문 체크 해제
		}
		
	}

}
