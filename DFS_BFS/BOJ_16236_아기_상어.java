package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2667
 * 걸린 시간 : 150분
 * 리뷰 : 물고기를 먹을 수 있는 경우가 여러개일때, 가장 위에 있거나 왼쪽에 있는 물고기를 먼저 먹어야 한다는 조건을
 * 만족하기가 너무 어려웠다. bfs의 deltas 탐색 순서로는 절대 이 조건을 구현해내지 못한다는걸 간과했다.
 * 해결책으로 우선순위큐에 물고기를 먹을 수 있는 경우를 전부 집어넣고, 행(행이 같으면 열) 오름차순으로 가장 먼저 pull 되는 물고기를
 * 먹도록 구현하여 우선순위 조건을 구현할 수 있었다.
*/
public class BOJ_16236_아기_상어 {
	static int N;
	static int[][] map;
	static int res; // 이동한 총 시간 (결과값)
	static int size; // 상어의 현재 크기
	static int cnt; // 상어가 물고기를 먹은 횟수
	static int[][] deltas = {{-1,0},{0,-1},{0,1},{1,0}}; // 상좌우하
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 공간의 크기
		map = new int[N][N]; // NxN 크기로 공간 초기화
		StringTokenizer st;
		int r = 0; // 상어 초기 위치 (행)
		int c = 0; // 상어 초기 위치 (열)
		
		// 공간의 상태 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) { // 상어 초기 위치값 저장
					r = i;
					c = j;
				}
			}
		}
		size = 2; // 상어 크기 2로 초기화
		
		bfs(r, c);
		
		System.out.println(res); // 결과 출력
	}

	/**
	 * @param startRow : 상어 초기 위치 (행)
	 * @param startCol : 상어 초기 위치 (열)
	 */
	private static void bfs(int startRow, int startCol) { // 너비 우선 탐색
		Queue<int[]> queue = new LinkedList<int[]>(); // 방문 순서를 관리할 queue
		boolean[][] visited = new boolean[N][N]; // 방문 체크를 위한 배열
		
		map[startRow][startCol] = 0; // 초기 상어 위치 비우기
		queue.offer(new int[] {startRow, startCol, 0}); // 초기 상어 위치값 queue에 넣기 {행, 열, 이동시간(depth)}
		visited[startRow][startCol] = true; // 초기 상어 위치 방문 체크
				
		// 먹을 수 있는 물고기의 위치값을 저장하는 우선순위큐 -> 상, 좌의 우선순위로 물고기 위치 정렬
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) { // 행 오름차순, 행 값이 같으면 열 오름차순
				return o1[0]==o2[0] ? o1[1]-o2[1] : o1[0]-o2[0];
			}
		});
		
		int time = 1; // 이동 시간

		while(!queue.isEmpty()) {
			int qSize = queue.size(); // 현재 depth(너비)만큼만 탐색하기 위해 queue 크기 저장
			
			while(--qSize >= 0) {
				int[] temp = queue.poll();
				int curRow = temp[0]; // 현재 상어의 행 위치
				int curCol = temp[1]; // 현재 상어의 열 위치
				int curTime = temp[2]; // 현재 위치로 이동하기까지 걸린 시간
				
				if(isAvailable(curRow, curCol)) { // 현재 위치에서 물고기를 먹을 수 있으면
					pQueue.offer(new int[] {curRow, curCol, curTime}); // 우선순위 큐에 물고기 위치 저장
					continue; // 더 이상의 이동은 필요없으니 밑에는 pass
				}

				visited[curRow][curCol] = true; // 현재 상어 위치 방문 체크
				
				// 상어 이동
				for (int i = 0; i < 4; i++) {
					int nr = curRow+deltas[i][0];
					int nc = curCol+deltas[i][1];
					
					// 이동 위치의 경계값 체크 && 이미 방문했는지 체크 && 상어의 사이즈보다 같거나 작은지 체크
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] <= size) {
						queue.offer(new int[] {nr, nc, time}); // 이동 위치 queue에 넣기
						visited[nr][nc] = true; // 이동 위치 방문 체크
					}
				}
			}
			
			if(pQueue.size() > 0) { // 먹을 수 있는 물고기를 찾았는지 체크
				int[] temp = pQueue.poll(); // 가장 우선순위가 높은 물고기 정보만 반환
				int curRow = temp[0];
				int curCol = temp[1];
				int curTime = temp[2];
				
				map[curRow][curCol] = 0; // 현재 위치 빈 칸으로 만들기
				cnt++; // 물고기 먹은 횟수 1 증가
				res += curTime; // 현재 위치로 이동하기까지 걸린 시간 결과값에 더하기
				
				if(cnt == size) { // 물고기 먹은 횟수가 상어 크기와 같으면
					size++; // 상어 크기 1 증가
					cnt = 0; // 물고기 먹은 횟수 0으로 다시 초기화
				}
				// 이동을 다시 처음부터 시작하니 이동 시간 0으로 초기화
				time = 0;
				// 물고기 먹었으니 다른 경우의 탐색은 더이상 불필요 -> queue 비우기
				queue.clear();
				// 현재 위치부터 다시 탐색하기 위에 현재 위치 queue에 넣기
				queue.offer(new int[] {curRow, curCol, time});
				// 우선순위 큐 비우기
				pQueue.clear();
				// 방문 탐색을 다시 처음부터 해야하니 방문 체크 배열 초기화
				visited = new boolean[N][N];
			}

			time++; // 이동 시간 1 증가
		}
		
	} 
	
	/**
	 * @param i : 상어의 현재 위치 (행)
	 * @param j : 상어의 현재 위치 (열)
	 * @return : 물고기를 먹을 수 있으면 true, 먹을 수 없으면 false
	 */
	private static boolean isAvailable(int i, int j) { // 현재 상어 위치에서 물고기를 먹을 수 있는지 여부를 판단
		if(map[i][j] == 9 || map[i][j] == 0) return false; // 현재 위치가 비어있으면 무조건 false 반환	
		if(map[i][j] == size) return false; // 물고기의 크기가 상어 크기와 같으면 못먹으므로 false 반환
		// 여기까지 왔다는건 물고기를 먹을 수 있다는 의미
		return true; 
	}

}
