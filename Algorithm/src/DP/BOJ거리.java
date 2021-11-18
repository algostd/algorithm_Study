package Algorithm.src.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        char[] chars = str.toCharArray();
        int[] ints = new int[N];
        Arrays.fill(ints, 1000001);
        ints[0] = 0;
        for (int i = 0; i < N - 1; i++) {
            if (chars[i] == 'B') {
                for (int next = i + 1; next < N; next++) {
                    if (chars[next] == 'O') {
                        ints[next] = Math.min(ints[next], (next - i) * (next - i) + ints[i]);
                    }
                }
            } else if (chars[i] == 'O') {
                for (int next = i + 1; next < N; next++) {
                    if (chars[next] == 'J') {
                        ints[next] = Math.min(ints[next], (next - i) * (next - i) + ints[i]);
                    }
                }
            } else if (chars[i] == 'J') {
                for (int next = i + 1; next < N; next++) {
                    if (chars[next] == 'B') {
                        ints[next] = Math.min(ints[next], (next - i) * (next - i) + ints[i]);
                    }
                }
            }
        }
//        for (int anInt : ints) {
//            System.out.print(anInt + " ");
//        }
//        System.out.println();
        if (ints[N - 1] == 1000001) {
            System.out.println(-1);
        } else{
            System.out.println(ints[N-1]);
        }

    }
}
