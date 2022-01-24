package Brute_Force;

import java.util.Scanner;

public class BOJ_2798_블랙잭 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //카드의 개수
		int M = sc.nextInt(); //카드 합
		
		int[] cards = new int[N];
		
		for(int i=0; i<N; i++) {
			cards[i] = sc.nextInt();
		}
		
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					int sum = cards[i]+cards[j]+cards[k];
					if(sum > max && sum <= M)
						max = sum;
				}
			}
		}
		System.out.println(max);
	}

}
