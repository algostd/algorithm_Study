import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 가장큰증가부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 저장
        int[] intArr = new int[N];
        int[] maxSumArr = new int[N];
        String[] strArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        // LIS 알고리즘
        for (int p1 = 0; p1 < N; p1++) {
            maxSumArr[p1] = intArr[p1];
            for (int p2 = 0; p2 < p1; p2++) {
                if (intArr[p2] < intArr[p1]) {
                    maxSumArr[p1] = Math.max(maxSumArr[p2] + intArr[p1], maxSumArr[p1]);
                }
            }
        }

        System.out.println(Arrays.stream(maxSumArr).max().getAsInt());
    }
}
