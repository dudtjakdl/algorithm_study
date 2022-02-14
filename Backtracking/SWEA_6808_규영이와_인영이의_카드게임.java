package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0
 * 걸린 시간 : 20분
 * 리뷰 : 인영이가 카드를 내는 경우의 수를 순열로 구해줘서 점수를 계산하면 되는 문제이다.
 * 풀이에서 한가지 아쉬운 점은, 인영이가 카드를 내는 경우를 먼저 구하고 그 후 한꺼번에 점수와 승패를 계산하였는데,
 * 순열을 구하는 과정에서 인영이의 카드를 하나씩 뽑을때마다 점수를 계산하고 마지막에만 승패를 계산하였으면 시간적인 면에서 더 빨랐을 것 같다는 아쉬움이 있다.
 */
public class SWEA_6808_규영이와_인영이의_카드게임 {
	
	static boolean[] isSelected; // 이미 뽑힌 카드를 체크하는 배열
	static int[] card1 = new int[9]; // 규영이의 카드
	static int[] card2 = new int[9]; // 인영이의 카드
	static int win = 0; // 규영이가 승리한 횟수
	static int lose = 0; // 규영이가 진 횟수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringTokenizer st;
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			isSelected = new boolean[18];
			
			for(int i=0; i<9; i++) {
				int cardNum = Integer.parseInt(st.nextToken());
				card1[i] = cardNum; // 규영이의 카드 입력
				isSelected[cardNum-1] = true; // 규영이가 가진 카드는 인영이가 뽑지 않도록 selet 체크
			}
			
			// 승패 횟수 0으로 초기화한후 인영이 카드 뽑기 시작
			win = 0;
			lose = 0;
			permutation(0);
			
			System.out.println("#"+test_case+" "+win+" "+lose);
		}
	}
	
	// 인영이의 카드를 뽑는 경우의 수 구하기 (순열)
	static void permutation(int cnt) { // cnt: 직전까지 뽑은 카드의 수
		if(cnt == 9) { // 인영의 카드를 9개 전부 뽑았으면 점수 계산 시작
			calScore();
			return;
		}
		
		for(int i=1; i<=18; i++) {
			if(isSelected[i-1]) continue;
			
			card2[cnt] = i;
			isSelected[i-1] = true;
			
			permutation(cnt+1);
			isSelected[i-1] = false;
		}
		
	}
	
	// 규영이와 인영이의 총점 계산, 규영이의 승패 계산
	static void calScore() {
		int player1 = 0; // 규영이의 총점
		int player2 = 0; // 인영이의 총점
		
		// 카드 게임 시작
		for(int i=0; i<9; i++) {
			if(card1[i] > card2[i]) { // 규영이의 카드가 크면 규영이 점수 얻기
				player1 += card1[i] + card2[i];
			}
			else { // 인영이의 카드가 크면 인영이 점수 얻기
				player2 += card1[i] + card2[i];
			}
		}
		
		// 규영이와 인영이의 총점 비교후 승패 체크
		if(player1 > player2) win++;
		else if (player1 < player2) lose++;
	}

}
