import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class 파일합치기 {

    private static long[][] dp;
    private static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String[] str;
        StringBuilder sb = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            str = br.readLine().split(" ");
            dp = new long[N][N];
            arr = new long[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Long.MAX_VALUE);
            }
            for (int i = 0; i < N; i++) {
                dp[i][i] = Integer.parseInt(str[i]);
                arr[i][i] = dp[i][i];
            }

            // DP
            long cost = 0;
            for (int i = 1; i < N; i++) { // 몇 개씩 검사할건지

                for (int j = 0; j + i < N; j++) {

                    for (int start = j; start < j + i; start++) {
                        cost = dp[j][start] + dp[start + 1][j + i];
                        if (j != start) {
                            cost += getArrSum(j, start);
                        }
                        if (start + 1 != j + i) {
                            cost += getArrSum(start + 1, j + i);
                        }
                        dp[j][j + i] = Math.min(dp[j][j + i], cost);
                    }
                }
            }
            sb.append(dp[0][N - 1]).append("\n");
        }
        System.out.println(sb);

    }

    private static long getArrSum(int start, int end) {
        if (arr[start][end] != 0) {
            return arr[start][end];
        }
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i][i];
        }
        return arr[start][end] = sum;
    }
}
