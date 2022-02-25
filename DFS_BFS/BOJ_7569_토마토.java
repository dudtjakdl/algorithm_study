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
 * 문제 링크 : https://www.acmicpc.net/problem/2606
 * 걸린 시간 : 30분
 * 리뷰 : 백준 7576번 문제에서 토마토상자가 3차원으로만 바뀐 문제이다.
 * 3차원 처리를 해주기 위해서 상자배열을 3차원 배열로 바꿔주었고, 높이 개념인 H를 추가해주었다.
 * 그리고 추가된 2방향의 탐색을 해주기 위해 6방탐색을 위한 deltas도 만들어주었다.
 * 7576 문제는 deltas를 만들어주지 않고 모든 방향을 다 if문으로 처리해주었는데 deltas를 만들어주니 더 깔끔한 코드가 된 것 같다.
*/
public class BOJ_7569_토마토 {
	static int N, M, H, K;
	static int[][][] map;
	static List<int[]> start;
	static Queue<int []> queue;
	static int cnt = 0; // 익은 토마토 개수
	static int day = 0; // 토마토가 모두 익을 때까지 걸리는 최소 날짜 (결과값)
	static int[][] deltas = {{0,-1,0},{0,1,0},{0,0,-1},{0,0,1},{-1,0,0},{1,0,0}}; // 상하좌우앞뒤
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸의 수
		N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸의 수
		H = Integer.parseInt(st.nextToken()); // 쌓아올려지는 상자의 수
		map = new int[H][N][M]; // 토마토가 들어있는 상자
		start = new ArrayList<>(); // 맨 처음 시점의 익은 토마토 위치
		queue = new LinkedList<>(); // 탐색 순서를 저장할 Queue
		K = M*N*H; // 상자에 들어있는 토마토의 개수 (처음은 최대 개수로 초기화)
		
		// 상자 칸에 토마토 상태 입력 (1: 익은 토마토, 0: 익지 않은 토마토, -1: 빈 칸)
		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[h][i][j] = temp;
					
					// -1이면(토마토가 들어있지 않은 칸이면) 토마토 개수 1개 빼기
					if(temp == -1) K--;
					// 1이면(익은 토마토이면) 탐색을 시작할 시작위치(h,i,j) 저장
					else if(temp == 1) {
						start.add(new int[] {h, i, j});
						cnt++; // 익은 토마토 개수 1 증가
					}
				}
			}
		}

		// 너비 우선 탐색 시작
		bfs();
		
		// 익은 토마토 개수가 전체 토마토 개수보다 적으면 토마토가 모두 익지 못했으므로 -1 저장
		if(cnt < K) day = -1;
		
		System.out.println(day); // 결과 출력
	}
	
	// 너비 우선 탐색 함수
	static void bfs() {
		
		// 맨 처음 시점의 익은 토마토 위치 queue에 넣기
		for(int i=0; i<start.size(); i++) {
			queue.add(start.get(i));
		}
		
		while(!queue.isEmpty()) {
			
			// 전체 토마토 개수와 익은 토마토 개수가 같으면 return
			if(cnt == K) return;
			
			// 하루 증가
			day++;
			
			// 같은 너비의 토마토들 모두 탐색
			int size = queue.size(); // 하루(같은 너비)동안 탐색해야할 토마토 개수
			while(--size >= 0) {
				int[] temp =  queue.poll();
				int curH = temp[0]; // 현재 주목하고 있는 토마토의 높이 위치
				int curX = temp[1]; // 현재 주목하고 있는 토마토의 행 위치
				int curY = temp[2]; // 현재 주목하고 있는 토마토의 열 위치
				
				// 6방 탐색
				for(int i=0; i<6; i++) {
					int nh = curH+deltas[i][0]; // 탐색 방향의 높이
					int nr = curX+deltas[i][1]; // 탐색 방향의 행
					int nc = curY+deltas[i][2]; // 탐색 방향의 열
					
					if(nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M && map[nh][nr][nc] == 0) {
						queue.offer(new int[]{nh, nr, nc});
						map[nh][nr][nc] = 1; // 익은 토마토로 바꾸기
						cnt++; // 익은 토마토 개수 1증가
					}
				}
			}
			
		}
	}
	
	

}
