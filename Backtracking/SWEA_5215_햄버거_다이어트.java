package Backtracking;

import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT#none
 * 걸린 시간 : 30분
*/
public class SWEA_5215_햄버거_다이어트 {
	
	static int[] scores;
	static int[] cals;
	static int result, N, L;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			L = sc.nextInt();
			scores = new int[N];
			cals = new int[N];
			
			for(int i=0; i<N; i++) {
				scores[i] = sc.nextInt();
				cals[i] = sc.nextInt();
			}
			
			result = 0;
			subset(0, 0, 0);
			
			System.out.printf("#%d %d\n",test_case, result);
		}
		
	}
	
	public static void subset(int idx, int sumScore, int sumCal) {
		if (sumCal > L) {
			return;
		}
		else if (idx == N) {
			if(result < sumScore) result = sumScore;
			return;
		}
		
		subset(idx+1, sumScore+scores[idx], sumCal + cals[idx]);
		subset(idx+1, sumScore, sumCal);
	}

}
