package Hash;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42576
 * 걸린 시간 : 20분
 */
class PROGRAMMERS_완주하지_못한_선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        // 완주한 사람 hashMap에 넣기
        for (int i = 0; i < completion.length; i++) {
            // 맵에 해당 key가 있으면 value를 가져오고 없으면 설정한 default value 반환
            int num = hashMap.getOrDefault(completion[i], 0);
            hashMap.put(completion[i], num + 1);
        }

        // 완주하지 못한 사람 찾기
        for (int i = 0; i < participant.length; i++) {
            int value = hashMap.getOrDefault(participant[i], 0);

            if (value == 0) {
                answer = participant[i];
                break;
            }
            else {
                hashMap.put(participant[i], value - 1);
            }
        }

        return answer;
    }
}