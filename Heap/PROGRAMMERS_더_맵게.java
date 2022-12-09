package Heap;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42626
 * 걸린 시간 : 20분
 */
class PROGRAMMERS_더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        int tSize = 0; // 고려해야 할(스코빌 지수를 높여야 할) 음식 수
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        for (int val : scoville) {
            if (val < K) tSize += 1; // 스코빌 지수 K 미만일 경우
            pQueue.offer(val);
        }

        while (tSize > 0 && pQueue.size() > 1) {
            int food1 = pQueue.poll(); // 가장 맵지 않은 음식의 스코빌 지수
            int food2 = pQueue.poll(); // 두 번째로 맵지 않은 음식의 스코빌 지수
            int newFood = food1 + food2 * 2; // 섞은 새로운 음식

            answer += 1; // 섞은 횟수 1 증가
            tSize -= 1; // 고려해야 할 음식 수 1 감소
            if (newFood >= K) tSize -= 1; // 새로운 음식이 스코빌 지수 만족했을 경우

            pQueue.offer(newFood);
        }

        // 모든 음식을 K 이상으로 만들 수 없는지 여부 체크
        if (pQueue.size() == 1 && pQueue.peek() < K) answer = -1;

        return answer;
    }
}
