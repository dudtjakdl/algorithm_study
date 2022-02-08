package Etc;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/*
 * 문제 출처 : SWEA
 * 문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14w-rKAHACFAYD
 * 걸린 시간 : 20분
 */
public class SWEA_1228_암호문1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			int N = sc.nextInt(); // 원본 암호문의 길이
			List<String> str = new LinkedList<>(); // 암호문 저장하는 리스트
			
			for(int i=0; i<N; i++) {
				str.add(sc.next());
			}
			int M = sc.nextInt(); // 명령어의 개수
			
			for(int i=0; i<M; i++) {
				// 명령어 하나 시작
				if(sc.next().equals("I")) {
					int idx = sc.nextInt(); // 명령어 끼워넣을 위치
					int cnt = sc.nextInt(); // 끼워넣을 명령어 개수
					for(int j=0; j<cnt; j++) {
						str.add(idx+j, sc.next());
					}
				}
			}
			System.out.print("#" + test_case + " ");
			for(int i=0; i<10; i++) {
				System.out.print(str.get(i) + " ");
			}
			System.out.println();
		}
	
	}

}
