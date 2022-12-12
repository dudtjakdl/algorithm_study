package Heap;
import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42627
 * 걸린 시간 : 30분
 */
class PROGRAMMERS_디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;
        int sum = 0;
        int time = 0; // 현재 시간 (ms)
        PriorityQueue<int[]> pQueue1 = new PriorityQueue<>(
                (int[] o1, int[] o2) -> {
                    if (o1[0] == o2[0]) return o1[1] - o2[1];
                    else return o1[0] - o2[0];
                }
        );
        PriorityQueue<int[]> pQueue2 = new PriorityQueue<>(
                (int[] o1, int[] o2) -> o1[1] - o2[1]);

        for (int [] job : jobs) {
            pQueue1.offer(job);
        }


        while (!pQueue1.isEmpty()) {
            int size = pQueue1.size();
            pQueue2.clear();

            while (--size >= 0) { // 요청 들어온 작업 찾기
                int[] job = pQueue1.poll();

                if (job[0] <= time) {
                    pQueue2.offer(job);
                }
                else {
                    pQueue1.offer(job);
                }
            }

            if (pQueue2.isEmpty()) {
                int[] job = pQueue1.poll();

                time = job[0] + job[1];
                sum += job[1];
            }
            else {
                int[] job = pQueue2.poll();

                time += job[1]; // 현재 시간 증가
                sum += time - job[0]; // 요청부터 종료까지 거린 시간 누적

                while(!pQueue2.isEmpty()) {
                    pQueue1.offer(pQueue2.poll());
                }
            }
        }

        answer = sum / jobs.length;

        return answer;
    }
}
