package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
 * 걸린 시간 : 60분
*/
public class SWEA_4013_특이한_자석 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int K = Integer.parseInt(br.readLine()); // 자석을 회전시키는 횟수
			int[][] magnet = new int[4][8]; // 자석 자성 정보를 저장할 배열
			
			// 4개의 자석 자성 정보 입력
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] turn; // 각 자석의 회전 정보 배열 (0: 회전 안함 1: 시계 방향 -1: 반시계 방향)
			
			// K번 회전시키기
			while(--K >= 0) {
				// 회전 정보 입력
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken()) - 1; // 시작 자석 번호 - 1 (인덱스는 0부터 시작이니)
				int dir = Integer.parseInt(st.nextToken()); // 회전 방향
				turn = new int[4]; // 회전 정보 배열 초기화
				turn[start] = dir; // 시작 자석 회전 정보 저장
				
				// 시작 자석을 중심으로 왼쪽에 있는 자석들 회전시킬지 고려하기
				for (int i = start; i > 0; i--) {
					// 서로 붙어 있는 날의 자성이 다를 경우
					if (magnet[i][6] != magnet[i-1][2]) {
						// 현재 자석의 반대 방향으로 회전 방향 저장
						turn[i-1] = (turn[i] == 1 ? -1 : 1);
					}
					// 서로 붙어 있는 날의 자성이 같을 경우 -> 회전 시키지 않기
					else if (magnet[i][6] == magnet[i-1][2]) {
						// 더 이전의 자석들도 연쇄적으로 회전되지 않으므로 반복문 나가기
						break;
					}
				}
				
				// 시작 자석을 중심으로 오른쪽에 있는 자석들 회전시킬지 고려하기
				for (int i = start; i < 3; i++) {
					// 서로 붙어 있는 날의 자성이 다를 경우
					if (magnet[i][2] != magnet[i+1][6]) {
						// 현재 자석의 반대 방향으로 회전 방향 저장
						turn[i+1] = (turn[i] == 1 ? -1 : 1);
					}
					// 서로 붙어 있는 날의 자성이 같을 경우 -> 회전 시키지 않기
					else if (magnet[i][2] == magnet[i+1][6]) {
						// 더 이전의 자석들도 연쇄적으로 회전되지 않으므로 반복문 나가기
						break;
					}
				}
				
				// 회전 정보에 맞춰서 자석들 회전시키기
				for (int i = 0; i < 4; i++) {
					// 회전 정보가 0이면 회전시키지 않기
					if (turn[i] == 0) continue;
					
					// 회전 정보가 1이면 시계방향으로 회전
					if (turn[i] == 1) {
						// 자석 배열 1칸씩 오른쪽으로 밀기
						int temp = magnet[i][7];
						for (int j = 7; j >= 1; j--) {
							magnet[i][j] = magnet[i][j-1];
						}
						magnet[i][0] = temp;
					}
					// 회전 정보가 -1이면 반시계방향으로 회전
					else if (turn[i] == -1) {
						// 자석 배열 1칸씩 왼쪽으로 밀기
						int temp = magnet[i][0];
						for (int j = 0; j <= 6; j++) {
							magnet[i][j] = magnet[i][j+1];
						}
						magnet[i][7] = temp;
					}
				}
			}
			
			int ans = 0; // 문제의 답
			
			// 점수 계산하기
			for (int i = 0; i < 4; i++) {
				// 빨간색 날이 N극이면 pass
				if (magnet[i][0] == 0) continue;
				
				// S극이면 2의 자석번호 제곱으로 점수 더하기
				ans += Math.pow(2, i);
			}
			
			// 결과 출력
			System.out.println("#"+test_case+" "+ans);
		}

	}

}
