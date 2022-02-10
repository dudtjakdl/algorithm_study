package Implementation;

import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/2563
 * 걸린 시간 : 15분
*/
public class BOJ_2563_색종이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 색종이 수
		int[][] map = new int[100][100]; // 가로 세로 크기가 100인 도화지 배열
		int res = 0; // 결과값
		
		// 색종이 수만큼 실행
		for(int n=0; n<N; n++) {
			int x = sc.nextInt(); // 색종이의 x 좌표
			int y = sc.nextInt(); // 색종이의 y 좌표
			
			// 색종이 영역만큼 map의 값을 1로 칠하기
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					map[y+i][x+j] = 1;
				}
			}						
		}
		
		// 전체 도화지에서 1로 색칠된 영역 수 구하기
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] == 1) {
					res++;
				}
			}
		}
		
		// 결과 출력
		System.out.println(res);
	}

}
