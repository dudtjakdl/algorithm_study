package Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc
 * 걸린 시간 : 60분
 * 리뷰 : 각 배열 요소의 값인 방 번호를 key로 하고 각 방의 상하좌우에 몇번 방 번호가 있는지를 저장한 배열을 value로 하여, HashMap을 만들어서 풀었다. 
 * 이미 한번이라도 방문한적 있으면 그 방은 더 짧은 방문횟수를 가지므로, 굳이 그 방은 이동해보지 않아도 되게 최적화 해주었으면 더 좋았을 것 같다. (visited 배열 사용으로 방문 체크)
 * 또한 HashMap 말고, dfs를 이용해 재귀적으로 방을 타고 타서 이동하는 방법으로 구현해 주었어도 좋았을 것 같다.
*/
public class SWEA_1861_정사각형_방 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case=1; test_case<=T; test_case++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			
			// 입력
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			Map<Integer, List<Integer>> hMap = new HashMap<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					List<Integer> list = new ArrayList<>(); 
					// 현재 방을 기준으로 상하좌우가 몇번 방인지 저장
					// 경계를 넘어서면 0을 저장
					
					// 상
					if(i-1 >= 0) list.add(map[i-1][j]);
					else list.add(0);
					
					// 하
					if(i+1 < N) list.add(map[i+1][j]);
					else list.add(0);
					
					// 좌
					if(j-1 >= 0) list.add(map[i][j-1]);
					else list.add(0);
					
					// 우
					if(j+1 < N) list.add(map[i][j+1]);
					else list.add(0);
					
					// key: 현재 방의 번호 value: 상화좌우가 몇번 방인지 list
					hMap.put(map[i][j], list);
				}
			}
			
			int start = 0; // 시작 방 번호
			int max = 0; // 결과값 (가장 많이 이동한 횟수)
			
			for(int key: hMap.keySet()) {
				int current = key; // 현재 위치한 방 번호
				int cnt = 1; // 이동 횟수
				
				while(true) {
					// 상하좌우에 현재 위치한 방 번호+1 이 있는지 확인
					if(hMap.get(current).contains(current+1)) {
						current++;
						cnt++;
					}
					else {
						break;
					}
				}
				
				if(max < cnt) { // 이동 횟수가 max보다 크면 교체
					start = key;
					max = cnt;
				}
				else if(max == cnt) { // 최댓값이 같으면 방 번호가 작은 것으로 바꿈
					if(start > key) {
						start = key;
					}
				}
			}
			
			// 출력
			System.out.println("#"+test_case+" "+start+" "+max);
		}

	}

}
