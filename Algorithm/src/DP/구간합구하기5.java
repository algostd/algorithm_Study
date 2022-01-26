package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/11660 (실버1) (DP)
public class 구간합구하기5 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] map;
    static int[][] dp; // 0,0 에서 부터 i,j 까지의 합

    public static void main(String[] args) throws IOException {
        getInfo();
        getDp();
        getAnswer();
        System.out.println(sb);
    }

    private static void getAnswer() throws IOException {
        int r1, c1, r2, c2;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());
            sb.append(dp[r2][c2] - (dp[r1 - 1][c2] + dp[r2][c1 - 1]) + dp[r1 - 1][c1 - 1])
                    .append("\n");
        }
    }

    private static void getDp() {
        for (int row = 1; row <= N; row++) {
            dp[row][1] = dp[row - 1][1] + map[row][1];
            for (int col = 2; col <= N; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
                        - dp[row - 1][col - 1] + map[row][col];

            }

        }
    }

    public static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}