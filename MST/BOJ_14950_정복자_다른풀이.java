package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14950_정복자_다른풀이 {
	static public class Edge implements Comparable<Edge> {
		int from, to, weight;

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
	static boolean[] isChecked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		edgeList = new Edge[M]; // 간선 리스트
		
		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		makeSet();
		isChecked = new boolean[N+1];
		isChecked[1] = true;
		Arrays.sort(edgeList);
		
		int ans = 0;
		int cnt = 0;
		
		while (cnt != N-1) {
			for (Edge edge : edgeList) {
				if (!isChecked[edge.from] && !isChecked[edge.to]) continue;
				
				if (union(edge.from, edge.to)) {
					int weight = edge.weight + (cnt*t);
					ans += weight;
					cnt += 1;
//					System.out.println(edge.from + " " + edge.to+" 연결됨");
//					System.out.println("weight: "+weight+" ans: "+ans);
					isChecked[edge.from] = true;
					isChecked[edge.to] = true;
					break;
				}
			}
		}

		System.out.println(ans);
	}
	
	private static void makeSet() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	private static int findSet(int a) {
		if (a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
}
