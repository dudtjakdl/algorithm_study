package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
 * 걸린 시간 : 20분
 */
public class SWEA_9229_한빈이와_Spot_Mart {
	
	static int N, M;
	static int[] snack;
	static StringTokenizer st;
	static int max; // 과자봉지 무게 합 최대 (결과값)
	static int[] pick = new int[2]; // 구입한 과자 2개의 무게를 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=TC; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 과자의 개수
			M = Integer.parseInt(st.nextToken()); // 과자 무게 합 제한
			snack = new int[N]; // 과자 무게 저장하는 배열
			st = new StringTokenizer(br.readLine(), " ");
			max = -1; // 결과값 -1로 초기화
			
			for(int i=0; i<N; i++) { // 과자 무게 입력
				snack[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0);
			
			System.out.printf("#%d %d\n", test_case, max);
		}
	}
	
	// 과자 2개를 고르는 조합을 정하는 함수
	static void combination(int cnt, int start) {
		if(cnt == 2) { // 과자를 2개 골랐으면 무게 제한을 넘는지 검사
			if(pick[0] + pick[1] <= M) {
				max = Math.max(max, pick[0] + pick[1]);
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			pick[cnt] = snack[i];
			combination(cnt+1, i+1);
		}
	}
}
