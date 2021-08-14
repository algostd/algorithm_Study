import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] memo = new int[N];
        String[] input = br.readLine().split("");
        for (int i = 0; i < N; i++) {
            String s = input[i];
            if (s.equals("B")) {
                arr[i] = 0;
            } else if (s.equals("O")) {
                arr[i] = 1;
            } else if (s.equals("J")) {
                arr[i] = 2;
            }
            memo[i] = Integer.MAX_VALUE;
        }

        //선형으로 간다.
        memo[0] = 0;
        for (int i = 1; i < N; i++) {
            int findNum = arr[i] - 1;
            if (arr[i] == 0) {
                findNum = 2;
            }

            int cnt = 0;
            for (int j = i-1; j >= 0; j--) {
                cnt++;
                if (arr[j] != findNum) {
                    continue;
                }

                //가지치기
                if (cnt * cnt > memo[i]) {
                    break;
                }


                if (memo[j] != Integer.MAX_VALUE) {
                    if (memo[j] + cnt*cnt < memo[i]) {
                        memo[i] = memo[j] + cnt*cnt;
                    }
                }
            }
        }

        if (memo[N-1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(memo[N-1]);
        }

    }
}
