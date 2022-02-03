package Recursion;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/17478
 * 걸린 시간 : 15분
 * 느낀 점 : ____를 출력하는 for문이 계속 반복되니 이걸 함수로 처리해줬다면 더 좋았을 것 같다.
 * 그리고 ____ 출력 반복 횟수를 위해 n 매개변수도 추가하였는데, 굳이 n을 추가하지 않고 매개변수 N 만으로도
 * 처리하게 했으면 좋았을 것 같다.
*/
public class BOJ_17478_재귀함수가_뭔가요 {

	public static void recursion(int N, int n) { // n: ____ 출력 반복 횟수를 위한 매개변수
		if (N == 0) {
			for (int i = 0; i < n; i++) {
				System.out.printf("____");
			}
			System.out.println("\"재귀함수가 뭔가요?\"");
			for (int i = 0; i < n; i++) {
				System.out.printf("____");
			}
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			for (int i = 0; i < n; i++) {
				System.out.printf("____");
			}
			System.out.println("라고 답변하였지.");
			return;
		}

		for (int i = 0; i < n; i++) {
			System.out.printf("____");
		}
		System.out.println("\"재귀함수가 뭔가요?\"");
		for (int i = 0; i < n; i++) {
			System.out.printf("____");
		}
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		for (int i = 0; i < n; i++) {
			System.out.printf("____");
		}
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		for (int i = 0; i < n; i++) {
			System.out.printf("____");
		}
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

		recursion(N - 1, n + 1);

		for (int i = 0; i < n; i++) {
			System.out.printf("____");
		}
		System.out.println("라고 답변하였지.");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursion(N, 0);
	}

}
