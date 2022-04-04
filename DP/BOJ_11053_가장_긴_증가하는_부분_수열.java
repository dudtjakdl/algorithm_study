package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/11053
 * 걸린 시간 : 20분
 */
public class BOJ_11053_가장_긴_증가하는_부분_수열 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 수열의 길이
		int[] arr = new int[N+1]; // 수열의 원소를 저장할 배열
		
		// 수열의 원소 입력
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
		int[] LIS = new int[N+1]; // i 원소를 끝으로 하는 최장증가 수열 길이
			
		for (int i = 1; i <= N; i++) {
			LIS[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j] < arr[i] && LIS[i] < LIS[j]+1) {
					LIS[i] = LIS[j] + 1;
				}
			}
		}
		
		// 가장 큰 값을 맨 뒤로 보내기 위해 오름차순 정렬
		Arrays.sort(LIS);
			
		// 결과 출력
		System.out.println(LIS[N]);
	}

}
