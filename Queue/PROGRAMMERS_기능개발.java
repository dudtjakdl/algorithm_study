package Queue;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 걸린 시간 : 30분
 */
class PROGRAMMERS_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int val : progresses) { // 큐에 값 넣기
            queue.offer(val);
        }

        int tIdx = 0; // 타겟 인덱스
        int day = 0; // 지난 일수

        while (!queue.isEmpty()) { // 큐가 빌 때까지 반복
            int target = queue.poll() + speeds[tIdx] * day; // 타겟 기능의 진도율 계산
            day += Math.ceil((100 - target) / (speeds[tIdx] * 1.0)); // 타겟 기능이 진도율 100 이상이 되는 일 수 계산

            int nIdx = tIdx; // 다음 기능의 인덱스
            int cnt = 1; // 하루에 배포할 기능 수 카운트

            while (!queue.isEmpty()) {
                if (queue.peek() + day * speeds[++nIdx] >= 100) { // 다음 기능이 진도율 100 이상이 됐을 경우
                    // 큐에서 제거한 후 배포 수 카운트
                    queue.poll();
                    cnt += 1;
                }
                else break;
            }

            tIdx += cnt; // 다음 타겟 기능의 인덱스 구하기
            answer.add(cnt); // 하루에 배포할 기능 수 추가
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
