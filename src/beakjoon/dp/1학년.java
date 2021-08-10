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

        int[] arr = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        long[] memo = new long[21];

        int last = arr[N - 1];
        memo[arr[0]]++; //시작점

        for (int i = 1; i < N-1; i++) {
            int tmp = arr[i];

            //배열복사
            long[] cur = new long[21];
            for (int k = 0; k <= 20; k++) {
                cur[k] = memo[k];
            }

            //덧셈뺄셈
            for (int k = 0; k <= 20; k++) {
                long value = cur[k];

                if (value == 0) {
                    continue;
                }

                int plus = k + tmp;
                int minus = k - tmp;
                memo[k] -= value;

                if (plus >= 0 && plus <= 20) {
                    memo[plus] += value;
                }
                if (minus >= 0 && minus <= 20) {
                    memo[minus] += value;
                }
            }
        }

        System.out.println(memo[last]);
    }

}
