package Implementation;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2564
 * 걸린 시간 : 120분
*/
public class BOJ_2564_경비원 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); // 블록의 가로 길이
		int N = sc.nextInt(); // 블록의 세로 길이
		int K = sc.nextInt(); // 상점의 개수
		int[][] map = new int[N][M];
		
		// 배열에 상점 위치 표시 -> 1: 북쪽 2: 남쪽 3: 서쪽 4: 동쪽
		for(int i=0; i<K; i++) {
			int dir = sc.nextInt(); // 상점 방향
			int dis = sc.nextInt(); // 경계로부터의 거리
			switch(dir) {
			case 1:
				map[0][dis-1] = 1;
				break;
			case 2:
				map[N-1][dis] = 2;
				break;
			case 3:
				map[dis][0] = 3;
				break;
			case 4:
				map[dis-1][M-1] = 4;
				break;
			}
		}
		
		int myDir = sc.nextInt(); // 동근이 위치의 방향
		int myDis = sc.nextInt(); // 동근이 위치의 경계로부터의 거리
		int r = 0;
		int c = 0;
		int[][] deltas = new int[4][2]; // 북쪽, 동쪽, 남쪽, 서쪽
		
		// 배열에 동근이 위치 5로 표시
		switch(myDir) {
		case 1:
			map[0][myDis-1] = 5;
			r = 0;
			c = myDis-1;
			deltas = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
			break;
		case 2:
			map[N-1][myDis] = 5;
			r = N-1;
			c = myDis;
			deltas = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
			break;
		case 3:
			map[myDis][0] = 5;
			r = myDis;
			c = 0;
			deltas = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
			break;
		case 4:
			map[myDis-1][M-1] = 5;
			r = myDis-1;
			c = M-1;
			deltas = new int[][]{{1,0},{0,-1},{-1,0},{0,1}};
			break;
		}
		
		int max = (N*2)+(M*2); // 한바퀴 도는 거리
		int dir = 0; // 현재 방향 (deltas의 index)
		int res = 0; // 결과값 (최단거리의 총합)
		int cnt = 0; // 총 이동한 거리
		
		// 한바퀴 다 돌때까지 이동
		while(cnt<max) {
			if(r+deltas[dir][0] >= 0 && r+deltas[dir][0] < N && c+deltas[dir][1] >= 0 && c+deltas[dir][1] < M) {
				r += deltas[dir][0];
				c += deltas[dir][1];
				cnt += 1;
				if(map[r][c] != 0) { // 상점에 도착하면 최단거리 계산하기
					res += Math.min(cnt, max-cnt); // 최단거리 = (이동거리)와 (한바퀴-이동거리) 중 작은 수
				}
			}
			else { // 경계 초과
				// 방향 바꾸기
				if(dir == 3) dir = 0;
				else dir++; 
				
				cnt += 1; // 꼭짓점 코너 돌기 위해서 거리 1 더하기
				
				if(map[r][c] != 0) {
					res -= 1;
				}
			}
		}

		System.out.println(res);

	}

}
