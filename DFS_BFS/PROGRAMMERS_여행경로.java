package DFS_BFS;

import java.util.*;
/*
 * 문제 출처 : 프로그래머스
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/43164
 * 걸린 시간 : 40분
 */
class PROGRAMMERS_여행경로 {
    static String[][] tickets;
    static String[] answer;
    static boolean[] visited;
    static boolean isFinished;
    static int N;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        N = tickets.length; // 항공권의 수
        answer = new String[N+1];
        visited = new boolean[N]; // 방문체크 배열

        // 알파벳 순으로 티켓 정렬
        Arrays.sort(tickets, (String[] o1, String[] o2) -> {
            if (o1[0].equals(o2[0])) {
                return o1[1].compareTo(o2[1]);
            }
            else {
                return o1[0].compareTo(o2[0]);
            }
        });

        answer[0] = "ICN"; // 처음 출발 도시 ICN으로 고정

        for (int i = 0; i < N; i++) { // 처음 출발 도시 검색
            if (tickets[i][0].equals("ICN")) { // ICN 티켓을 찾으면 dfs 시작
                visited[i] = true;
                dfs(1, tickets[i][1]);
                if (isFinished) break; // 이미 경로를 찾았으면 반복문 끝내기
                visited[i] = false;
            }
        }

        return answer;
    }

    private static void dfs(int cnt, String start) { // cnt : 현재까지 방문한 도시 개수, start : 출발 도시
        if (cnt == N) { // 모든 경로를 다 찾았을 경우
            answer[cnt] = start; // 마지막 도시 저장
            isFinished = true; // 경로 완성 표시
            return;
        }

        if (isFinished) return; // 이미 경로를 찾았으면 할 필요 X

        answer[cnt] = start; // 방문 도시 저장

        for (int i = 0; i < N; i++) {
            if (!visited[i] && tickets[i][0].equals(start)) { // 사용하지 않은 티켓이며 출발 도시가 일치할 경우
                visited[i] = true;
                dfs(cnt+1, tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}