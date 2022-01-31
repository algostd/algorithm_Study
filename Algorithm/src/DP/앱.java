package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/7579 (골드3) (DP)
public class 앱 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N, memory, totalPrice;
    static int[] memories, prices;
    static int[][] dp;
    private static int answer;

    public static void main(String[] args) throws IOException {
        getInfo();
        getDP();
        getAnswer();
        System.out.println(answer);
    }

    private static void getAnswer() {
        for (int price = 0; price <= totalPrice; price++) {
            if (dp[N][price] >= memory) {
               answer = price;
               break;
            }
        }
    }

    private static void getDP() {
        for (int i = 1; i <= N; i++) {
            for (int price = 0; price <= totalPrice; price++) {
                dp[i][price] = Math.max(dp[i][price], dp[i - 1][price]);
                if (price - prices[i] >= 0) {
                    dp[i][price] = Math.max(dp[i][price], dp[i - 1][price - prices[i]] + memories[i]);
                }
            }
        }
    }

    public static void getInfo() throws IOException {
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        memory = Integer.parseInt(st1.nextToken());

        st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        memories = new int[N + 1];
        prices = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            memories[i] = Integer.parseInt(st1.nextToken());
            prices[i] = Integer.parseInt(st2.nextToken());
            totalPrice += prices[i];
        }
        dp = new int[N + 1][totalPrice + 1];
    }
}
