package Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
 * 걸린 시간 : 30분
 * 리뷰 : 완전 이진 트리의 특성을 이용해서 N/2 이하의 정점은 자식노드를 하나 이상 가진 노드이고,
 * N/2를 넘는 정점은 자식노드가 없는 리프노드라는 조건을 이용해서 풀었어도 좋았을 것 같다.
 */
public class SWEA_1233_사칙연산_유효성_검사 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=10; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 정점의 개수
			
			int isValid = 1; // 유효성
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
				st.nextToken(); // 정점 번호는 생략
				
				String value = st.nextToken(); // 정점의 value
				
				if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
					// value가 알파벳이면 자식 노드가 있어야함
					if(!st.hasMoreTokens()) {
						isValid = 0;
					}
				}
				else {
					// value가 숫자면 자식 노드가 없어야함
					if(st.hasMoreTokens()) {
						isValid = 0;
					}
				}
			}
			System.out.println("#"+test_case+" "+isValid);
		}
		
	}

}
