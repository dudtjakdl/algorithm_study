package Implementation;

import java.util.*;
import java.io.*;
/*
 * 문제 출처 : Softeer
 * 문제 링크 : https://softeer.ai/practice/info.do?idx=1&eid=623
 * 걸린 시간 : 30분
 */
public class SOFTEER_GBC {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 구간의 개수
        int M = Integer.parseInt(st.nextToken()); // 테스트하는는 구간의 개수
        int[] speeds = new int[101]; // 각 구간의 제한 속도
        int[] tests = new int[101]; // 테스트하는 구간의 속도
        int start = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) { // 각 구간의 제한 속도 입력
            st = new StringTokenizer(br.readLine(), " ");
            int len = Integer.parseInt(st.nextToken()); // 구간 길이
            int speed = Integer.parseInt(st.nextToken()); // 구간 제한 속도

            for (int j = 1; j <= len; j++) {
                speeds[start+j] = speed;
            }

            start += len;
        }

        start = 0;

        for (int i = 0; i < M; i++) { // 각 테스트 구간의 속도 입력
            st = new StringTokenizer(br.readLine(), " ");
            int len = Integer.parseInt(st.nextToken()); // 구간 길이
            int speed = Integer.parseInt(st.nextToken()); // 구간 제한 속도

            for (int j = 1; j <= len; j++) {
                tests[start+j] = speed;
            }

            start += len;
        }

        for (int i = 1; i <= 100; i++) {
            if (speeds[i] < tests[i]) {
                answer = Math.max(answer, tests[i] - speeds[i]);
            }
        }

        System.out.println(answer);
    }
}
