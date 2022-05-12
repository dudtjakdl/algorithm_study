package Backtracking;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/15657
 * 걸린 시간 : 10분
 */
public class BOJ_15657_N과_M_8 {
	static int N, M;
	static int[] input, numbers; // input : 입력수 배열, numbers : 선택수 배열
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		input = new int[N];
		numbers = new int[M];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();;
		}
		
		Arrays.sort(input); // 사전순으로 출력하기 위해 오름차순 정렬
		combination(0, 0);
		System.out.println(sb);
	}
	
	public static void combination(int cnt, int start) { // cnt : 직전까지 뽑은 수 개수, start : 중복 방지를 위한 인덱스 시작 위치
		if(cnt == M) {
			for(int n: numbers) {
				sb.append(n + " ");
			}
			sb.append('\n');
			return;
		}
		
		// 입력받은 모든 수를 현재 자리에 넣어보기
		for(int i=start; i<N; i++) {
			numbers[cnt] = input[i];
			// 다음 수 뽑기
			combination(cnt+1, i);
		}
	}

}
