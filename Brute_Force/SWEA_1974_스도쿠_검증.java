package Brute_Force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Psz16AYEDFAUq
 * 걸린 시간 : 20분
 * 리뷰 : 1부터 9까지 숫자가 있는지 검증하기 위해 9개의 숫자를 오름차순 정렬하여 1부터 시작해서 1씩 증가하는지 배열을 차례대로 탐색하는 방법을 사용했다.
 * 하지만 이 방법은 배열을 copy해야하기 때문에 메모리 사용량이 많은 것이 단점이다.
 * 다른 방법으로, set 자료구조를 이용하여 숫자를 넣고 size가 9인지 아닌지 확인하는 방법,
 * 또는 boolean 배열을 이용하여 숫자를 체크하면서 하는 방법을 사용하는 것도 좋았을 것 같다.
 */
public class SWEA_1974_스도쿠_검증 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		outer: for(int test_case=1; test_case<=T; test_case++) {
			int[][] map = new int[9][9]; // 스도쿠 퍼즐을 입력할 배열
			
			// 스도쿠 퍼즐 숫자 입력
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.print("#"+test_case+" "); // 테스트케이스 번호 출력
			
			// 한행씩 차례대로 검사
			for (int i = 0; i < 9; i++) {
				int[] temp = map[i].clone(); //한 행의 배열 복사
				Arrays.sort(temp); // 복사한 배열 오름차순 정렬
				
				// 행에 1부터 9까지 1씩 증가하면서 차례대로 숫자가 존재하는지 체크
				for (int j = 0; j < 9; j++) {
					if(temp[j] != j+1) { // 차례대로 숫자가 있지 않으면 겹치는 숫자가 있다는 뜻
						System.out.println(0); // 결과 출력
						continue outer; // 검증을 종료하고 다음 테스트케이스로 이동
					}
				}
			}
			
			// 한열씩 차례대로 검사
			for (int i = 0; i < 9; i++) {
				int[] temp = new int[9]; //한 열의 숫자를 저장할 배열
				
				// i열의 숫자를 0행부터 9행까지 순서대로 저장
				for (int j = 0; j < 9; j++) {
					temp[j] = map[j][i];
				}
				
				Arrays.sort(temp); // 배열 오름차순 정렬
				
				// 열에 1부터 9까지 1씩 증가하면서 차례대로 숫자가 존재하는지 체크
				for (int j = 0; j < 9; j++) {
					if(temp[j] != j+1) {
						System.out.println(0);
						continue outer;
					}
				}
			}
			
			// 3x3 박스씩 차례대로 검사
			for (int i = 0; i < 3; i++) {
				int[] temp = new int[9]; // 3x3 박스의 숫자를 저장할 배열
				
				for (int j = 0; j < 3; j++) {
					
					int idx = 0; // 숫자저장을 위한 인덱스
					int nr = 3*i; // 3x3 크기 박스의 시작 행 위치
					int nc = 3*j; // 3x3 크기 박스의 시작 열 위치
					
					// 3x3 박스에 적힌 숫자를 차례대로 입력
					for (int r = 0; r < 3; r++) {
						for (int c = 0; c < 3; c++) {
							temp[idx++] = map[nr+r][nc+c];
						}
					}
					
					Arrays.sort(temp); // 배열 오름차순 정렬
					
					// 3x3 박스에 1부터 9까지 1씩 증가하면서 차례대로 숫자가 존재하는지 체크
					for (int k = 0; k < 9; k++) {
						if(temp[k] != k+1) {
							System.out.println(0);
							continue outer;
						}
					}
					
				}
			}
			// 위의 검증을 다 통과하고 여기까지 왔으면 겹치는 숫자가 없다는 뜻
			System.out.println(1); // 결과 출력
		}

	}

}
