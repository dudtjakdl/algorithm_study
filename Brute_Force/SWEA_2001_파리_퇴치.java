package Brute_Force;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq
 * 걸린 시간 : 20분
 * 리뷰 : 문제를 보자마자 완전 탐색으로 풀어야겠다고 생각했다.
 * 파리채크기만큼 2차원 배열을 탐색하는 경우의 수가 행 (N-M-1)번, 열 (N-M-1)번 이라는걸 바로 생각해낼 수 있었다.
 * 예전에 비슷한 문제를 풀어봤던게 도움이 됐다.
 */
public class SWEA_2001_파리_퇴치 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테스트 케이스 개수
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = sc.nextInt(); // 배열 행,열 크기
			int M = sc.nextInt(); // 파리채 크기
			int[][] flies = new int[N][N]; // 파리 수가 저장되는 배열
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					flies[i][j] = sc.nextInt();
				}
			}
			
			List<Integer> die = new ArrayList<>(); // 죽은 파리의 개수 저장하는 배열
			
			for(int i=0; i<N-M+1; i++) {
				for(int j=0; j<N-M+1; j++) {
					int sum = 0; // 죽은 파리의 수 저장하는 변수
					
					// 파리채 크기만큼 탐색
					for(int r=0; r<M; r++) {
						for(int c=0; c<M; c++) {
							sum += flies[i+r][j+c];
						}
					}
					die.add(sum);
				}
			}
			Collections.sort(die); // die 리스트 정렬 (최댓값 찾기 위해)
			System.out.printf("#%d %d\n", test_case, die.get(die.size()-1));
		}
	}
}
