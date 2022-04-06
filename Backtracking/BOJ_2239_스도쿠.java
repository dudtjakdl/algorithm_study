package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2239
 * 걸린 시간 : 60분
 * 리뷰 : 빈칸에 1부터 9까지 숫자를 전부 하나씩 넣어보고 가능한지 체크를 하는 방법과,
 * 숫자를 넣어보기 전에 가능한 숫자를 미리 체크해서 그 숫자를 넣는 방법으로 푸는 방법이 있다.
 * 나는 2번째 방법으로 숫자를 넣기 전에 가능한 숫자를 추려내서 경우의 수를 미리 사전에 가지치기하는 방법으로 풀었다.
 */
public class BOJ_2239_스도쿠 {
	static int[][] map;
	static int total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9]; // 스도쿠 보드판 배열
		total = 0; // 숫자를 채워야할 빈칸 개수
		
		// 스도쿠 보드판 숫자 입력
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j)-'0';
				if (map[i][j] == 0) total++; // 빈칸 개수 세기
			}
		}
		
		int startRow = 0; // 입력 시작할 빈칸 행 좌표
		int startCol = 0; // 입력 시작할 빈칸 열 좌표
		
		// 스도쿠 입력 시작할 시작위치 찾기
		outer:for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == 0) {
					startRow = i;
					startCol = j;
					break outer;
				}
			}
		}
		
		dfs(startRow, startCol, 0);
	}

	/**
	 * @param curRow : 현재 위치한 행 좌표
	 * @param curCol : 현재 위치한 열 좌표
	 * @param cnt : 직전까지 총 몇개의 빈칸을 채웠는지
	 */
	private static void dfs(int curRow, int curCol, int cnt) {
		if (cnt == total) {
			// 모든 빈칸을 다 채웠으면 스도쿠 출력하고 프로그램 종료
			
			// 결과 출력
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0);
		}
		
		boolean[] visited = new boolean[10]; // 1부터 9까지 숫자 중 이미 채워져 있는 숫자를 표시할 배열
		
		// 현재 행 중에서 이미 채워져 있는 숫자 체크
		for (int i = 0; i < 9; i++) {
			if (map[curRow][i] != 0) visited[map[curRow][i]] = true;
		}
		
		// 현재 열 중에서 이미 채워져 있는 숫자 체크
		for (int i = 0; i < 9; i++) {
			if (map[i][curCol] != 0) visited[map[i][curCol]] = true;
		}
		
		int row = (curRow/3)*3;
		int col = (curCol/3)*3;
		
		// 현재 3x3 박스에서 이미 채워져 있는 숫자 체크
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[row+i][col+j] != 0) visited[map[row+i][col+j]] = true;
			}
		}
		
		int nextRow = 0; // 다음으로 입력할 빈칸 행 좌표
		int nextCol = 0; // 다음으로 입력할 빈칸 열 좌표
		
		// 다음으로 숫자 넣을 칸 위치 찾기
		outer:for (int i = curRow; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == curRow && j == curCol) continue;
				if (map[i][j] == 0) {
					nextRow = i;
					nextCol = j;
					break outer;
				}
			}
		}
		
		// 가능한 숫자 하나씩 다 넣어보기
		for (int i = 1; i <= 9; i++) {
			if(!visited[i]) {
				map[curRow][curCol] = i;
				dfs(nextRow, nextCol, cnt+1);
				map[curRow][curCol] = 0;
			}
		}
		
	}

}
