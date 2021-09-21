import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ACM_Craft2 {
    private static int[] dp;
    private static int[] prices;
    private static ArrayList<Integer>[] rules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            String[] strArr = br.readLine().split(" ");
            int N = Integer.parseInt(strArr[0]);
            int K = Integer.parseInt(strArr[1]);
            dp = new int[N + 1];
            Arrays.fill(dp, -1);
            prices = new int[N + 1];
            rules = new ArrayList[N + 1]; // 4, 2-3이 만족되야함
            for (int i = 1; i < N + 1; i++) {
                rules[i] = new ArrayList<>();
            }
            // 건물비용 초기값 대입
            strArr = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                prices[i] = Integer.parseInt(strArr[i - 1]);
            }
            // K개의 규칙 적용
            for (int i = 0; i < K; i++) {
                strArr = br.readLine().split(" ");
                int start = Integer.parseInt(strArr[0]);
                int dest = Integer.parseInt(strArr[1]);
                rules[dest].add(start);
            }
            // W
            int W = Integer.parseInt(br.readLine());
            
            // 알고리즘 시작(TopDown)
            int minTime = getMinTime(W);
            sb.append(minTime + "\n");
        }
        System.out.println(sb);
    }

    private static int getMinTime(int w) {
        if(dp[w] != -1){
            return dp[w];
        }

        int max = 0;
        for (Integer before : rules[w]) {
            max = Math.max(max,getMinTime(before));
        }
        return dp[w] = max + prices[w];
    }
}

