package TSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/2098 (골드1) (TSP)
public class 외판원순회 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N;
    static int[][] map;
    static int[][] dp;
    static int VISIT_DONE;
    static final int INF = 16000000;

    public static void main(String[] args) throws IOException {
        getInfo();
        System.out.println(getTSP(0, 1));
    }

    public static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        VISIT_DONE = (1 << N) - 1;
        dp = new int[N][VISIT_DONE + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }
    }

    public static int getTSP(int now, int visit) {
        if (visit == VISIT_DONE) {
            if (map[now][0] != 0) {
                return map[now][0];
            } else {
                return INF;
            }
        }

        if (dp[now][visit] != INF) {
            return dp[now][visit];
        }

        for (int next = 1; next < N; next++) {
            // 방문 안했고 && 경로가 있으면
            if (((1 << next) & visit) < 1 && map[now][next] != 0) {
                int price = getTSP(next, visit | (1 << next)) + map[now][next];
                dp[now][visit] = Math.min(dp[now][visit], price);
            }
        }
        return dp[now][visit];
    }
}
