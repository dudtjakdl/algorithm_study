package Greedy;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2138
 * 걸린 시간 : 60분
 * 리뷰 : 이 문제는 i-1번째 스위치를 최종 결정할 수 있는 스위치가 i번째 스위치라는 것을 생각하는게 핵심인 것 같다.
 * 스위치를 앞에서부터 차례대로 누르지 않고 중간중간마다 누르면, 양옆의 전구를 다시 고려해봐야하니 비효율적이다.
 * 하지만 앞에서부터 차례대로 스위치를 정답과 똑같이 최종 결정하면서 체크해주면,
 * 최소로 스위치를 조작하면서 정답과 비슷하게 전구를 끄고 킬 수 있다.
 * 그리고 맨첫번째 스위치를 끄고 키냐 두가지 경우로 나눠서 경우의 수를 2개로 하여 위의 과정을 해주면,
 * 스위치 조작을 최소로 해서 정답을 만드는 경우를 시도해 볼 수 있다.
 */
public class BOJ_2138_전구와_스위치 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 스위치와 전구의 개수
		boolean[] switches1 = new boolean[N]; // 전구의 상태를 저장하는 배열
		boolean[] switches2 = new boolean[N]; // 0번째 스위치를 한번 조작한 전구의 상태를 저장하는 배열
		boolean[] answer = new boolean[N]; // 최종으로 만들어야 하는 전구의 상태를 저장하는 배열
		
		// 스위치 상태 배열 입력
		String s = sc.next();
		for (int i = 0; i < N; i++) {
			if(s.charAt(i)-'0' == 1) {
				switches1[i] = true;
				switches2[i] = true;
			}
		}
		
		// 정답 배열 입력
		s = sc.next();
		for (int i = 0; i < N; i++) {
			if(s.charAt(i)-'0' == 1) {
				answer[i] = true;
			}
		}
		
		// switches2의 0번째 스위치 켜기
		switches2[0] = !switches2[0];
		switches2[1] = !switches2[1];
		
		int cnt1 = 0; // switches1의 스위치를 조작한 횟수
		int cnt2 = 1; // switches2의 스위치를 조작한 횟수
		
		for (int i = 1; i < N; i++) {
			// switches1 조작
			if(switches1[i-1] != answer[i-1]) {
				// i-1번째 스위치가 정답과 다르면 i-1번과 i번째 전구 상태 바꾸기 (i번째 스위치 조작하기)
				switches1[i-1] = !switches1[i-1];
				switches1[i] = !switches1[i];
				// 마지막 스위치가 아니면 i+1번째 전구 상태도 바꾸기
				if(i+1 < N) switches1[i+1] = !switches1[i+1];
				cnt1++; //스위치 조작 횟수 1증가
			}
			// switches2 조작
			if(switches2[i-1] != answer[i-1]) {
				switches2[i-1] = !switches2[i-1];
				switches2[i] = !switches2[i];
				if(i+1 < N) switches2[i+1] = !switches2[i+1];
				cnt2++;
			}
		}
		
		// switches1 정답과 같은지 확인 (하나라도 다르면 횟수 -1로 바꾸기)
		for (int i = 0; i < N; i++) {
			if(switches1[i] != answer[i]) {
				cnt1 = -1;
				break;
			}
		}
		// switches2 정답과 같은지 확인 (하나라도 다르면 횟수 -1로 바꾸기)
		for (int i = 0; i < N; i++) {
			if(switches2[i] != answer[i]) {
				cnt2 = -1;
				break;
			}
		}
		
		int res = 0; // 결과값 (출력값)
		
		if(cnt1 == -1 || cnt2 == -1) res = Math.max(cnt1, cnt2); // 둘중에 하나라도 -1이면 큰값을 결과값에 대입
		else res = Math.min(cnt1, cnt2); // 둘다 -1이 아니면 작은값을 결과값에 대입
		
		System.out.println(res); // 결과 출력
		
	}

}
