package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/2143 (골드3) (부분합)
public class 두배열의합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static long answer = 0;
    static int T;
    static int[] A;
    static int[] B;
    static Map<Integer, Integer> mapA = new HashMap<>();
    static Map<Integer, Integer> mapB = new HashMap<>();
    private static ArrayList<Integer> setB;

    public static void main(String[] args) throws IOException {
        getInfo();
        getSumCountMap(A, mapA);
        getSumCountMap(B, mapB);
        setB = new ArrayList<>(mapB.keySet());
        Collections.sort(setB);

        // aSum과 bSum을 비교하여 T를 만들수 있는경우, [개수 * 개수]를 정답에 더해줌
        for (Integer aSum : mapA.keySet()) {
            int bSum = T - aSum;
            if (mapB.get(bSum) != null) {
                answer += (long) mapA.get(aSum) * (long) mapB.get(bSum);
            }
        }
        System.out.println(answer);
    }

    private static void getSumCountMap(int[] arr, Map<Integer, Integer> map) {
        int N = arr.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = arr[i];
            if (map.containsKey(dp[i][i])) {
                map.put(dp[i][i], map.get(dp[i][i]) + 1);
            } else {
                map.put(dp[i][i], 1);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (i == 0) {
                    dp[i][j] = dp[0][j - 1] + arr[j];
                } else {
                    dp[i][j] = dp[0][j] - dp[0][i - 1];
                }

                if (map.containsKey(dp[i][j])) {
                    map.put(dp[i][j], map.get(dp[i][j]) + 1);
                } else {
                    map.put(dp[i][j], 1);
                }
            }
        }

    }

    public static void getInfo() throws IOException {
        T = Integer.parseInt(br.readLine());
        A = getArr();
        B = getArr();
    }

    private static int[] getArr() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N];
        String[] strArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }
}
