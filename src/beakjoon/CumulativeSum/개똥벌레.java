import java.io.*;
import java.util.*;

public class Main {
    private static int[] LIS;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int H = Integer.parseInt(input[1]);

        //6 7
        //1
        //5
        //3
        //3
        //5
        //1

        int[] dp1 = new int[H+1];
        int[] dp2 = new int[H+1];
        int[] arr1 = new int[H+1];
        int[] arr2 = new int[H+1];

        int num0 = Integer.parseInt(br.readLine());
        dp1[num0]++;
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            //짝수일때 석순
            if (i % 2 == 0) {
                dp1[num]++;
            }
            //홀수일때 종유석
            if (i % 2 == 1) {
                dp2[num]++;
            }
        }

        //석순 누적합계산
        int sum1 = 0;
        for (int i = H; i >= 1; i--) {
            if (dp1[i] != 0) {
                sum1 += dp1[i];
            }
            arr1[i] = sum1;
        }

        //종유석 누적합계산
        //arr[H-i+1] = 1;
        int sum2 = 0;
        for (int i = H; i >= 1; i--) {
            if (dp2[i] != 0) { //i=5
                sum2 += dp2[i];
            }
            arr2[H-i+1] = sum2;
        }

        int[] arr = new int[H+1];
        int min = 1000000;
        for (int i = 1; i <= H; i++) {
            arr[i] = arr1[i] + arr2[i];
            min = Math.min(arr[i], min);
        }

        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            if (arr[i] == min) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}