package Sort;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42748
 * 걸린 시간 : 20분
 */
class PROGRAMMERS_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int N = commands.length;
        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(arr);
            answer[i] = arr[commands[i][2]-1];
        }

        return answer;
    }
}