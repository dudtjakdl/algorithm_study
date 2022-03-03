package Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1753
 * 걸린 시간 : 60분
 * 리뷰 : 입력이 간선 정보 형태로 주어졌기 때문에 시작정점번호를 인덱스로하는 정점 인접리스트를 사용하여 다익스트라를 구현하였다.
 * 입력이 인접배열로 주어지는지 또는 간선 정보로 주어지는지 등에 따라 자유자재로 알고리즘을 적용할 수 있도록 연습이 필요하다고 느꼈다.
*/
public class BOJ_1753_최단경로 {
	public static class Vertex implements Comparable<Vertex> {
		int no, distance; // 정점번호, 거리(가중치)

		public Vertex(int no, int distance) {
			super();
			this.no = no;
			this.distance = distance;
		}

		@Override
		public int compareTo(Vertex o) { // 거리를 기준으로 오름차순 정렬
			return this.distance - o.distance;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호
		
		List<List<Vertex>> adjList = new ArrayList<>(); // 정점 인접리스트 (인덱스는 시작정점번호)
		
		// 정점 개수만큼 인정리스트 초기화
		for (int i = 0; i <= V; i++) {
			adjList.add(new ArrayList<>());
		}
		
		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken()); // 시작정점번호
			int v = Integer.parseInt(st.nextToken()); // 도착정점번호
			int w = Integer.parseInt(st.nextToken()); // 거리(가중치)
			
			adjList.get(u).add(new Vertex(v, w)); // 인접리스트에 추가
		}
		
		int[] distance = new int[V+1]; // 출발지에서 자신으로 오는 최소비용
		boolean[] visited = new boolean[V+1]; // 최소비용 확정여부
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>(); // 최소비용인 정점부터 탐색하기위한 우선순위큐
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0; // 시작점 최소비용 0으로 초기화
		pQueue.offer(new Vertex(K, 0)); // 시작점 정점 넣어주기
		
		while(!pQueue.isEmpty()) {
			// 단계1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
			Vertex current = pQueue.poll();
			
			if(visited[current.no]) continue; // 이미 최소비용이 확정된 정점이면 pass
			
			visited[current.no] = true; // 최소비용 확정 체크
			
			// 단계2 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른정점의 최소비용을 고려
			for(Vertex temp : adjList.get(current.no)) {
				if(!visited[temp.no] && distance[temp.no] > distance[current.no] + temp.distance) {
					distance[temp.no] = distance[current.no] + temp.distance;
					pQueue.offer(new Vertex(temp.no, distance[temp.no]));
				}
			}
		}
		
		// 결과 출력
		for (int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}

}
