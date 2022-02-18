package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw
 * 걸린 시간 : 80분
 * 리뷰 : 시간초과에 걸리지 않기 위해 모든 경우의 수를 다 놓아보지 않고,
 * 오른쪽 저울이 무거워지는 경우 가지치기를 통해 그 뒤에 추를 놓는 경우를 모두 배제시켜야 하는 문제였다.
 * 하지만 이렇게 풀었는데도 시간초과가 나서 무엇이 문제인가 했는데,
 * 추를 양팔저울 위에 올려놓을때마다 따로 함수를 선언해서 왼쪽과 오른쪽의 무게를 for문 탐색을 통해 계속 계산해주었다.
 * 하지만 왼쪽과 오른쪽의 총 무게를 매개변수로 계속 넘겨주면 굳이 for문 탐색을 통한 무게계산이 필요가 없으므로 시간이 절약된다. 
 * 또한 왼쪽과 오른쪽의 무게 정보를 가지고있는 배열도 필요없어지므로 메모리도 절약된다.
 */
public class SWEA_3234_준환이의_양팔저울 {
	static int N;
	static int[] weight, order;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 무게추 개수
			weight = new int[N]; // 무게추 무게를 저장하는 배열
			order = new int[N]; // 저울에 놓을 순서대로 무게추 무게를 저장하는 배열
			
			// 무게추 무게 입력
			st = new StringTokenizer(br.readLine(), " "); 
			for(int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			res = 0; // 결과값 초기화
			
			permutation(0, 0);
			
			System.out.println("#"+test_case+" "+res);
		}
		
	}

	// 무게추를 놓는 순서를 정하는 함수 -> 순열
	private static void permutation(int cnt, int flag) {
		if(cnt == N) { // 순서를 다 정했으면 양팔저울에 올려놓기 시작
			putWeight(0, 0, 0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1 << i) != 0) continue;
			
			order[cnt] = weight[i];
			
			permutation(cnt+1, flag | 1 << i);
		}
		
	}	
	
	/**
	 * @param cnt: 직전까지 추를 올려놓은 횟수
	 * @param leftSum: 현재 왼쪽 저울의 무게
	 * @param rightSum: 현재 오른쪽 저울의 무게
	 */
	private static void putWeight(int cnt, int leftSum, int rightSum) { // 양팔저울에 추를 올려놓는 함수
		if(cnt == N) { // 모든 추를 다 놓은 경우
			// 오른쪽 무게가 더 무겁지 않으면 가능한 경우의 수 1 증가
			if(rightSum <= leftSum) res++;
			return;
		}
		
		if(rightSum > leftSum) { // 가지치기
			// 현재까지 놓여진 무게 중 오른쪽이 무거운 상태이면 더이상 안놓아보고 return 
			return;
		}

		// 오른쪽에 올려놓기
		putWeight(cnt+1, leftSum, rightSum+order[cnt]);
		
		// 왼쪽에 올려놓기
		putWeight(cnt+1, leftSum+order[cnt], rightSum);
		
	}

}
