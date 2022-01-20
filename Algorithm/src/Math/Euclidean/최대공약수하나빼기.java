package Math.Euclidean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;
// https://www.acmicpc.net/problem/14476 (골드2) (Euclidean)
public class 최대공약수하나빼기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    private static int[] intArr;
    private static int[] leftGcd;
    private static int[] rightGcd;

    public static void main(String[] args) throws IOException {
        getInfo();
        getGcdDp();
        getAnswer();
        System.out.println(sb);
    }

    private static void getAnswer() {
        int maxGcd = 0;
        int removed = 0;
        int gcd;
        for (int i = 0; i < N; i++) {
            int K = intArr[i];
            if (i == 0) {
                gcd = rightGcd[1];
            } else if (i == N - 1) {
                gcd = leftGcd[N - 2];
            } else {
                gcd = makeGcd(leftGcd[i - 1], rightGcd[i + 1]);
            }
            if (gcd > maxGcd && K % gcd != 0) {
                maxGcd = gcd;
                removed = intArr[i];
            }

        }

        if (maxGcd == 0 && removed == 0) {
            sb.append(-1);
        }else{
            sb.append(maxGcd)
                    .append(" ")
                    .append(removed);

        }
    }

    private static void getGcdDp() {
        leftGcd = new int[N];
        rightGcd = new int[N];

        // 왼쪽 누적합
        leftGcd[0] = intArr[0];
        for (int right = 1; right < N; right++) {
            leftGcd[right] = makeGcd(leftGcd[right - 1], intArr[right]);
        }

        // 오른쪽 누적합
        rightGcd[N - 1] = intArr[N-1];
        for (int left = N - 2; left >= 0; left--) {
            rightGcd[left] = makeGcd(rightGcd[left + 1], intArr[left]);
        }
    }

    private static int makeGcd(int num1, int num2) {
        int bigger = Math.max(num1, num2);
        int smaller = Math.min(num1, num2);

        while (smaller != 0) {
            int r = bigger % smaller;
            bigger = smaller;
            smaller = r;
        }
        return bigger;
    }

    public static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        intArr = new int[N];
        String[] strArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
    }
}
