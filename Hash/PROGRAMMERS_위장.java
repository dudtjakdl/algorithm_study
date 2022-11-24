package Hash;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42578
 * 걸린 시간 : 15분
 */
class PROGRAMMERS_위장 {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> hashMap = new HashMap<>();

        for (String[] item : clothes) {
            int num = hashMap.getOrDefault(item[1], 0);
            hashMap.put(item[1], num + 1);
        }

        for (Integer value : hashMap.values()) {
            answer *= (value + 1);
        }

        answer -= 1;

        return answer;
    }
}