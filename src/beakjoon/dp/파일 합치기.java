import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            //입력
            n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");

            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(input[i]);
            }

            //n = 4
            //40 30 30 50
            dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            //알고리즘
            Main m = new Main();
            int gap = 0;

            while (gap <= n-1) {
                gap++;
                for (int k = 0; k < n-gap; k++) {
                    m.d(k, k+gap);
                }
            }

            //test
			/*
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					System.out.println("dp["+i+"]["+j+"]: " + dp[i][j]);
				}
			}
			*/

            System.out.println(dp[0][n-1]);
        }
    }

    private void d(int x, int y) {
        if (y-x == 1) {
            dp[x][y] = arr[x] + arr[y];
        }

        if (y-x == 2) {
            int sum = 0;
            for (int i = x; i <= y; i++) {
                sum += arr[i];
            }

            dp[x][y] = Math.min(dp[x][x+1]+sum, dp[y-1][y]+sum);
        }

        if (y-x >= 3) {
            int sum = 0;
            for (int i = x; i <= y; i++) {
                sum += arr[i];
            }

            //중간값 k
            for (int k = x; k < y; k++) {
                //시작점
                if (k == x) {
                    dp[x][y] = Math.min(dp[k+1][y]+sum, dp[x][y]);
                } else if (k == y-1) {
                    //끝점
                    dp[x][y] = Math.min(dp[x][k]+sum, dp[x][y]);
                } else {
                    //중간점
                    dp[x][y] = Math.min(dp[x][k]+dp[k+1][y]+sum, dp[x][y]);
                }
            }
        }
    }
}
