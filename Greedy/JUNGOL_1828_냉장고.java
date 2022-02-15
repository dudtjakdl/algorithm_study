package Greedy;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 문제 출처 : 정올
 * 문제 링크 : http://jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=99&sfl=wr_hit&stx=1828
 * 걸린 시간 : 25분
 */
public class JUNGOL_1828_냉장고 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 화학물질의 수
		int[][] materials = new int[N][2]; // 화학물질의 최저,최고 보관온도를 저장할 배열
		int cnt = 1; // 최소로 필요한 냉장고의 대수 (결과값)
		
		for(int i=0; i<N; i++) {
			materials[i][0] = sc.nextInt(); // 최저보관온도
			materials[i][1] = sc.nextInt(); // 최고보관온도
		}
		
		// 최고보관온도를 기준으로 오름차순 정렬
		// 최고보관온도가 같을 경우 최저보관온도 오름차순 정렬
		Arrays.sort(materials, (o1, o2) -> {
			return (o1[1] == o2[1]) ? o1[0]-o2[0] : o1[1]-o2[1];
		});
		
		int temp = materials[0][1]; // 각 냉장고의 온도 -> 첫번째 화학물질의 최고보관온도로 초기화
		
		for (int i = 1; i < materials.length; i++) {
			if(temp >= materials[i][0] && temp <= materials[i][1]) {
				// 각 화학물질의 최저,최고 보관온도가 냉장고의 온도 범위내에 있으면 pass 
				continue;
			}
			else {
				// 냉장고의 온도 범위 밖이면,
				// 현재 주목하고 있는 화학물질의 최고 보관온도로 냉장고 온도 바꾸기
				temp = materials[i][1];
				cnt++; // 냉장고 대수 1 증가
			}
		}
		
		System.out.println(cnt); // 결과 출력
	}

}
