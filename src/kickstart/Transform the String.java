import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= N; test_case++) {
            String S = br.readLine();
            String F = br.readLine();
            int answer = 0;
            int[] dp = new int[26];
            for (int i = 0; i < 26; i++) {
                dp[i] = -1;
            }

            //F
            int[] arr = new int[26];
            int lenF = F.length();
            for (int i = 0; i < lenF; i++) {
                int num = F.charAt(i)-97;
                arr[num] = 1;
            }

            //S
            int lenS = S.length();
            for (int i = 0; i < lenS; i++) {
                int num = S.charAt(i)-97;
                if (dp[num] != -1) {
                    answer += dp[num];
                    continue;
                }

                if (arr[num] == 1) {
                    dp[num] = 0;
                    continue;
                }

                int left = num;
                int right = num;
                int cnt1 = 0;
                int cnt2 = 0;
                while (arr[left] != 1) {
                    cnt1++;
                    left--;
                    if (left < 0) {
                        left = 25;
                    }

                    if (arr[left] == 1) {
                        break;
                    }
                }

                while (arr[right] != 1) {
                    cnt2++;
                    right++;
                    if (right > 25) {
                        right = 0;
                    }

                    if (arr[right] == 1) {
                        break;
                    }
                }

                answer += Math.min(cnt1, cnt2);
                dp[num] = Math.min(cnt1, cnt2);
            }

            bw.write("Case #");
            bw.write(String.valueOf(test_case));
            bw.write(": ");
            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }
}
