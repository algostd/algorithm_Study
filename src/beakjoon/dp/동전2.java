import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        //중복제거
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int num = Integer.parseInt(str);
            set.add(num);
        }

        int[] coin = new int[set.size()];
        int ax = 0;
        for (int num : set) {
            coin[ax] = num;
            ax++;
        }

        int[] dp = new int[10001];
        for (int i = 1; i <= 10000; i++) {
            dp[i] = 10001;
        }
        dp[0] = 0;

        for (int i = 0; i < coin.length; i++) {
            int num = coin[i];
            for (int j = num; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - num] + 1);
            }
        }

        if (dp[K] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[K]);
        }

    }
}