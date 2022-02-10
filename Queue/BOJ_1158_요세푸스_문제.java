package Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1158
 * 걸린 시간 : 20분
*/
public class BOJ_1158_요세푸스_문제 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		
		// 리스트에 1번부터 N번까지 입력
		for(int i=1; i<=N; i++) {
			queue.offer(i);
		}
		
		int cnt = 0;
		System.out.print("<");
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
		
			cnt++;
			
			if(cnt == K) {
				if(queue.isEmpty()) {
					System.out.print(temp+">");
					break;
				}
				
				System.out.print(temp+", ");
				cnt = 0;
			}
			else {
				queue.offer(temp);
			}
		}

	}

}
