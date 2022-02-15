package Implementation;

import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PjMgaALgDFAUq
 * 걸린 시간 : 20분
*/
public class SWEA_1940_가랏_RC카 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테스트케이스 개수
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = sc.nextInt(); // 커맨드의 개수
			int speed = 0; // 현재 속도
			int sum = 0; // 총 이동한 거리
			
			// 커맨드 수만큼 이동 거리 계산
			for (int i = 0; i < N; i++) {
				int command = sc.nextInt(); // 커맨드
				int accel = 0; // 가속도
				switch(command) {
				case 0: // 현재 속도 유지
					break;
				case 1: // 가속
					accel = sc.nextInt();
					speed += accel; // 가속도 만큼 가속
					break;
				case 2: // 감속
					accel = sc.nextInt();
					if (accel > speed) speed = 0; // 가속도가 현재 속도보다 크면 현재 속도는 0
					else speed -= accel; // 가속도 만큼 감속
					break;
				}
				
				sum += speed; // 이동 거리 누적
			}
		
			System.out.println("#"+test_case+" "+sum); // 출력
		}
	}

}
