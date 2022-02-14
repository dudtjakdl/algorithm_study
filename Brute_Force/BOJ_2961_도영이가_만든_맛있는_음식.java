package Brute_Force;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2961
 * 걸린 시간 : 30분
 * 리뷰 : 재료를 선택하는 경우의 수를 부분집합으로 구해주는 문제였다.
 * 한가지 중요한 점은 문제에서 재료를 적어도 하나 사용해야 한다 했으므로, 
 * 아무 재료도 선택하지 않는 경우의 수도 구해지기 때문에 이는 조건문을 넣어서 제외시켜줘야 했다.
 */
public class BOJ_2961_도영이가_만든_맛있는_음식 {
	static int N; // 재료의 개수
	static int[][] ingredient; // 재료의 신맛과 쓴맛을 저장하는 배열
	static boolean[] isSelected; // 재료가 선택 되었는지를 체크하는 배열
	static int res = Integer.MAX_VALUE; // 요리의 신맛과 쓴맛의 차가 가장 적은 값 (결과값)
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ingredient = new int[N][2];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			ingredient[i][0] = sc.nextInt(); // 재료의 신맛 저장
			ingredient[i][1] = sc.nextInt(); // 재료의 쓴맛 저장
		}
		
		subset(0); // 재료 선택 시작
		
		System.out.println(res); // 결과 출력
	}

	// 재료를 선택하는 부분집합을 구하는 함수
	private static void subset(int cnt) { // cnt: 직전까지 고려한(선택 or 비선택) 재료 수
		if(cnt == N) { // 재료 수만큼 선택여부가 끝났으면 요리의 신맛과 쓴맛 구하기
			int S = 1; // 요리의 신맛
			int B = 0; // 요리의 쓴맛
			
			for(int i=0; i<N; i++) {
				// 재료가 선택되지 않았으면 제외
				if(!isSelected[i]) continue;
				
				S *= ingredient[i][0]; // 음식의 신맛 계산 (곱)
				B += ingredient[i][1]; // 음식의 쓴맛 계산 (합)
			}
			
			// 재료가 하나도 선택되지 않은 경우의 수는 계산 생략
			if(S == 1 && B == 0) return;
			
			int diff = Math.abs(S-B); // 음식의 신맛과 쓴맛의 차이
			res = Math.min(res, diff); // 최소값으로 업데이트
			
			return;
		}
		
		// 현재 재료를 선택
		isSelected[cnt] = true;
		subset(cnt+1);
		// 현재 재료를 비선택
		isSelected[cnt] = false;
		subset(cnt+1);
	}

}
