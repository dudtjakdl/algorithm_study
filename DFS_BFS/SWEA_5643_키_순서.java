package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
 * 걸린 시간 : 60분
 * 리뷰 : 자신이 키가 몇 번째인지 알 수 있으려면 (나보다 작은 학생 수+나보다 큰 학생 수)=(전체 학생 수-1) 을 만족하여야 한다.
 * 자기 자신보다 작은 학생 수와 큰 학생 수를 구하기 위해 Student 라는 객체에 자신보다 크고 작은 학생들의 번호 리스트를 만들어 관리해주었다.
 * 그리고 dfs 탐색을 통해 학생을 타고타고 가서 자신보다 작은 학생 수 와 큰 학생수를 카운트 해주었다. 
 */
public class SWEA_5643_키_순서 {
	public static class Student {
		int num; // 자신의 번호
		List<Integer> small; // 자신보다 작은 학생들의 번호 리스트
		List<Integer> big; // 자신보다 큰 학생들의 번호 리스트
		
		public Student(int num, List<Integer> small, List<Integer> big) {
			super();
			this.num = num;
			this.small = small;
			this.big = big;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 학생들의 수
			int M = Integer.parseInt(br.readLine()); // 키를 비교한 횟수
			Student[] students = new Student[N+1]; // 각 학생들의 객체를 담을 배열
			
			// 1번부터 N번 학생 객체 선언 및 초기화
			for (int i = 1; i <= N; i++) {
				students[i] = new Student(i, new ArrayList<Integer>(), new ArrayList<Integer>());
			}
			
			// 키 비교 정보 입력
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				students[a].big.add(b); // a보다 큰 학생 리스트에 b 추가
				students[b].small.add(a); // b보다 작은 학생 리스트에 a 추가
			}
			
			int ans = 0; // 문제의 답 (키가 몇 번째인지 알 수 있는 학생 수)
			
			// 1번부터 N번 학생까지 자신이 키가 몇 번째인지 알 수 있는지 체크
			for (int i = 1; i <= N; i++) {
				int smallCnt = 0; // 자신보다 작은 학생의 수
				int bigCnt = 0; // 자신보다 큰 학생의 수
				boolean[] visited = new boolean[N+1]; // 각 학생을 이미 고려했는지 체크하는 배열
				
				Queue<Student> queue = new LinkedList<Student>();
				visited[i] = true; // 자기 자신 방문체크
				queue.offer(students[i]); // 자기 자신 queue에 넣기
				
				// 자신보다 작은 학생들 bfs 탐색
				while (!queue.isEmpty()) {
					Student current = queue.poll();
					
					// 현재 학생보다 작은 학생들 리스트 불러와서 한명씩 고려해보기
					for (int num : current.small) {
						// 고려해보지 않은 학생이면 카운트 1 증가 및 방문체크
						if(!visited[num]) {
							smallCnt++;
							visited[num] = true;
							queue.offer(students[num]);
						}
					}
				}
				
				queue.offer(students[i]); // 자기 자신 queue에 넣기
				
				// 자신보다 큰 학생들 bfs 탐색
				while (!queue.isEmpty()) {
					Student current = queue.poll();
					
					// 현재 학생보다 큰 학생들 리스트 불러와서 한명씩 고려해보기
					for (int num : current.big) {
						// 고려해보지 않은 학생이면 카운트 1 증가 및 방문체크
						if(!visited[num]) {
							bigCnt++;
							visited[num] = true;
							queue.offer(students[num]);
						}
					}
				}
				
				// (나보다 작은 학생 수+나보다 큰 학생 수)가 전체 학생 수보다 1 작으면 키가 몇번째인지 알 수 있음
				if (smallCnt+bigCnt == N-1) ans++;
			}
			
			// 결과 출력
			System.out.println("#"+test_case+" "+ans);
		}
	}

}
