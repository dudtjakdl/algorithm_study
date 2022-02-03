package Recursion;

import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemSubmitHistory.do?contestProbId=AV5PobmqAPoDFAUq
 * 걸린 시간 : 60분
 * 리뷰 : 푸는 방법이 여러가지일 것 같은데, 
 * 맨 바깥쪽 행, 열에 숫자를 채우면 그 안쪽도 똑같은 방식으로 숫자가 채워지는 로직이기에 재귀를 사용하였다.
 * 처음에는 N의 크기가 홀수, 짝수일 때마다 재귀의 기저 조건이 달라서 홀수, 짝수를 나눠서 재귀함수를 구현했는데,
 * 어차피 같은 함수에 기저 조건을 if-else로 나누면 나머지 유도파트의 코드는 똑같기에 합쳐서 구현하였다.
*/
public class SWEA_1954_달팽이_숫자 {
	public static int[][] snail;
	public static int cnt = 1; // 달팽이에 입력할 숫자
	
	public static void recursion(int start, int n) {
		// i: 숫자 입력을 시작할 행과 열 index
		// n: 숫자를 입력할 행의 최대 길이
		if(n==1) { // n이 1이면 시작 지점 칸 하나만 입력하고 종료
			snail[start][start] = cnt;
			return;
		}
		else if(n==0) { // n이 0이면 숫자 입력 안하고 종료
			return;
		}
		
		// 맨 윗쪽 행 숫자 채우기
		int i = start;
		for(int c=0; c<n; c++) {
			snail[i][start+c] = cnt++;
		}
		
		// 맨 오른쪽 열 숫자 채우기
		int j=start+(n-1);
		for(int r=1; r<n; r++) {
			snail[start+r][j] = cnt++;
		}
		
		// 맨 아래쪽 행 숫자 채우기
		i = start+(n-1);
		for(int c=start+(n-2); c>=start; c--) {
			snail[i][c] = cnt++;
		}
		
		// 맨 왼쪽 열 숫자 채우기
		j=start;
		for(int r=start+(n-2); r>start; r--) {
			snail[r][j] = cnt++;
		}
		
		recursion(start+1, n-2);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테스트 케이스 개수
		
		for(int test_case=1; test_case<=T; test_case++){
			int N = sc.nextInt(); // 달팽이의 크기
			snail = new int[N][N];
			cnt = 1;
			
			recursion(0, N);
			
			System.out.println("#"+test_case);
			// 달팽이 배열 출력
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(snail[i][j]+" ");
				}
				System.out.println();
			}
		}
		
	}

}
