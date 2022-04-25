package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/*
 * 문제 출처 : 백준
 * 문제 링크 : https://www.acmicpc.net/problem/1755
 * 걸린 시간 : 30분
*/
public class BOJ_1755_숫자놀이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); // M 입력
		int N = sc.nextInt(); // N 입력
		
		// 0부터 9까지의 숫자를 영문으로 저장한 배열
		String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		Map<String, Integer> map = new HashMap<>(); // <영어, 숫자> 의 형태로 매핑한 map 자료구조
		List<String> numToEng = new ArrayList<>(); // M이상 N이하의 숫자를 영어로 변환한 값을 넣을 리스트
		
		// M이상 N이하의 숫자만큼 실행
		for (int i = M; i <= N; i++) {
			// i가 일의자리 수일 경우
			if (i / 10 == 0) {
				map.put(nums[i], i); // <변환한 영어, 해당 숫자>로 map에 넣기
				numToEng.add(nums[i]); // 변한한 영어를 리스트에 넣기
			}
			// i가 십의자리 수일 경우
			else if (i / 10 != 0) {
				String temp = ""; // 변환한 영어를 저장할 임시 문자열
				temp += nums[i/10] + " "; // 십의자리수를 영어로 변환하여 temp에 공백과 함께 저장
				temp += nums[i%10]; // 일의자리수를 영어로 변환하여 temp에 붙여넣기
				map.put(temp, i); // <변환한 영어, 해당 숫자>로 map에 넣기
				numToEng.add(temp); // 변한한 영어를 리스트에 넣기
			}
		}
		
		Collections.sort(numToEng); // numToEng를 오름차순 정렬 -> 알파벳 사전 순으로 정렬됨
		
		StringBuilder sb = new StringBuilder();
		
		// 출력값 StringBuilder에 추가
		for (int i = 0; i < numToEng.size(); i++) {
			// 사전 순으로 정렬된 영어에 해당하는 숫자를 map에서 가져와서 sb에 넣기
			sb.append(map.get(numToEng.get(i))+ " ");
			if (i % 10 == 9) sb.append("\n"); // 한 줄을 넘어갈 경우 엔터 공백 추가
		}
		
		System.out.println(sb.toString()); // 결과 출력
	}

}
