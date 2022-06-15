package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/23743
 * 걸린 시간 : 60분
 * 리뷰 : 처음에는 다익스트라를 사용해서 i번째 방에서 출구로 가는 최단거리를 모두 구해서 더해주면 된다 생각했다.
 * 하지만 하나의 간선으로 연결만 되어있으면 언제든지 방을 이동하면서 워프할 수 있으므로,
 * 싸이클이 생기지 않게 모든 정점을 다 연결 해주는 최소 스패닝 문제라는걸 알아챘다.
 * 출구를 0번 정점으로 가정하여 풀면 쉽게 이 문제를 해결 가능하다.
*/
public class BOJ_23743_방탈출 {
	public static class Edge implements Comparable<Edge>{
		int from, to, weight; // 시작점, 도착점, 가중치(시간)

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 방의 개수 (정점)
		M = Integer.parseInt(st.nextToken()); // 워프의 개수 (간선)
		List<Edge> edgeList = new ArrayList<>(); // 간선 리스트
		
		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // a번 방의 번호
			int b = Integer.parseInt(st.nextToken()); // b번 방의 번호
			int c = Integer.parseInt(st.nextToken()); // a번 방과 b번 방을 잇는 시간
			edgeList.add(new Edge(a, b, c)); // a번 방과 b번 방 간선 추가
//			edgeList.add(new Edge(b, a, c));
		}
		
		// 각 방과 출구를 잇는 간선 정보 입력 -> 출구를 0번 노드로 정의
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			int t = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(i, 0, t)); // i번 방과 출구 간선 추가
		}
		
		Collections.sort(edgeList); // 간선비용을 기준으로 오름차순 정렬
		makeSet(); // 각 정점의 단위 집합 생성
		
		int ans = 0; // 결과값 (최소 신장 트리의 가중치 합)
		int cnt = 0; // 경로를 이은 횟수
		
		// kruskal 알고리즘
		for (Edge edge : edgeList) { // 모든 간선을 하나하나 다 연결해보기 
			if (union(edge.from, edge.to)) {
				ans += edge.weight;
				if (++cnt == N) break;
			}
		}
		
		// 결과 출력
		System.out.println(ans);
	}

	// 단위집합 생성
	private static void makeSet() {
		parents = new int[N+1];
		for (int i = 0; i <= N; i++) {
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
