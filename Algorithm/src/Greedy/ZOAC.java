package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/16719 (골드5) (분할정복)
public class ZOAC {

    private static int N;

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder answerSb = new StringBuilder();
    private static boolean[] visited;
    private static String str;

    public static void main(String[] args) throws IOException {
        getInfo();
        getAnswer();
        System.out.println(answerSb);
    }

    private static void getAnswer() {
        getLowWord(0, N -1);
    }

    private static void getLowWord(int left, int right) {
        // 탈출
        if (left >= N ) {
            return;
        }
        // 최소 char 찾아봄
        char min = 'Z' + 1;
        int minIndex = -1;
        for (int i = left; i <= right; i++) {
            if (!visited[i] && str.charAt(i) < min) {
                min = str.charAt(i);
                minIndex = i;
            }
        }

        // 찾았다면
        if (minIndex != -1) {
            visited[minIndex] = true;
            printNow();
            getLowWord(minIndex + 1, right);
            getLowWord(left, minIndex - 1);
        }

    }

    private static void printNow() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                sb.append(str.charAt(i));
            }
        }
        answerSb.append(sb).append("\n");
    }

    public static void getInfo() throws IOException {
        str = br.readLine();
        N = str.length();
        visited = new boolean[N];
    }
}
