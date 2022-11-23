package Hash;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42577
 * 걸린 시간 : 20분
 */
class PROGRAMMERS_전화번호_목록 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        int N = phone_book.length;
        Map<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            hashMap.put(phone_book[i], 1);
        }

        for (int i = 0; i < N; i++) {
            String phone = phone_book[i];
            int phoneLen = phone.length();

            for (int j = 0; j < phoneLen; j++) {
                if (hashMap.containsKey(phone.substring(0, j))) {
                    answer = false;
                    break;
                }
            }

        }

        return answer;
    }
}
