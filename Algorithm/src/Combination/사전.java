package Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1256
public class 사전 {

    static int N, M, K;
    static int[][] dp = new int[201][201];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        getInfo();

        if (K > combination(N + M, M)) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder();
            query(N, M, K, sb);
            System.out.println(sb);
        }
    }

    private static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    public static void query(int n, int m, int k, StringBuilder sb) {
        if (n + m == 0) {
            return;
        } else if (n == 0) {
            sb.append("z");
            query(n, m - 1, k, sb);
        } else if (m == 0) {
            sb.append("a");
            query(n - 1, m, k, sb);
        } else {
            int leftCount = combination(n + m - 1, m);
            if (leftCount >= k) {
                sb.append('a');
                query(n - 1, m, k, sb);
            } else {
                sb.append('z');
                query(n, m - 1, k - leftCount, sb);
            }
        }
    }

    public static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else if (dp[n][r] != 0) {
            return dp[n][r];
        } else {
            return dp[n][r] = Math.min((int) 1e9, combination(n - 1, r - 1) + combination(n - 1, r));
        }
    }
}