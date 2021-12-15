import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 가장긴증가하는부분수열2 {
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = Integer.parseInt(str);

        String[] strArr = br.readLine().split(" ");
        int[] intArr = new int[N];
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        // BinarySearch를 이용한 LIS
        dp = new int[N + 1]; // 1부터 시작하는가 편의상 좋다.
        int maxPointer = 0;
        int binaryPointer = 0;

        for (int i = 0; i < N; i++) {
            if (dp[maxPointer] < intArr[i]) {
                dp[++maxPointer] = intArr[i];
            } else { // 이진탐색으로 어느 곳에 넣을지
                binaryPointer = binarySearch(intArr[i], 1, maxPointer);
                dp[binaryPointer] = intArr[i];
            }
        }
        System.out.println(maxPointer);
        for (int i : dp) {
            System.out.print(i + " ");
        }
    }

    private static int binarySearch(int num, int left, int right) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (num > dp[mid]) {
                left = mid + 1;
            } else if(num < dp[mid]){
                right = mid;
            } else {
                return mid;
            }
        }

        return right;
    }
}
