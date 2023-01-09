package Greedy;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42862
 * 걸린 시간 : 30분
 */
class PROGRAMMERS_체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] counts = new int[n+2]; // 각 학생들의 체육복 수 배열

        Arrays.fill(counts, 1); // 배열값 1로 초기화

        // 도난당한 학생 체육복 -1
        for (int i = 0; i < lost.length; i++) {
            counts[lost[i]] -= 1;
        }

        // 여벌 옷 있는 학생 체육복 +1
        for (int i = 0; i < reserve.length; i++) {
            counts[reserve[i]] += 1;
        }

        for (int i = 1; i <= n; i++) {
            if (counts[i] == 0) { // 체육복이 없는 학생일 경우
                if (counts[i-1] == 2) { // 왼쪽 학생 여벌 있는 경우 빌리기
                    counts[i-1] = 1;
                    counts[i] = 1;
                }
                else if (counts[i+1] == 2) { // 오른쪽 학생 여벌 있는 경우 빌리기
                    counts[i+1] = 1;
                    counts[i] = 1;
                }
            }
        }

        /**
         왼쪽 학생부터 여벌을 찾을 경우 1번째 학생부터 탐색을 시작해야 한다.
         오른쪽 학생부터 여벌을 찾을 경우 반대로 n번째 학생부터 탐색을 시작해야 한다.
         즉, 순서를 바꾼 아래의 경우도 가능
         */
        // for (int i = n; i >= 1; i--) {
        //     if (counts[i] == 0) {
        //         if (counts[i-1] == 2) {
        //             counts[i-1] = 1;
        //             counts[i] = 1;
        //         }
        //         else if (counts[i+1] == 2) {
        //             counts[i+1] = 1;
        //             counts[i] = 1;
        //         }
        //     }
        // }

        // 체육복 없는 학생 빼기
        for (int i = 1; i <= n; i++) {
            if (counts[i] == 0) {
                answer -= 1;
            }
        }

        return answer;
    }
}
