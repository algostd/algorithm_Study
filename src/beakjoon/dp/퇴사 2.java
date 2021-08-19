import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = Integer.parseInt(input);

        int[] dp = new int[N+2];
        int[] time = new int[N+1];
        int[] val = new int[N+1];
        for (int i = 1; i <= N; i++) {
            String[] str = br.readLine().split(" ");
            time[i] = Integer.parseInt(str[0]);
            val[i] = Integer.parseInt(str[1]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            int t = time[i];
            int v = val[i];

            //일을 하지않았을때
            //현재 값만을 그저 들고간다.
            dp[i+1] = Math.max(dp[i], dp[i+1]);
            max = Math.max(dp[i+1], max);

            //일을 했을때
            if (i+t > N+1) {
                continue;
            }
            dp[i+t] = Math.max(dp[i] + v, dp[i+t]);
            max = Math.max(dp[i+t], max);
        }

        if (max == Integer.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(max);
        }
    }
}
