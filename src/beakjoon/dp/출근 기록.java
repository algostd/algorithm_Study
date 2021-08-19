import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int N;
    private static boolean check;
    private static int[][][][][] dp;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int cntA = 0;
        int cntB = 0;
        int cntC = 0;

        N = input.length();
        for (int i = 0; i < N; i++) {
            char ch = input.charAt(i);
            if (ch == 'A') {
                cntA++;
            }
            if (ch == 'B') {
                cntB++;
            }
            if (ch == 'C') {
                cntC++;
            }
        }

        check = false;
        dp = new int[cntA+1][cntB+1][cntC+1][3][3];

        Main m = new Main();
        m.dfs(cntA, cntB, cntC, "", 0, 0);

        if (check == false) {
            System.out.println(-1);
        }
    }

    private void dfs(int a, int b, int c, String s, int pre1, int pre2) {
        if (check == true) {
            return;
        }

        if (a == 0 && b == 0 && c == 0) {
            System.out.println(s);
            check = true;
            return;
        }

        if (dp[a][b][c][pre1][pre2] == 1) {
            return;
        }
        dp[a][b][c][pre1][pre2] = 1;

        if (a > 0) {
            dfs(a-1,b,c,s+"A",0,pre1);
        }
        if (b > 0 && pre1 != 1) {
            dfs(a,b-1,c,s+"B",1,pre1);
        }
        if (c > 0 && pre1 != 2 && pre2 != 2) {
            dfs(a,b,c-1,s+"C",2,pre1);
        }
    }
}
