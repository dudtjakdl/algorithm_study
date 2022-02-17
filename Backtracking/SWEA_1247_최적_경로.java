package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
 * 걸린 시간 : 50분
*/
public class SWEA_1247_최적_경로 {
	static int N;
	static int[] company, home;
	static int[][] client;
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 고객의 수
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			company = new int[2]; // 회사의 위치 정보를 입력할 배열
			home = new int[2]; // 집의 위치 정보를 입력할 배열
			client = new int[N][2]; // 고객의 위치 정보를 입력할 배열
			
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				client[i][0] = Integer.parseInt(st.nextToken());
				client[i][1] = Integer.parseInt(st.nextToken());
			}
			
			res = Integer.MAX_VALUE; // 결과값 (최단거리)
			
			permutation(0, 0, 0, company[0], company[1]);
			
			System.out.println("#"+test_case+" "+res);
		}
	}

	/**
	 * @param cnt : 고객 방문한 횟수
	 * @param sum : 지금까지 이동한 총 거리
	 * @param flag : 고객 방문 체크를 위한 flag
	 * @param preRow : 이전 방문했던 곳의 행 위치
	 * @param preCol : 이전 방문했던 곳의 열 위치
	 */
	// 고객 방문 순서를 구해주는 순열
	private static void permutation(int cnt, int sum, int flag, int preRow, int preCol) {
		if(cnt == N) { // 고객을 전부 방문했으면
			// 지금까지 이동한 거리+마지막 고객위치에서 집까지의 거리
			sum += Math.abs(preRow-home[0]) + Math.abs(preCol-home[1]);
			res = Math.min(res, sum);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1 << i) != 0) continue; // 이미 방문한 고객이면 pass
			
			// 이전에 방문했던 위치와 현재 방문할 고객까지의 거리 계산
			int distance = Math.abs(preRow-client[i][0]) + Math.abs(preCol-client[i][1]);
			
			// 다음 위치 방문하러 이동
			permutation(cnt+1, sum+distance, flag | 1 << i, client[i][0], client[i][1]);
		}
		
	}
	

}
