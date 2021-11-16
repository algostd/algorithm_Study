import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int len = s.length();

            int[] R = new int[100002];
            int[] Y = new int[100002];
            int[] B = new int[100002];

            //U = Uncolored
            //R = Red
            //Y = Yellow
            //B = Blue

            //O = Orange
            //P = Purple
            //G = Green
            //A = Gray

            //Red + Yellow = Orange
            //Red + Blue = Purple
            //Yellow + Blue = Green
            //Red + Yellow + Blue = Gray

            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                if (ch == 'R') {
                    R[i] = 1;
                }
                if (ch == 'Y') {
                    Y[i] = 1;
                }
                if (ch == 'B') {
                    B[i] = 1;
                }
                if (ch == 'O') {
                    R[i] = 1;
                    Y[i] = 1;
                }
                if (ch == 'P') {
                    R[i] = 1;
                    B[i] = 1;
                }
                if (ch == 'G') {
                    Y[i] = 1;
                    B[i] = 1;
                }
                if (ch == 'A') {
                    R[i] = 1;
                    Y[i] = 1;
                    B[i] = 1;
                }
            }

            int toggle1 = 0;
            int toggle2 = 0;
            int toggle3 = 0;

            int answer = 0;
            for (int i = 0; i < len; i++) {
                if (R[i] == 1) {
                    if (toggle1 == 0) {
                        toggle1 = 1;
                        answer++;
                    }
                } else {
                    toggle1 = 0;
                }

                if (Y[i] == 1) {
                    if (toggle2 == 0) {
                        toggle2 = 1;
                        answer++;
                    }
                } else {
                    toggle2 = 0;
                }

                if (B[i] == 1) {
                    if (toggle3 == 0) {
                        toggle3 = 1;
                        answer++;
                    }
                } else {
                    toggle3 = 0;
                }
            }

            bw.write("Case #");
            bw.write(String.valueOf(test_case));
            bw.write(": ");
            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }
}
