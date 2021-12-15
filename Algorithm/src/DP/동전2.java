import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/2294
public class 동전2 {

    final static int MAXIMUM = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");

        int N = Integer.parseInt(strArr[0]);
        int K = Integer.parseInt(strArr[1]);
        Set<Integer> coins = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            coins.add(num);
        }
        int[] dp = new int[K + 1];
        Arrays.fill(dp, MAXIMUM);
        dp[0] = 0;
        for (Integer coin : coins) {
            for (int i = coin; i <= K; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if(dp[K] == MAXIMUM){
            System.out.println(-1);
        } else{
            System.out.println(dp[K]);
        }
    }
}