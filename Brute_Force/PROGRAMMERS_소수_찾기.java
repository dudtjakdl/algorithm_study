package Brute_Force;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42839
 * 걸린 시간 : 60분
 */
class PROGRAMMERS_소수_찾기 {
    static int N, M, answer;
    static int[] nums, pick;
    static boolean[] isSelected;
    static Map<Integer, Integer> primeCnt;

    public int solution(String numbers) {
        answer = 0;
        N = numbers.length(); // 숫자의 개수
        nums = new int[N];
        primeCnt = new HashMap<>();

        for (int i = 0; i < N; i++) {
            nums[i] = numbers.charAt(i) - '0';
        }

        for (int i = 1; i <= N; i++) {
            M = i;
            pick = new int[M];
            isSelected = new boolean[N];
            permutation(0);
        }

        answer = primeCnt.size();

        return answer;
    }

    private static void permutation(int cnt) {
        if (cnt == M) {
            String num = "";

            for (int i = 0; i < M; i++) {
                num += pick[i];
            }

            isPrimeCheck(Integer.parseInt(num));

            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;

            isSelected[i] = true;
            pick[cnt] = nums[i];
            permutation(cnt+1);
            isSelected[i] = false;
        }

    }

    private static boolean isPrimeCheck(int num) { // 소수인지 아닌지 체크하는 함수
        if (num == 0 || num == 1) return false;

        int i = 1;

        while (++i < num) {
            if (num % i == 0) return false;
        }

        primeCnt.put(num, 1);

        return true;
    }

}