package Binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2805 (실버3)
public class 나무자르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int M;
    private static int[] intArr;

    public static void main(String[] args) throws IOException {
        int answer = 0;
        String[] strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        M = Integer.parseInt(strArr[1]);

        intArr = new int[N];
        strArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        // 이분탐색
        int left = 0;
        int right = Arrays.stream(intArr).max().getAsInt();
        while (left <= right) {
            int cutHeight = (right + left) / 2;
            long tree = getTree(cutHeight);
            if (tree == M) {
                answer = Math.max(answer, cutHeight);
                break;
            } else if (tree > M) {
                answer = Math.max(answer, cutHeight);
                left = cutHeight + 1;
            } else {
                right = cutHeight - 1;
            }
        }
        System.out.println(answer);
    }

    private static long getTree(int cutHeight) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (intArr[i] - cutHeight > 0) {
                sum += intArr[i] - cutHeight;
            }
        }
        return sum;
    }

}
