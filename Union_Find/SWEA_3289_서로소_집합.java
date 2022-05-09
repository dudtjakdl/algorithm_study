package Union_Find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
 * 걸린 시간 : 30분
 */
public class SWEA_3289_서로소_집합 {
	static int N;
	static int[] parents; // 각 원소의 부모를 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 집합의 개수
			int M = Integer.parseInt(st.nextToken()); // 연산의 개수
			
			makeSet(); // 1부터 n까지의 단위집합 생성
			
			System.out.print("#"+test_case+" ");
			
			// 연산 실행
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int mode = Integer.parseInt(st.nextToken()); // 연산의 종류
				int a = Integer.parseInt(st.nextToken()); // 연산을 수행할 원소1
				int b = Integer.parseInt(st.nextToken()); // 연산을 수행할 원소2
				
				switch (mode) {
				case 0: // 두 원소 합치기
					union(a, b);
					break;
				case 1: // 두 원소가 같은 집합에 있는지 체크
					// 두 원소의 대표자가 같으면 같은 집합에 있다는 뜻
					if(findSet(a) == findSet(b)) System.out.print(1);
					else System.out.print(0);
					break;
				}
			}
			
			System.out.println();
		}
	}

	// 단위집합 생성
	public static void makeSet() {
		parents = new int[N+1];
		// 자신의 부모노드를 자신의 값으로 세팅
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	// a,b 두 집합 합치기
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot != bRoot) parents[bRoot] = aRoot;
	}
}
