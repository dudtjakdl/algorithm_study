package Etc;

import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN
 * 걸린 시간 : 20분
 */
public class SWEA_1289_원재의_메모리_복구하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			String s = sc.next();
			char[] memory = s.toCharArray();
			char[] init = new char[memory.length];
			int cnt = 0;

			for (int i = 0; i < init.length; i++) {
				init[i] = '0';
			}

			for (int i = 0; i < memory.length; i++) {
				if (memory[i] != init[i]) {
					char tmp = memory[i];
					for (int j = i; j < memory.length; j++) {
						init[j] = tmp;
					}
					cnt++;
				}
			}
			System.out.printf("#%d %d\n", t + 1, cnt);
		}

	}

}
