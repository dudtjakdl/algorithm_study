package Implementation;

import java.util.*;
import java.io.*;
/*
 * 문제 출처 : Softeer
 * 문제 링크 : https://softeer.ai/practice/info.do?idx=1&eid=623
 * 걸린 시간 : 20분
 */
public class SOFTEER_비밀_메뉴 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken()); // 비밀 메뉴 조작법 버튼 개수
        int N = Integer.parseInt(st.nextToken()); // 사용자의 버튼 조작 개수
        int K = Integer.parseInt(st.nextToken()); // 버튼의 개수
        int[] sMenu = new int[M];
        int[] input = new int[N];

        if (M > N) {
            System.out.println("normal");
            return;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            sMenu[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;

        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j < M; j++) {
                if (sMenu[j] != input[i+j]) break;
                if (j == M - 1) flag = true;
            }

            if (flag) break;
        }

        if (flag) {
            System.out.println("secret");
        }
        else {
            System.out.println("normal");
        }

        /* 다른 풀이 - 문자열 라이브러리인 contains를 사용하는 방법
            String sMenu = br.readLine();
            String input = br.readLine();

            if (input.contains(sMenu)) {
                System.out.println("secret");
            }
            else {
                System.out.println("normal");
            }
         */
    }
}
