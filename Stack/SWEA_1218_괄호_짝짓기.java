package Stack;

import java.util.Scanner;
import java.util.Stack;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD
 * 걸린 시간 : 30분
*/
public class SWEA_1218_괄호_짝짓기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();
		int result = 1;
		
		for(int test_case=1; test_case<=10; test_case++) {
			int N = sc.nextInt();
			char[] chars = sc.next().toCharArray();
			result = 1;
			
			for(char ch : chars) {
				if(result == 0) {
					break;
				}
				else if(ch == '{' || ch == '[' || ch == '<' || ch == '(') {
					stack.push(ch);
				}
				else {
					char popChar = stack.pop();
					switch(ch) {
					case '}':
						if(popChar != '{') result = 0;
						break;
					case ']':
						if(popChar != '[') result = 0;
						break;
					case '>':
						if(popChar != '<') result = 0;
						break;
					case ')':
						if(popChar != '(') result = 0;
						break;
					}
					
				}
			}
			System.out.printf("#%d %d\n",test_case, result);
		}
	}
}
