package LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/3176 (플레4) (LCA)
public class 도로네트워크 {
    private static int[] depth;

    static class Edge {
        int dest;
        int price;

        public Edge(int dest, int price) {
            this.dest = dest;
            this.price = price;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, Q;
    static int height;
    static ArrayList<Edge>[] adjList;
    static int[][] parent, minPrice, maxPrice;

    public static void main(String[] args) throws IOException {
        getInfo();
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            getAnswer(node1, node2);
        }
        System.out.println(sb);
    }

    private static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            adjList[start].add(new Edge(dest, price));
            adjList[dest].add(new Edge(start, price));

        }
        // 높이 구하기
        height = getHeight();
        depth = new int[N + 1];
        parent = new int[height + 1][N + 1];
        minPrice = new int[height + 1][N + 1];
        maxPrice = new int[height + 1][N + 1];
        getDepth(1);
        getParseTable();
    }

    private static int getHeight() {
        int logN = 0;
        for (int i = 1; i < N; i *= 2) {
            logN++;
        }
        return logN;
    }

    private static void getAnswer(int num1, int num2) {
        if (depth[num1] < depth[num2]) {
            getAnswer(num2, num1);
            return;
        }
        int max = -1;
        int min = Integer.MAX_VALUE;

        // 높이 맞추기
        for (int i = 0; i <= height; i++) {
            if (((depth[num1] - depth[num2]) & (1 << i)) >= 1) {
                min = Math.min(min, minPrice[i][num1]);
                max = Math.max(max, maxPrice[i][num1]);
                num1 = parent[i][num1];
            }
        }

        if (num1 == num2) {
            sb.append(min).append(" ").append(max).append("\n");
            return;
        }
        // 일치하는 부모 구하기
        for (int i = height; i >= 0; i--) {
            if (parent[i][num1] != parent[i][num2]) {
                min = Math.min(min, Math.min(minPrice[i][num1], minPrice[i][num2]));
                max = Math.max(max, Math.max(maxPrice[i][num1], maxPrice[i][num2]));
                num1 = parent[i][num1];
                num2 = parent[i][num2];
            }
        }
        min = Math.min(min, Math.min(minPrice[0][num1], minPrice[0][num2]));
        max = Math.max(max, Math.max(maxPrice[0][num1], maxPrice[0][num2]));
        sb.append(min).append(" ").append(max).append("\n");
    }

    private static void getParseTable() {
        for (int h = 1; h <= height; h++) {
            for (int node = 1; node <= N; node++) {
                parent[h][node] = parent[h - 1][parent[h - 1][node]];
                minPrice[h][node] = Math.min(minPrice[h - 1][node], minPrice[h - 1][parent[h - 1][node]]);
                maxPrice[h][node] = Math.max(maxPrice[h - 1][node], maxPrice[h - 1][parent[h - 1][node]]);
            }
        }
    }

    private static void getDepth(int start) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(start);
        depth[start] = 1;
        while (!bfsQueue.isEmpty()) {
            Integer now = bfsQueue.poll();
            for (Edge next : adjList[now]) {
                if (depth[next.dest] == 0) {
                    depth[next.dest] = depth[now] + 1;
                    parent[0][next.dest] = now;
                    maxPrice[0][next.dest] = next.price;
                    minPrice[0][next.dest] = next.price;
                    bfsQueue.add(next.dest);
                }
            }
        }

    }
}
