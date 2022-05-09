package Union_Find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
 * 걸린 시간 : 30분
 * 리뷰 : 서로소집합의 개념을 이용해서 각 마을의 사람을 원소로 생각하고 무리를 집합으로 생각하여,
 * 만들어지는 집합의 수를 구한다는 개념을 이용하여 문제를 풀었다.
 * 한가지 중요했던 점은 한명만 있는 집합도 무리라고 생각하여, 그 집합도 개수의 포함시켜 줘야하는 문제였다.
 * 그래서 맨처음의 결과값을 사람의 수만큼 초기화 시키고 집합이 합쳐질때마다 -1 시켜주는 방식을 이용하여 문제를 풀었다.
 */
public class SWEA_7465_창용_마을_무리의_개수 {
	static int N;
	static int[] parents;
	static int res; // 무리의 개수 (결과값)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 원소의 수 (사람의 수)
			int M = Integer.parseInt(st.nextToken()); // 관계 수
			parents = new int[N+1]; // 각 원소의 부모를 저장하는 배열
			
			// 단위 집합 생성
			for (int i = 1; i <= N; i++) {
				parents[i] = i; // 자신의 부모노드를 자신의 값으로 세팅
			}
			
			res = N; // 무리의 개수 사람의 수로 초기화 (맨 처음은 서로 연결 안된 단위 집합의 상태이니)
			
			// 관계 수 만큼 실행
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			System.out.println("#"+test_case+" "+res); // 결과 출력
		}
	}
	
	// a가 속한 무리 찾기 -> 그 무리의 대표자 반환
	private static int findSet(int a) {
		if(parents[a] == a) return a; // 자기자신이 무리의 대표자이면 자기자신 반환
		parents[a] = findSet(parents[a]); // path compression -> 무리의 대표자로 부모노드 변경 
		return parents[a];
	}
	
	// a와 b 관계 묶기
	private static void union(int a, int b) {
		int aRoot = findSet(a); // a가 속한 무리의 대표자
		int bRoot = findSet(b); // b가 속한 무리의 대표자
		
		// 서로 속한 무리가 다르면(대표자가 다르면) 한 무리로 묶기
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
			res--; // 무리의 총 개수 1 감소
		}
		
		// 이미 같은 무리이면(대표자가 같으면) 아무것도 하지 않기
	}
}
