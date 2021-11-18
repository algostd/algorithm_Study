package Algorithm.src.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV134DPqAA8CFAYh

public class View {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] buildings = new int[N];
            int[] counts = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int i = 0;
            while (st.hasMoreTokens()) {
                buildings[i++] = Integer.parseInt(st.nextToken());
            }


            for (i = 2; i < N - 2; i++) {
                if (buildings[i] > buildings[i - 2] && buildings[i] > buildings[i - 1] &&
                        buildings[i] > buildings[i + 1] && buildings[i] > buildings[i + 2]) {
                    int maxFloor = 0;
                    if (maxFloor < buildings[i - 2]) {
                        maxFloor = buildings[i - 2];
                    }
                    if (maxFloor < buildings[i - 1]) {
                        maxFloor = buildings[i - 1];
                    }
                    if (maxFloor < buildings[i + 1]) {
                        maxFloor = buildings[i + 1];
                    }
                    if (maxFloor < buildings[i + 2]) {
                        maxFloor = buildings[i + 2];
                    }
                    counts[i] = buildings[i] - maxFloor;
                }
            }

            int answer = 0;
            for (i = 0; i < N; i++) {
                answer += counts[i];
            }
            System.out.println("#" + test_case + " " + answer);
        }

    }
}
