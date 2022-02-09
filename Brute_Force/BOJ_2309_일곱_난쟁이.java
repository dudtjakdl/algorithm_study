package Brute_Force;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2309
 * 걸린 시간 : 10분
 * 리뷰 : 배열 하나로 간단하게 풀 수 있는 문제였다.
 * 키를 더하지 않을 2명을 뽑고 그 2명을 뺀 나머지의 키를 모두 더해서 합이 100인지를 검사하였다.
 * 가능한 정답이 여러 가지인 경우에는 아무거나 출력하라고 했으니,
 * 모든 경우의 수를 탐색해보다가 키가 100이면 그 결과를 저장한 배열을 오름차순으로 정렬하고 출력한 뒤,
 * 바로 return하여 프로그램을 종료시켰다.
*/
public class BOJ_2309_일곱_난쟁이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] height = new int[9];
		int sum;
		int[] result;
		int cnt;
		
		for(int i=0; i<9; i++) {
			height[i] = sc.nextInt();
		}
		
		for(int i=0; i<9; i++) {
			for(int j=i+1; j<9; j++) {
				sum = 0;
				cnt = 0;
				result = new int[7];
				for(int k=0; k<9; k++) {
					if(k == i || k == j) {
						continue;
					}
					result[cnt++] = height[k];
					sum += height[k];
				}
				
				if(sum == 100) {
					Arrays.sort(result);
					for(int n: result) {
						System.out.println(n);
					}
					return;
				}
				
			}
		}

	}

}
