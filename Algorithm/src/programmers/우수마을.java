import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 우수마을 {
    private static int N;
    private static ArrayList<Integer>[] adjList;
    private static int[] prices;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 설정
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        prices = new int[N + 1];
        dp = new int[N + 1][2]; //
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 가격
        String[] strArr = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            prices[i] = Integer.parseInt(strArr[i - 1]);
        }
        // 인접리스트
        for (int i = 0; i < N - 1; i++) {
            strArr = br.readLine().split(" ");
            int num1 = Integer.parseInt(strArr[0]);
            int num2 = Integer.parseInt(strArr[1]);
            adjList[num1].add(num2);
            adjList[num2].add(num1);
        }

        int answer = dfs(1, 0);
        System.out.println(answer);
    }

    private static int dfs(int now, int parent) {
        dp[now][0] = 0;
        dp[now][1] = prices[now];

        for (Integer next : adjList[now]) {
            if (next != parent) {
                dp[now][0] += dfs(next, now);
                dp[now][1] += dp[next][0];
            }
        }
        return Math.max(dp[now][0], dp[now][1]);
    }
}
