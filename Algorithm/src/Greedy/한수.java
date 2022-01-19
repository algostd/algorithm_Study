package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1065 (실버4)
public class 한수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int zaricksoo = 1;
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (i == 1000) {
                break;
            }
            if (i == 10 || i == 100) {
                zaricksoo++;
            }
            if (zaricksoo <= 2 || checkEqualDiff(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean checkEqualDiff(int num) {
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = num % 10;
            num /= 10;
        }
        if (arr[0] - arr[1] == arr[1] - arr[2]) {
            return true;
        }
        return false;
    }
}
