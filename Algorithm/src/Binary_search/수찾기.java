import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1920
public class 수찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answerSb = new StringBuilder();
    static final int MAX = 100001;
    private static int[] numArr;

    public static void main(String[] args) throws IOException {
        // 입력
        int N = Integer.parseInt(br.readLine());
        String[] strArr = br.readLine().split(" ");
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(strArr[i]);
            numArr[i] = num;
        }
        // 정렬
        Arrays.sort(numArr);

        // 이진 탐색과 정답 도출
        int M = Integer.parseInt(br.readLine());
        strArr = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(strArr[i]);
            if (binarySearch(num)) {
                answerSb.append(1);
            } else {
                answerSb.append(0);
            }
            answerSb.append("\n");
        }
        System.out.println(answerSb);
    }

    private static boolean binarySearch(int num) {
        int left = 0;
        int right = numArr.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (numArr[mid] == num) {
                return true;
            } else if (numArr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}