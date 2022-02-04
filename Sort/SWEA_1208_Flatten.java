package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
 * 걸린 시간 : 30분
 * 리뷰 : 가장 먼저 가장 높은 블록과 낮은 블록을 구하기 위해 정렬을 해야한다고 생각했다.
 * length-1 번째 위치가 가장 높은 블록, 0 번째 위치가 가장 낮은 블록이므로
 * 덤프 횟수만큼 덤프를 반복하면서 계속 정렬을 해주도록 했다.
 * 이러한 아이디어만 생각해준다면 금방 풀 수 있는 문제였던 것 같다.
*/
public class SWEA_1208_Flatten {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10; // 테스트 케이스 개수
		List<Integer> boxes = new ArrayList<Integer>();
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(bf.readLine()); // 덤프 횟수
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int size = st.countTokens(); // 블록의 개수
			
			boxes.clear();
			
			for(int i = 0; i < size; i++) {
				boxes.add(Integer.parseInt(st.nextToken()));
			}
			int height = -1; // 가장 높은 곳과 가장 낮은 곳의 차이
			
			for(int i = 0; i < N; i++) {
				Collections.sort(boxes);
				int max = boxes.get(size-1);
				int min = boxes.get(0);
				
				height = max - min;
				
				if(height == 0 || height == 1) {
					break;
				}
				
				// 덤프 실행
				boxes.set(size-1, max-1);
				boxes.set(0, min+1);
			}
			
			Collections.sort(boxes);
			height = boxes.get(size-1) - boxes.get(0);
			
			System.out.printf("#%d %d\n", test_case, height);
		}

	}

}
