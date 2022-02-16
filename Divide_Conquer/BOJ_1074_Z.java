package Divide_Conquer;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1074
 * 걸린 시간 : 120분
*/
public class BOJ_1074_Z {
	static int r, c;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 2의 N제곱에서 N
		r = sc.nextInt(); // 방문 순서 찾아야하는 행
		c = sc.nextInt(); // 방문 순서 찾아야하는 열
		int n = (int) Math.pow(2, N); // 배열의 가로,세로 크기 (2의 N제곱)
		
		divide(n, 0, 0, 0); // 분할 시작
	}

	/**
	 * @param n : 분할된 배열의 가로,세로 크기
	 * @param startRow : 분할된 배열의 첫번째 값의 행
	 * @param startCol : 분할된 배열의 첫번째 값의 열
	 * @param startNum : 분할된 배열의 첫번째 값의 방문순서
	 */
	private static void divide(int n, int startRow, int startCol, int startNum) {
		if(n == 1) { // 배열 크기가 1이면 한칸을 의미하니 그 칸의 방문순서 출력
			System.out.println(startNum);
			return;
		}
		
		int centerRow = startRow + n/2; // 분할된 배열의 행의 중앙 경계값 
		int centerCol = startCol + n/2; // 분할된 배열의 열의 중앙 경계값 
		int size = n*n; // 분할된 배열의 전체 크기
		int quarter = size/4; // 배열의 크기를 4로 나눈 값
		
		// 탐색하는 r과 c의 범위에 포함되는 칸으로 분할 시작
		if(r < centerRow && c < centerCol) {
			divide(n/2, startRow, startCol, startNum); // 왼쪽 위칸
		}
		else if(r < centerRow && c >= centerCol) {
			divide(n/2, startRow, startCol+n/2, startNum+quarter); // 오른쪽 위칸
		}
		else if(r >= centerRow && c < centerCol) {
			divide(n/2, startRow+n/2, startCol, startNum+quarter*2); // 왼쪽 아래칸
		}
		else if(r >= centerRow && c >= centerCol) {
			divide(n/2, startRow+n/2, startCol+n/2, startNum+quarter*3); // 오른쪽 아래칸
		}
		
	}

}
