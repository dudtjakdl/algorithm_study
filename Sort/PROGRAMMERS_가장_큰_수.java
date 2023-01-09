package Sort;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42746
 * 걸린 시간 : 40분
 */
class PROGRAMMERS_가장_큰_수 {
    public String solution(int[] numbers) {
        int N = numbers.length; // 정수의 개수

        // int 배열 string 배열로 변환
        String[] strArray = Arrays.stream(numbers)
                .mapToObj(String::valueOf).toArray(String[]::new);

        // 두 문자열을 합해서 내림차순 정렬
        Arrays.sort(strArray, (o1, o2) -> {
            String a = o1 + o2;
            String b = o2 + o1;

            return b.compareTo(a);
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(strArray[i]);
        }

        int i = 0;

        // 0으로 시작하지 않는 지점 찾기
        for (i = 0; i < sb.length() - 1; i++) {
            if (sb.charAt(i) != '0') break;
        }

        return sb.substring(i);
    }
}
