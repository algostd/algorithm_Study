package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/11049 (골드3) (DP)
public class 행렬곱샘순서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N;
    static int INF = Integer.MAX_VALUE;
    private static int[][] matrix;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInfo();
        getDp();
    }

    private static void getDp() {
        for (int length = 1; length < N; length++) {
            for (int start = 0; start + length < N; start++) {
                dp[start][start + length] = INF;
                calDp(start, start + length);
            }
        }
        System.out.println(dp[0][N - 1]);
    }

    private static void calDp(int start, int end) {
        for (int i = start; i < end; i++) {
            int cost = dp[start][i] + dp[i + 1][end]
                    + matrix[start][0] * matrix[i][1] * matrix[end][1];
            dp[start][end] = Math.min(dp[start][end], cost);
        }
    }

    private static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][2];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
