package Backtracking;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/9663
 * 걸린 시간 : 20분
 */
public class BOJ_9663_N_Queen {

	static int N, ans;
	static int[] col;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 체스판의 가로세로 크기
		col = new int[N+1]; // 각 행에 놓여진 체스의 열 번호를 저장하는 배열
		
		setQueen(1);
		
		System.out.println(ans);
	}

	public static void setQueen(int rowNo) { // rowNo : 퀸을 두어야하는 현재 행
		
		// 가지치기
		if(!isAvailable(rowNo-1)) return; // 직전까지의 상황이 유망하지 않다면 리턴
		
		// 기저조건 : 퀸을 모두 놓았다면
		if(rowNo > N) {
			ans++;
			return;
		}
		
		// 1열부터 - n열까지 퀸을 놓는 시도
		for (int i = 1; i <= N; i++) {
			col[rowNo] = i;
			setQueen(rowNo+1);
		}
	}
	
	public static boolean isAvailable(int rowNo) { // rowNo : 놓아진 마지막 퀸
		for (int i = 1; i < rowNo; i++) {
			// 같은 열과 대각선에 퀸이 놓아져있는지 체크
			if(col[rowNo] == col[i] || rowNo-i == Math.abs(col[rowNo]-col[i])) return false;
		}
		return true;
	}
}
