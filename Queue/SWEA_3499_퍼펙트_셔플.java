package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWGsRbk6AQIDFAVW
 * 걸린 시간 : 20분
 * 리뷰 : 덱1과 덱2 그리고 두개의 덱을 섞은 result 덱을 각각 큐에 넣고 빼면서 관리하도록 코드를 짰다.
 * 그리고 덱을 두개로 나누기 위해서 그 기준이 되는 center 변수를 선언하였고,
 * 카드의 수가 홀수면 덱1이 하나 더 많이 나눠지므로, 카드 수가 홀수면 center 값이 올림이 되도록 Math api를 사용하였다.
*/
public class SWEA_3499_퍼펙트_셔플 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 카드의 개수
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int center = (int) Math.ceil((double) N / 2);
			Queue<String> queue1 = new LinkedList<>();
			Queue<String> queue2 = new LinkedList<>();
			Queue<String> result = new LinkedList<>();
			
			for(int i=0; i<center; i++) {
				queue1.offer(st.nextToken());
			}
			
			for(int i=center; i<N; i++) {
				queue2.offer(st.nextToken());
			}
			
			while(result.size() != N) {
				if(!queue1.isEmpty()) {
					result.offer(queue1.poll());
				}
				if(!queue2.isEmpty()) {
					result.offer(queue2.poll());
				}
			}
			
			System.out.print("#" + test_case + " ");
			for(int i=0; i<N; i++) {
				System.out.print(result.poll() + " ");
			}
			System.out.println();
		}
		
		
	}

}
