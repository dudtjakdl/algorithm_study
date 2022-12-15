package DFS_BFS;

import java.util.*;
import java.io.*;
/*
 * 문제 출처 : Softeer
 * 문제 링크 : https://softeer.ai/practice/info.do?idx=1&eid=409
 * 걸린 시간 : 30분
 */
public class SOFTEER_장애물_인식_프로그램 {
    static int N, blockCnt;
    static List<Integer> answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 지도의 크기
        map = new int[N][N];
        visited = new boolean[N][N];
        answer = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        Collections.sort(answer);

        System.out.println(blockCnt);

        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }

    }

    private static void bfs (int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startRow, startCol});
        visited[startRow][startCol] = true;

        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = curRow + deltas[i][0];
                int nc = curCol + deltas[i][1];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                    cnt += 1;
                }
            }
        }

        blockCnt += 1;
        answer.add(cnt);
    }

}
