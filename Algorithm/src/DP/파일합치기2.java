import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 파일합치기2 {
    private static long[][] dp;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String[] str;
        StringBuilder sb = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            dp = new long[N + 1][N + 1];
            arr = new int[N + 1];
            long[] sum = new long[N + 1];

            str = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(str[i-1]);
                sum[i] = sum[i - 1] + arr[i];
            }

            // DP
            long cost = 0;
            for (int n = 1; n <= N; n++) { // 몇 개씩 검사할건지
                for (int from = 1; from + n <= N; from++) {
                    int to = from + n;
                    dp[from][to] = Long.MAX_VALUE;
                    for (int start = from; start < to; start++) {
                        cost = dp[from][start] + dp[start + 1][to] + sum[to] - sum[from - 1];
                        dp[from][to] = Math.min(dp[from][to], cost);
                    }
                }
            }
            sb.append(dp[1][N]).append("\n");
        }
        System.out.println(sb);

    }
}
