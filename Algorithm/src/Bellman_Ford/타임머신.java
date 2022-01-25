package Bellman_Ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/11657 (골드4) (벨만포드)
public class 타임머신 {

    static class Edge{
        int from;
        int to;
        long price;

        public Edge(int from, int to, long price) {
            this.from = from;
            this.to = to;
            this.price = price;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    private static long[] minPrice;
    private static ArrayList<Edge> edges;
    private static final long MAX = (long) 1e9;
    private static boolean check;

    public static void main(String[] args) throws IOException {
        getInfo();
        bellmanFord();
        printAnswer();
    }

    private static void printAnswer() {
        if (check) {
            System.out.println(-1);
        } else{
            for (int i = 2; i <= N; i++) {
                if (minPrice[i] == MAX) {
                    sb.append(-1);
                } else {
                    sb.append(minPrice[i]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    private static void bellmanFord() {
        check = false;
        for (int i = 0; i < N; i++) {
            for (Edge edge : edges) {
                if (minPrice[edge.from] == MAX) {
                    continue;
                }
                if (minPrice[edge.to] > minPrice[edge.from] + edge.price) {
                    minPrice[edge.to] = minPrice[edge.from] + edge.price;
                    if (i == N - 1) { // 음의 순환이 있으면
                        check = true;
                        break;
                    }
                }
            }
        }
    }

    public static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        minPrice = new long[N + 1];
        for (int i = 2; i <= N; i++) {
            minPrice[i] = MAX;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long price = Long.parseLong(st.nextToken());
            edges.add(new Edge(from, to, price));
        }

    }
}

