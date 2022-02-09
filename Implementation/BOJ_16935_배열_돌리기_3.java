package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/16935
 * 걸린 시간 : 90분
 * 리뷰 : 좌우, 상하 반전을 짝수 번 수행하면 원래 형태로 돌아오고, 90도 회전과 그룹 복사도 4번 수행하면 원래 형태이니
 * 굳이 여러번 수행하지 않도록 수행 횟수를 조절해 주었으면 더 최적화 된 코드였을 것 같다.
 * 또한 5, 6번 연산인 그룹 복사를 모든 경우의 수 4개를 각각 구현하였는데,
 * 이를 깔끔하게 하나의 반복문에서 같이 수행하도록 해줬으면 더 좋았을 것 같다.
*/
public class BOJ_16935_배열_돌리기_3 {
	static int N, M, R;
	static int[] op;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행의 크기
		M = Integer.parseInt(st.nextToken()); // 열의 크기
		R = Integer.parseInt(st.nextToken()); // 연산의 개수
		op = new int[R]; // 연산 번호를 저장하는 배열
		map = new int[N][M]; // N×M 배열
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<R; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		// 연산 수행
		for(int n: op) {
			switch(n) {
			case 1:
				op1();
				break;
			case 2:
				op2();
				break;
			case 3:
				op3();
				break;
			case 4:
				op4();
				break;
			case 5:
				op5();
				break;
			case 6:
				op6();
				break;
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
	
	// 1번 연산 - 상하 반전
	static void op1() {
		for(int i=0; i<N/2; i++) {
			int[] temp = map[i].clone(); // 첫번째 행 복사
			map[i] = map[N-i-1].clone(); // 첫번째 행 바꾸기
			map[N-i-1] = temp; // 마지막 행 바꾸기
		}
	}
	
	// 2번 연산 - 좌우 반전
	static void op2() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				int temp = map[i][j];
				map[i][j] = map[i][M-j-1];
				map[i][M-j-1] = temp;
			}
		}
	}
	
	// 3번 연산 - 오른쪽으로 90도 회전
	static void op3() {
		int[][] result = new int[M][N]; // 90도 회전한 배열 -> MxN 배열이 됨
		int[] temp; // 각 행을 임시 저장할 배열
		
		for(int i=0; i<N; i++) {
			temp = map[i]; // 행 복사
			for(int j=0; j<M; j++) {
				result[j][N-i-1] = temp[j];
			}
		}
		map = result.clone();
		
		// NxM -> MxN 이 됐으니 N과 M 서로 swap
		int temp2 = N;
		N = M;
		M = temp2;
	}
	
	// 4번 연산 - 왼쪽으로 90도 회전
	static void op4() {
		int[][] result = new int[M][N]; // 90도 회전한 배열 -> MxN 배열이 됨
		int[] temp; // 각 행을 임시 저장할 배열
		
		for(int i=0; i<N; i++) {
			temp = map[i]; // 행 복사
			for(int j=0; j<M; j++) {
				result[M-j-1][i] = temp[j];
			}
		}
		map = result.clone();
		
		// NxM -> MxN 이 됐으니 N과 M 서로 swap
		int temp2 = N;
		N = M;
		M = temp2;
	}
	
	// 5번 연산 - 그룹을 4개로 나눈뒤 각각을 바로 다음 그룹으로 복사
	static void op5() {
		int[][] copy = new int[N][M]; // 원본 배열을 복사한 배열
		int r; // 시작 행
		int c; // 시작 열
		
		// 원본 배열 복사
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		// 1번 그룹 -> 2번 그룹으로 복사
		r = 0;
		c = M/2;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i][c+j-(M/2)];
			}
		}
		
		// 2번 그룹 -> 3번 그룹으로 복사
		r = N/2;
		c = M/2;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i-(N/2)][c+j];
			}
		}
		
		// 3번 그룹 -> 4번 그룹으로 복사
		r = N/2;
		c = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i][c+j+(M/2)];
			}
		}
		
		// 4번 그룹 -> 1번 그룹으로 복사
		r = 0;
		c = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i+(N/2)][c+j];
			}
		}
	}
	
	// 6번 연산 - 그룹을 4개로 나눈뒤 각각을 바로 이전 그룹으로 복사
	static void op6() {
		int[][] copy = new int[N][M]; // 원본 배열을 복사한 배열
		int r; // 시작 행
		int c; // 시작 열
		
		// 원본 배열 복사
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		// 1번 그룹 -> 4번 그룹으로 복사
		r = N/2;
		c = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i-(N/2)][c+j];
			}
		}
		
		// 4번 그룹 -> 3번 그룹으로 복사
		r = N/2;
		c = M/2;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i][c+j-(M/2)];
			}
		}
		
		// 3번 그룹 -> 2번 그룹으로 복사
		r = 0;
		c = M/2;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i+(N/2)][c+j];
			}
		}
		
		// 2번 그룹 -> 1번 그룹으로 복사
		r = 0;
		c = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				map[r+i][c+j] = copy[r+i][c+j+(M/2)];
			}
		}
	}
}
