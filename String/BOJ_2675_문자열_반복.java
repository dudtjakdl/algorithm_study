package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2675
 * 걸린 시간 : 10분
 */
public class BOJ_2675_문자열_반복 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken()); // 반복 횟수
			String S = st.nextToken(); // 문자열
			StringBuilder sb = new StringBuilder();
			
			// 문자열 길이만큼 반복
			for (int i = 0; i < S.length(); i++) {
				char ch = S.charAt(i); // 반복 출력할 문자
				// 반복 횟수만큼 문자 추가
				for (int j = 0; j < R; j++) {
					sb.append(ch);
				}
			}
			
			System.out.println(sb.toString()); // 완성된 P 출력
		}
	}

}
