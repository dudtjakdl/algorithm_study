package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1197
 * 걸린 시간 : 20분
 * 리뷰 : 간선의 정보가 입력값으로 주어졌으니 kruskal 알고리즘을 사용해서 풀었다.
 * 만약 입력이 정점 정보로 이루어졌거나 간선이 너무 많으면 prim 알고리즘을 써서 푸는게 더 좋을 것 같다.
*/
public class BOJ_1197_최소_스패닝_트리 {
	static class Edge implements Comparable<Edge>{ // 간선 정보를 저장하는 클래스
		int from, to, weight; // 시작점, 도착점, 가중치

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) { // 가중치 오름차순 정렬
			return this.weight - o.weight;
		}
	}
	
	static int V; // 정점의 개수
	static int[] parents; // 각 정점의 부모를 저장하는 배열
	static Edge[] edgeList; // 간선 리스트
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		edgeList = new Edge[E];
		
		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); // 간선비용(가중치)의 오름차순 정렬
		makeSet(); // 각 간선의 단위 집합 생성
		
		int result = 0; // 결과값 (최소 신장 트리의 가중치 합)
		int cnt = 0; // 경로를 이은 횟수
		
		for (Edge edge : edgeList) { // 모든 간선을 하나하나 다 연결해보기 
			if(union(edge.from, edge.to)) { // 간선 연결이 되면 (싸이클이 만들어지지 않으면)
				result += edge.weight; // 결과값에 연결된 간선의 가중치 더하기
				if(++cnt == V-1) break; // V-1개의 경로가 다 이어졌으면 break
			}
		}
		
		System.out.println(result);
	}
	
	// 단위집합 생성
	public static void makeSet() {
		parents = new int[V+1];
		// 자신의 부모노드를 자신의 값으로 세팅
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	// a,b 두 집합 합치기 -> 같은 경로로 포함시키기
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		// a와 b 집합의 대표자가 같으면 이미 같은 경로에 포함된 집합이라는 뜻
		// 즉 경로를 이으면 싸이클이 생성된다는 뜻이므로 연결하지 않고 종료
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot; // b 집합의 대표자를 a 집합의 대표자로 바꾸기
		return true;
	}

}
