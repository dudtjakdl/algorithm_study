package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/17471
 * 걸린 시간 : 150분
 * 리뷰 : 선거구를 나누는 경우의 수는 비트마스킹으로 쉽게 구현하였지만,
 * 같은 선거구끼리 이어져있는지 여부를 판단하는게 어려웠다.
 * 내가 생각했던 방법은 첫번째 선거구끼리 이어져있는 곳을 방문체크하고,
 * 두번째 선거구끼리 이어져있는 곳을 방문체크해서,
 * 한번도 방문체크 하지 않은 구역이 있는지 확인하는 방법으로 풀었다.
 */
public class BOJ_17471_게리맨더링 {
	static int N;
	static int[] arr;
	static int[][] adjMatrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 개수
		arr = new int[N+1]; // 각 구역의 인구수 배열 
		int max = (int) Math.pow(2, N); // 선거구를 나눌 수 있는 모든 경우의 수 (2의 N제곱)
		int total = 0; // 모든 구역의 총 인구수
		
		// 인구수 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		
		adjMatrix = new int[N+1][N+1]; // 인접 배열
		
		// 서로 인접한 구역 인접 배열값 1로 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[i][to] = 1;
			}
		}
		
		int ans = Integer.MAX_VALUE; // 문제의 답
		
		// 비트마스크 1부터 max-2 까지 선거구 나눠보기 
		// 0과 max-1는 하나의 선거구로밖에 나눠지지 않으므로 제외
		for (int flag = 1; flag < max-1; flag++) {
			// 해당 경우의수로 선거구를 나눌 수 있는 경우
			if(isValid(flag)) {
				int sum = 0; // 첫번째 선거구의 총 인구수
				
				// 비트마스크 1로만 체크된 지역의 인구수 합산
				for (int i = 0; i < N; i++) {
					if ((flag & 1 << i) != 0) {
						sum += arr[i+1];
					}
				}

				int diff = Math.abs((total-sum)-sum); // 두 선거구의 인구 차이
				ans = Math.min(ans, diff); // ans 작은값으로 갱신
			}
		}
		
		// 결과 출력
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}

	// 해당 경우의 수로 선거구를 나눌 수 있는지 여부를 반환하는 함수
	private static boolean isValid(int flag) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1]; // 방문체크 배열

		// 첫번째 선거구 지역 탐색 시작할 위치 찾기 (flag에서 1로 체크된 부분)
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) {
				visited[i+1] = true;
				queue.offer(i+1);
				break;
			}
		}
		
		// bfs 탐색하면서 첫번째 선거구지역으로 연결된 곳만 방문
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int i = 0; i < N; i++) {
				if (adjMatrix[current][i+1] == 1 && !visited[i+1] && (flag & 1 << i) != 0) {
					visited[i+1] = true;
					queue.offer(i+1);
				}
			}
		}
		
		// 두번째 선거구 지역 탐색 시작할 위치 찾기 (flag에서 0으로 체크된 부분)
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				visited[i+1] = true;
				queue.offer(i+1);
				break;
			}
		}
		
		// bfs 탐색하면서 두번째 선거구지역으로 연결된 곳만 방문
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int i = 0; i < N; i++) {
				if (adjMatrix[current][i+1] == 1 && !visited[i+1] && (flag & 1 << i) == 0) {
					visited[i+1] = true;
					queue.offer(i+1);
				}
			}
		}
		
		// 하나라도 방문체크 되지 않은 곳이 있으면 같은 선거구끼리 연결되지 않은 지역이 있다는 뜻
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) return false;
		}
		
		return true;
	}

}
