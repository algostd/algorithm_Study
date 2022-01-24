package Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1922 (골드4) (Kruskal) (Union-Find)
public class 네트워크연결 {

    static class Edge{
        int node1;
        int node2;
        int price;

        public Edge(int node1, int node2, int price) {
            this.node1 = node1;
            this.node2 = node2;
            this.price = price;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N;
    static int M;
    static int[] parent;
    static PriorityQueue<Edge> pq;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        getInfo();
        kruskal();
        System.out.println(answer);
    }

    private static void kruskal() {
        int count = 0;
        while (!pq.isEmpty()) {
            if (count == N - 1) {
                break;
            }
            Edge edge = pq.poll();
            int parent1 = find(edge.node1);
            int parent2 = find(edge.node2);
            if (parent1 != parent2) {
                union(parent1, parent2);
                answer += edge.price;
                count++;
            }
        }
    }

    private static void union(int parent1, int parent2) {
        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }

    private static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<Edge>(Comparator.comparingInt(a -> a.price));
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            pq.add(new Edge(node1, node2, price));
        }
    }

    private static int find(int node) {
        if (node == parent[node]) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
}
