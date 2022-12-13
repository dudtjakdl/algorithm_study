package Queue;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12906
 * 걸린 시간 : 10분
 */
public class PROGRAMMERS_같은_숫자는_싫어 {
    public int[] solution(int []arr) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int val : arr) {
            queue.offer(val);
        }

        int target = queue.poll();
        answer.add(target);

        while(!queue.isEmpty()) {
            int num = queue.poll();
            if (target != num) {
                target = num;
                answer.add(target);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
