package Divide_Conquer;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1629
 * 걸린 시간 : 40분
 * 리뷰 : 지수 법칙과 모듈러 성질을 알아야 풀 수 있는 문제였다.
 * 모듈러 성질에 대해 잘 몰랐는데 이 문제를 통해
 * (a * b) % c = (a % c * b % c) % c 가 성립한다는 것을 알 수 있었다.
*/
public class BOJ_1629_곱셈 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong(); // 밑
		long B = sc.nextLong(); // 지수
		long C = sc.nextLong(); // 거듭제곱 결과 나눌 숫자

		System.out.println(divide(A, B, C)%C);
	}
	
	private static long divide(long base, long exp, long mod) {
		if(exp == 0) { // 지수가 0인 경우 값은 항상 1이므로 1 반환
			return 1;
		}
		
		long temp = divide(base, exp/2, mod)%mod;
		
		if(exp % 2 == 0) { // 지수가 짝수인 경우
			return (temp*temp)%mod;
		}
		else { // 지수가 홀수인 경우
			return (((temp*temp)%mod)*base)%mod;
		}
	}

}
