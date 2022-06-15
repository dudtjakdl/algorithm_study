package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/14950
 * 걸린 시간 : 90분
 * 리뷰 : 처음 점거하고 있는 도시가 1번 도시라고 했기에, 무조건 1번 도시부터 시작해서 다익스트라 또는 그래프 탐색으로
 * 풀어야 겠다고 착각할 수 있는 문제였다.
 * 하지만 최소신장트리를 생각해보면 어쨌든 최소 비용으로 모든 노드가 포함되므로 어디서 시작하든 1번 도시는 반드시 포함된다.
 * 그러므로 mst 알고리즘을 적용하면 간단하게 풀 수 있는 문제였다.
 * 하나 까다로운건 비용이 t만큼 증가하는 것인데 이는 최소비용을 구하고 거기에 t*1+t*2+...+t*(n-2) 를 더해주면 간단하게 계산할 수 있다.
*/
public class BOJ_14950_정복자 {
	static public class Edge implements Comparable<Edge> { // 간선 클래스
		int from, to, weight; // 시작점, 도착점, 가중치(비용)

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
		
	}
	
	static int N, M, t;
	static Edge[] edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		t = Integer.parseInt(st.nextToken()); // 증가하는 도로의 비용
		edgeList = new Edge[M]; // 간선 리스트
		
		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		makeSet(); // 각 정점의 단위 집합 생성
		Arrays.sort(edgeList); // 간선비용을 기준으로 오름차순 정렬
		
		int ans = 0; // 결과값 (최소 신장 트리의 가중치 합)
		int cnt = 0; // 경로를 이은 횟수
		
		// kruskal 알고리즘
		for (Edge edge : edgeList) { // 모든 간선을 하나하나 다 연결해보기 
			if (union(edge.from, edge.to)) {
				ans += edge.weight;
				if (++cnt == N-1) break;
			}
		}
		
		ans += ((N-2)*(N-1)/2)*t; // 최소비용에 t만큼 증가하는 비용 더해주기
	
		// 결과 출력
		System.out.println(ans);
	}
	
	// 단위집합 생성
	private static void makeSet() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	// a의 집합 찾기 : a의 대표자 찾기
	private static int findSet(int a) {
		if (a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	// a,b 두 집합 합치기 -> 같은 경로로 포함시키기
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
}
