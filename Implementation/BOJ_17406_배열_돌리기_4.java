package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/17406
 * 걸린 시간 : 120분
 * 리뷰 : 배열 돌리기 1 에서 풀었던 회전을 시계방향으로 바꿔주었고, 연산 순서를 정하는 방법을
 * 순열로 구해주어 모든 경우의 수를 실행하여 배열을 회전 해주는 로직으로 풀었다.
*/
public class BOJ_17406_배열_돌리기_4 {
	static int N, M, K;
	static int[][] map, op, rotation, mapCopy;
	static boolean[] isSelected; 
	static int min = Integer.MAX_VALUE; // 결과값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 행의 크기
		M = Integer.parseInt(st.nextToken()); // 열의 크기
		K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수
		map = new int[N][M]; // 배열 A
		mapCopy = new int[N][M]; // 배열 A를 복사하는 임시 배열
		
		// 배열 값 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		op = new int[K][3]; // 회전 연산 정보 (r, c, s)
		isSelected = new boolean[K]; // 순열을 구하기 위해 중복 체크를 하는 배열
		rotation = new int[K][3]; // 연산 수행 순서
		
		// 회전 연산 정보 입력
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			op[i][0] = Integer.parseInt(st.nextToken()); // r
			op[i][1] = Integer.parseInt(st.nextToken()); // c
			op[i][2] = Integer.parseInt(st.nextToken()); // s
		}
		
		permutation(0);
		
		// 결과 출력
		System.out.println(min);
	}
	
	// 회전 연산 수행 순서를 구하고 연산을 시작하는 함수 (순열)
	public static void permutation(int cnt) { // cnt : 직전까지 뽑은 연산 개수
		
		if(cnt == K) { // 연산을 다 뽑았으면 연산 시작
			
			// 원본배열 복사
			for(int k=0; k<N; k++) {
				for(int l=0; l<M; l++) {
					mapCopy[k][l] = map[k][l];
				}
			}
			
			// 회전 연산 수 만큼 실행
			for(int i=0; i<K; i++) {

				int r = rotation[i][0];
				int c = rotation[i][1];
				int s = rotation[i][2];
				int n = (2*s+1) / 2; // 회전해야하는 테두리 개수
				
				// 바깥쪽부터 안쪽까지 사각형 회전
				for(int j=0; j<n; j++) {
					rotate(r-s-1+j, r+s-j, c-s-1+j, c+s-j);
				}
			}
			
			// 각 행의 합 구하기 -> 최소값 찾기
			for(int i=0; i<N; i++) {
				int sum = 0;
				for(int j=0; j<M; j++) {
					sum += mapCopy[i][j];
				}
				min = Math.min(min, sum);
			}
			return;
		}
		
		// 연산의 순열 만들기
		for(int i=0; i<K; i++) {
			if(isSelected[i]) continue;
			
			rotation[cnt] = op[i];
			isSelected[i] = true;
			
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	// 배열을 돌리는 함수
	static void rotate (int rowStart, int rowEnd, int colStart, int colEnd) {
		// rowStart : 시작행 rowEnd: 마지막행 colStart: 시작열 colEnd: 마지막열
		int pre = mapCopy[rowStart][colStart]; // 바꿔야하는 위치의 그 전에 있었던 수
		int i = rowStart;
		int j = colStart+1;
		
		//맨 윗쪽 행 돌리기
		while(j < colEnd) {
			int temp = mapCopy[i][j];
			mapCopy[i][j] = pre;
			pre = temp;
			j++;
		}
		//맨 오른쪽 열 돌리기
		i++;
		j--;
		while(i < rowEnd) {
			int temp = mapCopy[i][j];
			mapCopy[i][j] = pre;
			pre = temp;
			i++;
		}
		//맨 아랫쪽 행 돌리기
		i--;
		j--;
		while(j >= colStart) {
			int temp = mapCopy[i][j];
			mapCopy[i][j] = pre;
			pre = temp;
			j--;
		}
		//맨 왼쪽 열 돌리기
		j++;
		i--;
		while(i >= rowStart) {
			int temp = mapCopy[i][j];
			mapCopy[i][j] = pre;
			pre = temp;
			i--;
		}
	}

}
