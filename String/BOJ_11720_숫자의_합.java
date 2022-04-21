package String;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/11720
 * 걸린 시간 : 10분
 * 리뷰 : 입력을 받을 때 sc.nextInt() 다음에 sc.nextLine()을 썼더니 입력이 제대로 받아와지지 않았다.
 * 위쪽에서 숫자의 개수 N을 입력하고 엔터를 누르면 엔터값을 문자열로 인식하여 nextLine()에서 엔터값을 받아오는 문제점이 생겼다.
 * 이를 방지하기 위해 두번째 줄 입력은 sc.next()로 받아와서 해결해주었다.
 */
public class BOJ_11720_숫자의_합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 숫자의 개수
		String s = sc.next(); // 숫자 N개 문자열로 받아오기

		char[] nums = s.toCharArray(); // 숫자 문자열 char 배열로 변환
		int ans = 0; // 숫자의 총합
		
		// 각 숫자에 문자 '0'을 빼서 숫자형으로 바꿔주기 -> 해당 숫자 총합에 누적
		for (int i = 0; i < N; i++) {
			ans += nums[i] - '0';
		}

		// 결과 출력
		System.out.println(ans);
	}

}
