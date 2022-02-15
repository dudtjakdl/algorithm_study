package Greedy;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2839
 * 걸린 시간 : 25분
 * 리뷰 : 먼저 그리디 알고리즘으로 최적의 방법으로 풀어보고, 최적의 방법이 먹히지 않는 경우이면 
 * 완전 탐색으로 모든 경우의 수를 다 계산해보는 방법으로 풀었다.
 * 다른 풀이 중 남은 kg이 5로 정확히 나누어 떨어지지 않으면 남은 kg에 -3을 해주고,
 * 또 남은 kg을 5로 나누거나 3을 빼주는 과정을 반복해서 해를 구하는게 가장 베스트 풀이인 것 같다.
 */
public class BOJ_2839_설탕_배달 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 배달해야하는 설탕 kg
		int res = 0; // 결과값 (봉지의 최소 개수)
		
		int kg = N; // 배달해야하는 설탕 kg
		
		// 그리디 알고리즘으로 최적으로 풀기 (5kg부터 들 수 있을 만큼 들고 남은 kg는 3kg로 들기)
		res += kg / 5;
		kg %= 5;
		
		res += kg / 3;
		kg %= 3;
		
		// 만약 최적으로 풀었을 시 남은 설탕 kg이 있으면 완전 탐색으로 풀기
		if(kg != 0) {
			kg = N;
			res = Integer.MAX_VALUE;
			
			// i: 5kg짜리 봉지 수 j: 3kg짜리 봉지 수
			for(int i=0; i<= kg/5; i++) {
				for(int j=0; j<= kg/3; j++) {
					if((5*i)+(3*j) == kg) { // 총 봉지의 무게가 18kg이면 결과값 i+j(설탕 봉지 개수) 최소값으로 업데이트
						res = Math.min(res, i+j);
					}
				}
			}
		}
		
		// 결과값이 Integer.MAX_VALUE 이면 완전탐색으로도 N킬로그램을 만족하지 못했으니 -1 출력
		if(res == Integer.MAX_VALUE) res = -1;
		
		System.out.println(res);
	}

}
