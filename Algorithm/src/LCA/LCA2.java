package LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/11438 (플레5) (LCA)
public class LCA2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] depth;
    static ArrayList<Integer>[] adjList;
    static int[][] parent;
    static int height;

    public static void main(String[] args) throws IOException {
        getInfo();
        getDepth(1);
        getSparseTable();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getLCA(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    private static int getLCA(int a, int b) {
        // a가 더 깊은 곳에 있다고 가정
        if (depth[a] < depth[b]) {
            return getLCA(b, a);
        }
        // 높이 맞추기
        for (int i = 0; i <= height; i++) {
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                a = parent[i][a];
            }
        }
        // 같은지 검사
        if (a == b) {
            return a;
        }

        // 맨위에서부터 공통조상이 아닐때까지 내려온다
        // 최종적으로는 LCA 바로 밑칸까지 내려오고, 그럼 바로 윗칸이 LCA다
        for (int i = height; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }

    private static void getSparseTable() {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    private static void getDepth(int start) {
        depth[start] = 1;
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(start);
        while (!bfsQueue.isEmpty()) {
            Integer now = bfsQueue.poll();
            for (Integer next : adjList[now]) {
                // 방문하지 않은 곳만 탐색
                if (depth[next] == 0) {
                    depth[next] = depth[now] + 1;
                    parent[0][next] = now;
                    bfsQueue.add(next);
                }
            }
        }
    }

    private static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        adjList = new ArrayList[N + 1];
        height = getHeight();
        parent = new int[height + 1][N + 1];

        // 인접리스트 만들기
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adjList[num1].add(num2);
            adjList[num2].add(num1);
        }
    }

    private static int getHeight() {
        int logN = 0;
        for (int i = 1; i < N; i *= 2) {
            logN++;
        }
        return logN;
    }
}
