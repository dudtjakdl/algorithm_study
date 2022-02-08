package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD
 * 걸린 시간 : 20분
*/
public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int T = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int size = st.countTokens();
			Queue<Integer> queue = new LinkedList<>();
			
			for(int i=0; i<size; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			while(true) {
				boolean finish = false;
				
				for(int i=1; i<=5; i++) {
					int pollNum = queue.poll();
					int offerNum = pollNum - i;
					
					if(offerNum <= 0) {
						queue.offer(0);
						finish = true;
						break;
					}
					queue.offer(offerNum);
				}
				
				if(finish) break;
			}
			
			System.out.print("#" + T + " ");
			while(!queue.isEmpty()) {
				System.out.print(queue.poll() + " ");
			}
			System.out.println();
			
		}

	}

}
