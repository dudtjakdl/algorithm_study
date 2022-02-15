package Implementation;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1592
 * 걸린 시간 : 15분
 * 리뷰 : 배열의 인덱스가 경계값을 넘어섰는지 검사할때,
 * 왼쪽은 (N+idx-L)%N , 오른쪽은 (N+idx+L)%N 을 해주면
 * 더 간단하게 배열 N 크기만큼의 범위 안으로 인덱스를 조절할 수 있을 것 같다.
 * 나머지 구하는 연산을 배열 경계값 검사할때 써줬다면 더 좋았을 것 같다.
*/
public class BOJ_1592_영식이와_친구들 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 사람의 수
		int M = sc.nextInt(); // 공 최대 받을 수 있는 횟수
		int L = sc.nextInt(); // L번째 사람에게 공 던지기
		int[] cnt = new int[N]; //각 인덱스번째 사람이 공 몇번 받았는지 카운트하는 배열
		
		int res = 0; // 공을 던진 횟수
		int i = 0; // 공을 받은 사람의 인덱스
		
		while(true) {
			
			cnt[i] += 1; // i번째 사람 공 받은 횟수 1 증가
			
			if(cnt[i] == M) break; // i번째 사람이 공을 M번 받았으면 종료
			
			// i번째 사람이 공을 받은 횟수가 홀수이면 시계방향으로 L번째 사람에게 공 던지기
			if(cnt[i]%2 == 1) i += L;
			// 짝수이면 반시계방향으로 L번째 사람에게 공 던지기
			else i -= L;
			
			if(i > N-1) i -= N; // i가 배열 크기를 초과하면 배열 크기만큼 빼주기
			else if(i < 0) i += N; // i가 배열 크기 미만이면 배열 크기만큼 더해주기
			
			res++; // 공 던진 횟수 1 증가
		}
		
		System.out.println(res); // 공을 던진 횟수 출력
	}

}
