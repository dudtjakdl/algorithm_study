package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/15683
 * 걸린 시간 : 100분
 * 리뷰 : 각각의 cctv 방향을 표현하는 방법으로 모든 CCTV의 deltas를 하나하나 선언해주었다.
 * 하지만 이 방법보다는 밑의 주석처리한 방법처럼 좌우상하의 dletas를 미리 만들어놓고,
 * 각 CCTV마다 가능한 방향의 deltas index를 저장하는 방법으로 하는것도 좋았을 것 같다.
 * 그리고 설치된 cctv의 정보를 관리하기 위해 cctv 클래스를 만들어서 관리해주었다면 더 깔끔했을 것 같다. 
*/
//static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }; // 우좌상하
//static int[][][] cctvMode = {
//        { { 0 } },
//        { { 1 }, { 2 }, { 3 }, { 0 } }, // 1번 CCTV 우, 좌, 상, 하
//        { { 0, 1 }, { 2, 3 } }, // 2번 CCTV 우좌, 상하
//        { { 0, 2 }, { 0, 3 }, { 1, 3 }, { 1, 2 } }, // 3번 CCTV 우상, 우하, 좌하, 좌상
//        { { 0, 1, 2 }, { 0, 2, 3 }, { 0, 1, 3 }, { 1, 2, 3 } }, // 4번 CCTV 우좌상, 우상하, 우좌하, 좌상하
//        { { 0, 1, 2, 3 } } // 5번 CCTV 모든방향
//		};
public class BOJ_15683_감시 {
	
	static int N, M, min;
	static int[][] map, copy;
	static int[][] CCTV1 = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static int[][][] CCTV2 = {{{0,-1},{0,1}}, {{-1,0},{1,0}}};
	static int[][][] CCTV3 = {{{-1,0},{0,1}}, {{0,1},{1,0}}, {{0,-1},{1,0}}, {{0,-1},{-1,0}}};
	static int[][][] CCTV4 = {{{0,-1},{-1,0},{0,1}}, {{-1,0},{0,1},{1,0}}, {{0,-1},{1,0},{0,1}}, {{0,-1},{-1,0},{1,0}}};
	static int[][][] CCTV5 = {{{0,1}, {1,0}, {0,-1}, {-1,0}}};
	static int[] direction;
	static int cctvSize;
	static List<Integer> cctv;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 사무실의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 사무실의 가로 크기
		map = new int[N][M]; // 사무실 각 칸을 저장하는 배열
		cctv = new ArrayList<Integer>(); // 설치된 cctv의 번호를 차례대로 저장하는 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
			
				if(temp != 0 && temp != 6) cctv.add(temp);
				map[i][j] = temp;
			}
		}

		cctvSize = cctv.size(); // CCTV 개수
		direction = new int[cctvSize]; // 설치된 cctv의 방향을 순서대로 저장하는 배열
		copy = new int[N][M]; // 원본 배열인 map을 복사할 배열
		min = Integer.MAX_VALUE; // 사각 지대의 최소 크기 -> Integer 가장 큰 값으로 초기화
		selectCCTVDir(0);
		
		System.out.println(min); // 결과 출력

	}
	
	// 설치된 cctv들의 방향을 정하는 함수 -> 모든 경우의 수 다 해보기
	private static void selectCCTVDir(int idx) {
		if(idx == cctvSize) {
			// 모든 cctv의 방향이 정해졌으면 감시 위치 표시 시작
			operate();
			return;
		}
		
		int n = 0; // 각 cctv 종류에 따른 방향 전환 가능 횟수
		
		switch(cctv.get(idx)) {
		case 1:
			n = CCTV1.length;
			break;
		case 2:
			n = CCTV2.length;
			break;
		case 3:
			n = CCTV3.length;
			break;
		case 4:
			n = CCTV4.length;
			break;
		case 5:
			n = CCTV5.length;
			break;
		}
		
		// 방향 전환 가능 횟수만큼 idx번째 cctv의 방향 한번씩 다 놓아보기
		for(int i=0; i<n; i++) {
			direction[idx] = i;
			selectCCTVDir(idx+1);
		}
	}
	
	// cctv의 감시 위치를 체크하는 함수 (감시되는 부분 -1로 바꾸기)
	private static void operate() {
		mapCopy(); // 배열 복사
		
		int idx = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 0 || copy[i][j] == 6 || copy[i][j] == -1) continue;
				
				int row = i;
				int col = j;
				int dir = direction[idx];
				
				switch(cctv.get(idx)) {
				case 1:
						while(true) {
							int nr = row+CCTV1[dir][0];
							int nc = col+CCTV1[dir][1];
							if(nr >= 0 && nr < N && nc >= 0 && nc < M && copy[nr][nc] != 6 ) {
								if(copy[nr][nc] == 0) copy[nr][nc] = -1;
								row = nr;
								col = nc;
							}
							else {
								break;
							}
						}
					break;
				case 2:
						for(int l=0; l<2; l++) {
							row = i;
							col = j;
							while(true) {
								int nr = row+CCTV2[dir][l][0];
								int nc = col+CCTV2[dir][l][1];
								if(nr >= 0 && nr < N && nc >= 0 && nc < M && copy[nr][nc] != 6 ) {
									if(copy[nr][nc] == 0) copy[nr][nc] = -1;
									row = nr;
									col = nc;
								}
								else {
									break;
								}
							}
						}
					break;
				case 3:
						for(int l=0; l<2; l++) {
							row = i;
							col = j;
							while(true) {
								int nr = row+CCTV3[dir][l][0];
								int nc = col+CCTV3[dir][l][1];
								if(nr >= 0 && nr < N && nc >= 0 && nc < M && copy[nr][nc] != 6 ) {
									if(copy[nr][nc] == 0) copy[nr][nc] = -1;
									row = nr;
									col = nc;
								}
								else {
									break;
								}
							}
						}
					break;
				case 4:
						for(int l=0; l<3; l++) {
							row = i;
							col = j;
							while(true) {
								int nr = row+CCTV4[dir][l][0];
								int nc = col+CCTV4[dir][l][1];
								if(nr >= 0 && nr < N && nc >= 0 && nc < M && copy[nr][nc] != 6 ) {
									if(copy[nr][nc] == 0) copy[nr][nc] = -1;
									row = nr;
									col = nc;
								}
								else {
									break;
								}
							}
						}
					break;
				case 5:
						for(int l=0; l<4; l++) {
							row = i;
							col = j;
							while(true) {
								int nr = row+CCTV5[dir][l][0];
								int nc = col+CCTV5[dir][l][1];
								if(nr >= 0 && nr < N && nc >= 0 && nc < M && copy[nr][nc] != 6 ) {
									if(copy[nr][nc] == 0) copy[nr][nc] = -1;
									row = nr;
									col = nc;
								}
								else {
									break;
								}
							}
						}
					break;
				}
				
				idx++;
			}
		}
		
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 0) cnt++;
			}
		}
		
		min = Math.min(min, cnt);
		
	}

	// 원본 배열인 map을 복사하는 함수
	private static void mapCopy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

}
