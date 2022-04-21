package String;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1152
 * 걸린 시간 : 10분
 */
public class BOJ_1152_단어의_개수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine(); // 문자열 입력

		s = s.trim(); // 문자열의 앞뒤 공백 제거
		
		String[] split = s.split(" "); // 공백을 기준으로 split
		
		// split한 첫번째 값이 비어있으면("") 단어의 개수는 0
		if (split[0].equals("")) System.out.println(0);
		// split 배열의 크기가 곧 단어의 개수
		else System.out.println(split.length);
	}

}
