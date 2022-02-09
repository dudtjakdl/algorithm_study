package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/16926
 * 걸린 시간 : 100분
 * 리뷰 : 로테이션을 위한 로직은 잘 생각해서 차근차근 구현을 했다. 예제도 전부 맞지만
 * 계속 틀렸다고 나오길래 열심히 반례를 찾아보았는데, 알고보니 로테이션을 수행해야할 사각형 개수(테두리 개수)를
 * 단순히 N/2로 구해서 생긴 문제였다. 문제의 제약사항을 잘 보면 min(N, M) mod 2 = 0 라는 조건이 있는데,
 * 이를 무시하고 무조건 N/2로 했던 것을 N과 M중 더 작은 변수를 이용하여 사각형 개수를 구했더니 해결됐다.
 * 쓸모없는 제약사항은 없다.. 앞으로 잘 살펴볼 것..!!
*/
public class BOJ_16926_배열_돌리기_1 {
	static int N, M, R;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행의 크기
		M = Integer.parseInt(st.nextToken()); // 열의 크기
		R = Integer.parseInt(st.nextToken()); // 회전 수
		map = new int[N][M]; // N×M 배열
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = Math.min(N, M)/2; // 회전해야하는 사각형 개수
		
		// 바깥쪽부터 안쪽까지 사각형 회전
		for(int i=0; i<cnt; i++) {
			for(int j=0; j<R; j++) { // R 만큼 회전
				rotate(i, N-i, i, M-i);
			}
		}
		
		// 결과 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void rotate (int rowStart, int rowEnd, int colStart, int colEnd) {
		// rowStart : 시작행 rowEnd: 마지막행 colStart: 시작열 colEnd: 마지막열
		int pre = map[rowStart][colStart]; // 바꿔야하는 위치의 그 전에 있었던 수
		int i = rowStart+1;
		int j = colStart;
		//맨 왼쪽 행 돌리기
		while(i < rowEnd) {
			int temp = map[i][j];
			map[i][j] = pre;
			pre = temp;
			i++;
		}
		//맨 아랫쪽 열 돌리기
		i--;
		j++;
		while(j < colEnd) {
			int temp = map[i][j];
			map[i][j] = pre;
			pre = temp;
			j++;
		}
		//맨 오른쪽 행 돌리기
		j--;
		i--;
		while(i >= rowStart) {
			int temp = map[i][j];
			map[i][j] = pre;
			pre = temp;
			i--;
		}
		//맨 윗쪽 열 돌리기
		i++;
		j--;
		while(j >= colStart) {
			int temp = map[i][j];
			map[i][j] = pre;
			pre = temp;
			j--;
		}
	}

}
