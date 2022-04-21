package String;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/11654
 * 걸린 시간 : 5분
 */
public class BOJ_11654_아스키_코드 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// String으로 한줄 읽어와서 String의 첫번째 문자 char형으로 변환
		char ch = sc.nextLine().charAt(0);
		
		// char형 문자 int로 강제 형변환 -> 아스키코드 값으로 변환돼서 출력
		System.out.println((int) ch);
	}

}
