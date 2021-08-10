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

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);

        int[] arr = new int[N];
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        if (arr[0] >= S) {
            System.out.println(1);
            return;
        }

        int p1 = 0;
        int p2 = 0;
        int sum = arr[p1];
        int cnt = 1;
        int min = Integer.MAX_VALUE;
        while(p2 < N - 1) {
            //구간 내에 부분합이 S이상이 아닐 경우 p2++;
            if (sum < S) {
                p2++;
                sum += arr[p2];
                cnt++;
                continue;
            } else if (sum >= S){
                //구간 내에 부분합이 S이상일 경우 p2 stop;
                min = Math.min(cnt, min);

                sum -= arr[p1];
                p1++;
                cnt--;
            }
        }

        //마지막에 p1처리
        while(sum >= S) {
            min = Math.min(cnt, min);

            sum -= arr[p1];
            p1++;
            cnt--;
        }

        if (min == Integer.MAX_VALUE) {
            min = 0;
        }

        System.out.println(min);
    }

}