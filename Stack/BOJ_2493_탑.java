package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT#none
 * 걸린 시간 : 2시간 이상
*/
public class BOJ_2493_탑 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 탑의 수
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<int[]> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int[] element;
		
		for(int i=0; i<N; i++) {
			element = new int[2];
			int height = Integer.parseInt(st.nextToken());
			element[0] = height;
			element[1] = i+1;
			if(stack.isEmpty()) {
				stack.push(element);
				sb.append(0 + " ");
			}
			else if(stack.peek()[0] < height) {
				while(!stack.isEmpty() && stack.peek()[0] < height) {
					stack.pop();
				}			
				if(stack.isEmpty()) {
					stack.push(element);
					sb.append(0 + " ");
				}
				else {
					sb.append(stack.peek()[1] + " ");
					stack.push(element);
				}
			}
			else if(stack.peek()[0] >= height) {
				sb.append(stack.peek()[1] + " ");
				stack.push(element);
			}
		}
		System.out.println(sb);
	}

}
