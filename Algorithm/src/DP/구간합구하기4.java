package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/11659 (실버3) (DP)
public class 구간합구하기4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] intArr, dp;

    public static void main(String[] args) throws IOException {
        getInfo();
        getAnswer();
        System.out.println(sb);
    }

    private static void getAnswer() throws IOException {
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(dp[to] - dp[from - 1]).append("\n");
        }
    }

    public static void getInfo() throws IOException {
        String[] strArr;
        strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        M = Integer.parseInt(strArr[1]);
        intArr = new int[N + 1];
        dp = new int[N + 1];

        strArr = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            intArr[i] = Integer.parseInt(strArr[i - 1]);
            dp[i] = dp[i - 1] + intArr[i];
        }
    }
}