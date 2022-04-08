package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/17135
 * 걸린 시간 : 150분
 * 리뷰 : 궁수들이 한턴에서 서로 같은 적을 죽일때 중복을 처리해주는 것이 어려웠다.
 * 이미 죽은 적의 값을 임시로 2로 바꾸고, 각 턴이 끝날 때마다 2를 빈 칸인 0으로 바꿔주었다.
 * 그리고 bfs 탐색을 진행하면서 한 궁수가 같은 distance에 있는 적을 동시에 죽이는 경우도 발생해서,
 * 이를 방지하기 위해 kill이라는 boolean 배열을 통해 각 궁수가 이미 적을 죽인 경우는 true로 바꿔주어
 * 중복으로 적을 죽이는 경우를 방지해주었다.
 */
public class BOJ_17135_캐슬_디펜스 {
	static int N, M, D, cnt, ans;
	static int[][] map, copy;
	static int[][] deltas = {{0,-1},{-1,0},{0,1}}; // 좌상우
	static int[] archer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 격자판 행의 수
		M = Integer.parseInt(st.nextToken()); // 격자판 열의 수
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한
		map = new int[N][M]; // 격자판 배열
		
		// 격자판의 상태 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0; // 제거할 수 있는 적의 최대 수
		archer = new int[3]; // 궁수 3명이 위치할 열 인덱스
		combination(0, 0); // 궁수 3명을 배치할 조합 구하기
		
		// 결과 출력
		System.out.println(ans);
	}

	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			// 궁수 3명을 배치할 조합을 구했으면 게임 시작하기
			copy = copyMap(); // 맵 복사
			game(); // 게임 시작
			return;
		}
		
		// 궁수가 위치할 열 인덱스 뽑기
		for (int i = start; i < M; i++) {
			archer[cnt] = i;
			combination(cnt+1, i+1);
		}
		
	}

	private static void game() {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[] kill; // 각 궁수가 적을 죽였는지 체크하는 배열
		int cnt = 0; // 제거한 적의 수
		int dis = 0; // 현재 궁수의 공격 범위
		
		// 각 턴 진행
		while (true) {
			dis = 0;
			kill = new boolean[3];
			
			// dis가 1인 경우 -> 각 궁수 N-1행에 있는 적 죽이기
			for (int i = 0; i < 3; i++) {
				int x = N-1;
				int y = archer[i];
				
				// 적일 경우
				if(copy[x][y] == 1) {
					cnt++;
					copy[x][y] = 2;
					kill[i] = true;
				}
				// 빈 칸일 경우
				else if(copy[x][y] == 0) {
					queue.offer(new int[] {x, y, i});
				}
			}
			
			while(++dis != D) {
				int size = queue.size();

				while (--size >= 0) {
					int[] current = queue.poll();
					int curX = current[0];
					int curY = current[1];
					int archerNum = current[2];
					
					// 이미 적을 죽인 궁수이면 pass
					if(kill[archerNum]) continue;
					
					for (int i = 0; i < 3; i++) {
						int nr = curX + deltas[i][0];
						int nc = curY + deltas[i][1];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
						
						// 적일 경우
						if (copy[nr][nc] == 1) {
							cnt++;
							copy[nr][nc] = 2;
							kill[archerNum] = true;
							break;
						}
						// 이미 죽은 적일 경우
						else if (copy[nr][nc] == 2) {
							kill[archerNum] = true;
							break;
						}
						// 빈 칸일 경우
						else if (copy[nr][nc] == 0) {
							queue.offer(new int[] {nr, nc, archerNum});
						}
					}
				}
				
			}
			
			// 죽은 적 빈 칸으로 바꾸기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(copy[i][j] == 2) {
						copy[i][j] = 0;
					}
				}
			}
			
			queue.clear(); // queue 비우기
			move(); // 적들 아래로 한칸 이동
			
			// 적이 더 이상 존재하지 않을 경우 게임 종료
			if(end()) break;
		}

		// 최대값 갱신
		ans = Math.max(ans, cnt);
	}

	// 게임을 끝낼지 여부를 반환하는 함수
	private static boolean end() {
		// 적이 한명이라도 존재하면 false 반환
		for (int i = N-1; i >= 0; i--) {
			for (int j = M-1; j >= 0; j--) {
				if (copy[i][j] == 1) return false;
			}
		}
		
		return true;
	}

	private static void move() {
		// i-1행 값을 i행에 복사
		for (int i = N-1; i >= 1; i--) {
			int[] temp = copy[i-1].clone();
			copy[i] = temp;
		}
		
		Arrays.fill(copy[0], 0); // 첫번째 행 0으로 채우기
	}

	private static int[][] copyMap() {
		int[][] copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		return copy;
	}

}
