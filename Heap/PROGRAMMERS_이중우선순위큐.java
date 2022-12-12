package Heap;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42628
 * 걸린 시간 : 20분
 */
class PROGRAMMERS_이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // 최대힙
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // 최소힙

        for (int i = 0; i < operations.length; i++) {
            char op = operations[i].charAt(0); // 명령어 D, I
            int num = Integer.parseInt(operations[i].split(" ")[1]); // 숫자

            if (op == 'I') { // 숫자 삽입 명령
                maxHeap.offer(num);
                minHeap.offer(num);
            }
            else if (op == 'D' && !maxHeap.isEmpty() && !minHeap.isEmpty()) { // 숫자 삭제 명령
                if (num == 1) { // 최댓값 삭제 명령
                    int maxNum = maxHeap.poll();
                    minHeap.remove(maxNum);
                }
                else { // 최솟값 삭제 명령
                    int minNum = minHeap.poll();
                    maxHeap.remove(minNum);
                }
            }
        }

        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }

        return answer;
    }
}
