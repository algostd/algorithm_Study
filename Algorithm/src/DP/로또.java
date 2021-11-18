package Algorithm.src.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.in;

public class 로또 {
    private static int N;
    private static int M;
    private static long answer;
    private static long[][] dp;
    private static ArrayList<Integer> arrayList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int T = Integer.parseInt(br.readLine());
        String[] strArr;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            arrayList = new ArrayList<>();
            answer = 0;
            strArr = br.readLine().split(" ");
            N = Integer.parseInt(strArr[0]);
            M = Integer.parseInt(strArr[1]);
            dp = new long[N][M + 1];

            // 범위
            int m = M;
            arrayList.add(M);
            for (int i = 0; i < N - 1; i++) {
                m /= 2;
                arrayList.add(m);
            }


            // 정답 도출
            for (int i = 1; i <= arrayList.get(N - 1); i++) {
                answer += get(i, N - 1);
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }

    private static long get(int num, int restCount) {
        if (restCount == 0) {
            return 1;
        }

        int next = num * 2;
        long sum = 0;
        for (int i = next; i <= arrayList.get(restCount - 1); i++) {
            if (dp[restCount][i] == 0) {
                dp[restCount][i] = get(i, restCount - 1);
            } else{
            }
            sum += dp[restCount][i];
        }

        return sum;
    }

}