package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/14719 (골드5)
public class 빗물 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int H, W;
    private static int[] height, left, right;
    static int max = -1;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        getInfo();
        getLeft();
        getRight();
        getAnswer();
        System.out.println(answer);
    }

    private static void getAnswer() {
        for (int i = 0; i < W; i++) {
            answer += Math.min(left[i], right[i]) - height[i];
        }
    }

    private static void getRight() {
        max = -1;
        for (int i = W -1; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
            right[i] = max;
        }
    }

    private static void getLeft() {
        max = -1;
        for (int i = 0; i < W; i++) {
            if (height[i] > max) {
                max = height[i];
            }
            left[i] = max;
        }
    }

    public static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        height = new int[W];
        left = new int[W];
        right = new int[W];
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }


    }
}
