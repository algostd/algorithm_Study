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

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N+N];

        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            //화면에 그대로 출력하지 않는다면
            //전체선택
            //복사
            //붙여넣기
            //과정을 거쳐야 한다.
            long num = dp[i-1];
            long tmp = num;
            for (int j = i+2; j < N; j++) {
                tmp += num;
                dp[j] = Math.max(tmp, dp[j]);
            }

            //화면에 그대로 출력한다면
            dp[i] = Math.max(dp[i-1]+1, dp[i]);
        }

        System.out.println(dp[N-1]);
    }
}