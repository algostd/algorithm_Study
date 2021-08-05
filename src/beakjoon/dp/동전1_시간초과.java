import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K, cnt;
    private static Integer[] arr;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        arr = new Integer[N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int num = Integer.parseInt(str);
            arr[i] = num;
        }

        // 내림차순으로 정렬
        Arrays.sort(arr, Collections.reverseOrder());

        cnt = 0;
        Main main = new Main();
        main.recur(K, 0);

        System.out.println(cnt);
    }

    private void recur(int num, int idx) {
        int divideNum = arr[idx];
        int m = num / divideNum;
        int remain = num % divideNum;

        // 마지막 인덱스일 경우
        if (idx == N - 1) {
            if (remain == 0) {
                cnt++;
            }
            return;
        }

        for (int i = 0; i <= m; i++) {
            if (num == i * divideNum) {
                cnt++;
            } else {
                recur(num - (i * divideNum), idx + 1);
            }
        }
    }
}