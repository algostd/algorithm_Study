package Union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/3830 (플레3) (Union-Find)
public class 교수님은기다리지않는다 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] parent;
    static long[] dp;
    static final String UNKNOWN = "UNKNOWN";

    public static void main(String[] args) throws IOException {
        while (true) {
            if (getInfo()) {
                break;
            }
            int a, b, w, p1, p2;
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String token = st.nextToken();
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (token.equals("?")) {
                    if (find(a) == find(b)) {
                        sb.append(dp[b] - dp[a]);
                    } else {
                        sb.append(UNKNOWN);
                    }
                    sb.append("\n");
                } else {
                    w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                }

            }
        }
        System.out.println(sb);
    }

    private static int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        int parentIndex = find(parent[num]);
        dp[num] += dp[parent[num]];
        return parent[num] = parentIndex;
    }

    // 왼쪽으로 온값이, union-find 의 부모노드(기준점)가 된다.
    private static void union(int a, int b, int w) {
        int pA = find(a);
        int pB = find(b);
        if (pA == pB) {
            return;
        }
        dp[pB] = dp[a] - dp[b] + w;
        parent[pB] = pA;
    }

    private static boolean getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N == 0 && M == 0) {
            return true;
        }
        parent = new int[N + 1];
        dp = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        return false;
    }

}
