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
 * 문제 링크 : https://www.acmicpc.net/problem/15686
 * 걸린 시간 : 60분
*/
public class BOJ_15686_치킨_배달 {
	static int N, M;
	static int[][] map;
	static List<Location> storeList, houseList;
	static Location[] pick;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	static int sum;
	static int res;
	
	static class Location { // 위치 정보 클래스
		int x, y; // x: 행 위치 y: 열 위치

		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 도시의 크기
		M = Integer.parseInt(st.nextToken()); // 폐업시키지 않을 치킨집 수
		map = new int[N][N]; // N×N인 도시 정보 배열
		storeList = new ArrayList<Location>(); // 치킨집 위치 정보를 저장하는 리스트
		houseList = new ArrayList<Location>(); // 집 위치 정보를 저장하는 리스트
		
		// 도시 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) { // 치킨집 칸이면 치킨집 리스트에 저장
					storeList.add(new Location(i, j));
					map[i][j] = 0; // 빈 칸으로 바꾸기
				}
				else if(map[i][j] == 1) { // 집 칸이면 집 리스트에 저장
					houseList.add(new Location(i, j));
				}
			}
		}
		
		pick = new Location[M]; // 폐업시지지 않을 치킨집 M개를 저장할 배열
		res = Integer.MAX_VALUE;
				
		combination(0, 0); // 치킨집 M개 조합 뽑기

		System.out.println(res); // 결과 출력
	}

	private static void combination(int cnt, int start) {
		if(cnt == M) { // 치킨집 M개를 다 뽑았을 경우
			bfs(); // 너비 우선 탐색으로 치킨 거리 구하기
			res = Math.min(res, sum); // 치킨집 거리 최소값으로 갱신
			return;
		}
		
		for(int i = start; i < storeList.size(); i++) {
			pick[cnt] = storeList.get(i);
			combination(cnt+1, i+1);
		}
	}

	private static void bfs() {
		Queue<Location> queue = new LinkedList<>(); // 방문 순서를 관리할 queue
		boolean[][] visited; // 방문 체크 배열
		
		// 뽑힌 치킨집 M개 지도에 표시하기
		for(int i = 0; i < M; i++) {
			Location temp = pick[i];
			map[temp.x][temp.y] = 2;
		}
		
		sum = 0; // 치킨 거리의 합
		
		// 집의 개수만큼 각자 치킨 거리 구하기
		for(int i = 0; i < houseList.size(); i++) {
			Location start = houseList.get(i);
			
			queue.offer(start); // 탐색을 시작할 집 위치 정보 queue에 넣기
			visited = new boolean[N][N]; // 방문 체크 배열 초기화
			visited[start.x][start.y] = true; // 시작 위치 방문 체크

			while(!queue.isEmpty()) {
				Location temp = queue.poll();
				
				// 상하좌우로 이동
				for(int j = 0; j < 4; j++) {
					int nr = temp.x + deltas[j][0];
					int nc = temp.y + deltas[j][1];
					
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
						if(map[nr][nc] == 2) { // 이동 방향이 치킨집이면
							sum += Math.abs(start.x-nr)+Math.abs(start.y-nc); // 치킨 거리 구해서 sum에 더하기
							queue.clear(); // 더 이상의 탐색은 불필요하니 queue 비우기
							break;
						}
						else { // 치킨집이 아닐 경우 이동
							queue.offer(new Location(nr, nc));
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		
		// 뽑힌 치킨집 M개 지도에 다시 0으로 돌려놓기
		for(int i = 0; i < M; i++) {
			Location temp = pick[i];
			map[temp.x][temp.y] = 0;
		}
	}

}
