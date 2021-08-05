import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        coin = new int[101];
        dp = new int[10001];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int num = Integer.parseInt(str);
            coin[i] = num;
        }

        dp[0] = 1; // 자기자신의 값
        for (int i = 0; i < N; i++) {
            int num = coin[i]; // 1 2 5
            for (int j = num; j <= K; j++) {
                //해당하는 동전의 값을 뺀 dp값을 계속해서 갱신한다.
                dp[j] = dp[j] + dp[j - num];
            }
        }

        System.out.println(dp[K]);
    }
}