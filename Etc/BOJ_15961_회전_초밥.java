package Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/15961
 * 걸린 시간 : 60분
 * 리뷰 : 먼저 초밥 벨트 배열을 k-1만큼 뒤에 늘려주었는데 그 이유는, 초밥 벨트는 원으로 이뤄어져 배열 첫번째값과
 * 마지막값이 서로 이어져야 하기 때문이다.
 * 하지만 이 방법 말고도 % 연산을 이용해서 배열의 끝에 도달하면 나머지 값을 이용하는 방법도 있다.
 * 그리고 벨트의 초밥을 k개씩 계속 탐색을 하면은 O(n*k) 라는 시간복잡도가 나오지만,
 * left와 right라는 변수를 포인터로 활용하여 첫번째 초밥은 빼주고 마지막 초밥은 더해주는 방법으로
 * O(n)의 시간복잡도로 풀 수 있었다.
 */
public class BOJ_15961_회전_초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] arr = new int[N+k-1]; // 벨트에 놓인 초밥 배열
		int[] count = new int[d+1]; // idx번 초밥을 몇번 먹었는지 카운트하는 배열
		
		// 벨트에 놓인 초밥 번호 입력
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 벨트 앞에 놓인 순서대로 k-1개만큼 초밥 배열 늘리기
		for (int i = N; i < N+k-1; i++) {
			arr[i] = arr[i-N];
		}
		
		int left = 0; // 처음으로 먹을 초밥 번호
		int right = k-1; // 마지막으로 먹을 초밥 번호
		int num = 0; // 몇 종류의 초밥을 먹었는지
		int ans = 0; // 문제의 답
		
		// 벨트에 놓인 첫번째 초밥부터 k개 먹기
		for (int i = left; i <= right; i++) {
			// 아직 먹어보지 않은 초밥이면 종류 1 증가
 			if(count[arr[i]] == 0) num++;
 			// 초밥을 먹은 횟수 1 증가
			count[arr[i]] += 1;
		}
		
		// ans 초기화
		// 쿠폰번호의 초밥을 먹지 않았으면 서비스로 쿠폰번호 초밥 먹기 -> 먹은 종류 1 증가
		if(count[c] == 0) ans = num+1;
		else ans = num;
		
		// left와 right을 1 증가시키면서 연속으로 k개씩 초밥 먹어보기
		while (right < arr.length-1) {
			left++;
			right++;
			
			// 이전 phase에서 처음으로 먹었던 초밥을 1번 먹었었으면 종류 1 감소
			if(count[arr[left-1]] == 1) num--;
			count[arr[left-1]] -= 1;
			
			// 현재 phase에서 마지막으로 먹을 초밥을 한번도 먹어보지 않았으면 종류 1 증가
			if(count[arr[right]] == 0) num++;
			count[arr[right]] += 1;
			
			// 쿠폰번호의 초밥을 먹지 않았으면 서비스로 쿠폰번호 초밥 먹기 -> 먹은 종류 1 증가
			if(count[c] == 0) {
				// num+1한 값과 ans값을 비교해서 큰값으로 갱신
				ans = Math.max(ans, num+1);
			}
			else {
				// num과 ans값을 비교해서 큰값으로 갱신
				ans = Math.max(ans, num);
			}
		}
		
		// 결과 출력
		System.out.println(ans);
	}

}
