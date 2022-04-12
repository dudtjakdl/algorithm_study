package Math;

import java.io.IOException;
import java.util.Scanner;

public class BOJ_1019_책_페이지 {
	static int[] cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int start = 1; // 첫 페이지
		int end = sc.nextInt(); // 마지막 페이지
		cnt = new int[10]; // 0부터 9까지 총 나온 횟수를 저장할 배열
		int digit = 1; // 현재 자릿수
	
		while (start <= end) {
			// start의 일의 자리수가 0이 될때까지 1 증가 시키기
			while (start % 10 != 0 && start <= end) {
				// 1 증가시키기 전에 숫자가 총 몇번 나왔는지 카운트
				calc(start++, digit);
			}
				
			if (start > end) break;
				
			// end의 일의 자리수가 9가 될때까지 1 감소 시키기
			while (end % 10 != 9 && start <= end) {
				// 1 감소시키기 전에 숫자가 총 몇번 나왔는지 카운트
				calc(end--, digit);
			}
				
			// end와 start 사이에서 0부터 9까지 숫자 사이클이 몇번 나오는지 계산
			long diff = (end / 10) - (start / 10) + 1;
				
			// 0부터 9까지 숫자 나온 횟수 카운트 증가
			for (int i = 0; i <= 9; i++) {
				cnt[i] += diff*digit;
			}
				
			start /= 10;
 			end /= 10;
			digit *= 10;
		}
	
		// 0부터 9까지 몇 번 나왔는지 출력
		for (int i = 0; i <= 9; i++) {
			System.out.print(cnt[i]+" ");
		}

	}
	
	private static void calc(int n, int digit) {
		while (n > 0) {
			cnt[n%10] += digit;
			n /= 10;
		}
	}

}
