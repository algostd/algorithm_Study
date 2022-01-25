package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1753 (골드5) (Dijkstra)
public class 최단경로 {

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

    static int V, E, start;
    static PriorityQueue<Edge> pq;
    private static int[] dp;
    static ArrayList<Edge>[] adjList;
    static final int MAX = 4000000;

    public static void main(String[] args) throws IOException {
        getInfo();
        dijkstra();
        printAnswer();
    }

    private static void printAnswer() {
        for (int i = 1; i <= V; i++) {
            if (dp[i] == MAX) {
                sb.append("INF");
            } else{
                sb.append(dp[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        pq.add(new Edge(start, 0));
        dp[start] = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (edge.price > dp[edge.dest]) {
                continue;
            }
            // 탐색
            for (Edge next : adjList[edge.dest]) {
                // 갱신
                if(dp[next.dest] > next.price + dp[edge.dest]){
                    dp[next.dest] = next.price + dp[edge.dest];
                    pq.add(new Edge(next.dest, dp[next.dest]));
                }
            }
        }
    }

    private static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        dp = new int[V + 1];
        Arrays.fill(dp, MAX);

        pq = new PriorityQueue<Edge>((a, b) -> {
            return a.price - b.price;
        });

        // 인접리스트 만들기
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            adjList[start].add(new Edge(dest, price));
        }
    }
}
