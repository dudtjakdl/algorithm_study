package Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1717
 * 걸린 시간 : 30분
*/
public class BOJ_1717_집합의_표현 {
	static int[] parents; // 각 원소의 부모를 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 0부터 n까지 원소
		int m = Integer.parseInt(st.nextToken()); // 연산의 개수
		
		makeSet(n); // 0부터 n까지 단위 집합 생성
		
		// 연산
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b, op);
		}

	}

	// 단위 집합 생성
	private static void makeSet(int n) {
		parents = new int[n+1];
		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}
	}
	
	// a의 집합 찾기 : a의 대표자 찾기
	private static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	// a,b 두 집합 합치기
	private static void union(int a, int b, int op) { // op는 연산 종류 (0 또는 1)
		int aRoot = findSet(a); // a의 대표자
		int bRoot = findSet(b); // b의 대표자
	
		if(op == 0 && aRoot != bRoot) { // 연산이 0이고 a와 b가 같은 집합이 아니면 합치기
			parents[bRoot] = aRoot;
		}
		else if(op == 1 && aRoot == bRoot) { // 연산이 1이고 a와 b가 같은 집합이면 YES 출력
			System.out.println("YES");
		}
		else if(op == 1 && aRoot != bRoot){ // 연산이 1이고 a와 b가 같은 집합이 아니면 NO 출력
			System.out.println("NO");
		}
	}

}
