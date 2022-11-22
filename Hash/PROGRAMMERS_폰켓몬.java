package Hash;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/1845
 * 걸린 시간 : 10분
 */
class 폰켓몬 {
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length; // 총 포켓몬 수
        int n = N / 2; // 가져갈 수 있는 포켓몬 수
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], 1);
            }
        }

        if (hashMap.size() >= n) answer = n;
        else answer = hashMap.size();

        return answer;
    }
}