package Implementation;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1244
 * 걸린 시간 : 30분
 * 느낀 점 : 구현은 어려움 없이 잘 했지만 계속 출력 형식이 잘못되어서 해결하느라 시간이 많이 걸렸다.
 * 알고보니 문제에 20개를 넘으면 개행을 해야한다고 명시되어 있었는데..
 * 예제만 보지 말고 문제도 꼼꼼히 잘 읽어보자..!
*/
public class BOJ_1244_스위치_켜고_끄기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 스위치 개수
		int[] switches = new int[N]; // 각 스위치 상태 저장하는 배열

		// 배열에 각 스위치 상태 저장
		for (int i = 0; i < N; i++) {
			switches[i] = sc.nextInt();
		}

		int M = sc.nextInt(); // 학생 수

		// 학생 수 만큼 실행
		for (int i = 0; i < M; i++) {
			int sex = sc.nextInt(); // 학생 성별 (1은 남자, 2는 여자)
			int num = sc.nextInt(); // 받은 숫자

			if (sex == 1) { // 성별이 남자일 때
				for (int j = 1; j <= N; j++) { // j가 스위치 번호
					if (j % num == 0) {
						// 스위치 번호가 num의 배수일때 스위치 상태 바꾸기
						if (switches[j - 1] == 1)
							switches[j - 1] = 0;
						else if (switches[j - 1] == 0)
							switches[j - 1] = 1;
					}
				}
			} else if (sex == 2) {// 성별이 여자일 때
				int center = num - 1; // 받은 숫자의 인덱스
				int previous = center - 1; // 받은 숫자의 이전 인덱스 번호
				int next = center + 1; // 받은 숫자의 다음 인덱스 번호
				
				// 받은 숫자의 스위치 번호 바꾸기
				if (switches[center] == 1)
					switches[center] = 0;
				else if (switches[center] == 0)
					switches[center] = 1;
				
				while (true) {
					// 경계값 검사 && 이전 스위치와 다음 스위치 상태 같은지 검사
					if (previous >= 0 && next < N && switches[previous] == switches[next]) {
						if (switches[previous] == 1)
							switches[previous] = 0;
						else if (switches[previous] == 0)
							switches[previous] = 1;

						switches[next] = switches[previous];
						previous--;
						next++;
					} else
						break;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (i != 0 && i % 20 == 0) { //20개를 넘어가면 개행
				System.out.println();
			}
			System.out.print(switches[i] + " ");
		}

	}

}
