import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬물음표 {

    private static int[] intArr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        int N = Integer.parseInt(br.readLine());
        String[] strArr = br.readLine().split(" ");
        intArr = new int[N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        // 모든 경우에 수에 대한 팰린드롬 인지 결과 내기
        int right = 0;
        int left = 0;
        for (int n = 1; n < N; n++) {
            for (left = 0; left + n < N; left++) {
                right = left + n;
                dp[left][right] = checkPalindrome(left, right);
            }
        }


        // 입력받고 결과내기
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            strArr = br.readLine().split(" ");
            left = Integer.parseInt(strArr[0]) - 1;
            right = Integer.parseInt(strArr[1]) - 1;
            if (left == right) {
                sb.append(1).append("\n");
                continue;
            }
            sb.append(dp[left][right]).append("\n");
        }
        System.out.println(sb);
    }

    private static int checkPalindrome(int left, int right) {
        while (left < right) {
            if (dp[left][right] == 1) {
                return 1;
            }
            if (intArr[left] != intArr[right]) {
                return 0;
            }
            left++;
            right--;
        }
        return 1;
    }
}
