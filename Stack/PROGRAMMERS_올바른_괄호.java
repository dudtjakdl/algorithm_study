package Stack;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12906
 * 걸린 시간 : 30분
 */
class PROGRAMMERS_올바른_괄호 {
    boolean solution(String s) {
        boolean answer = true;
        char[] chars = s.toCharArray(); // 문자열을 문자형 배열로 변환
        Stack<Character> stack = new Stack<>();

        for (char ch : chars) {
            if (ch == '(') stack.push(ch); // 열린 괄호일 경우 스택에 push
            else { // 닫힌 괄호일 경우
                if (stack.empty()) { // 짝이 되는 열린 괄호가 없으면 불가능
                    answer = false;
                    break;
                }
                stack.pop(); // 짝이 되는 열린 괄호 꺼내기
            }
        }

        // 스택에 열린 괄호가 남아있으면 불가능한 경우
        if (answer && !stack.empty()) answer = false;

        return answer;
    }
}
