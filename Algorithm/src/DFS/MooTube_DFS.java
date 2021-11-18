package Algorithm.src.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class MooTube_DFS {

    static final int MAX = 1000000001;
    static boolean[] visited;
    private static ArrayList<Edge>[] adjList;
    private static int k;
    private static int[] answer;

    static class Edge {
        int dest;
        long price;

        public Edge(int dest, long price) {
            this.dest = dest;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 인접리스트 생성
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int dest = Integer.parseInt(st.nextToken()) - 1;
            long price = Long.parseLong(st.nextToken());
            adjList[start].add(new Edge(dest, price));
            adjList[dest].add(new Edge(start, price));
        }
        // 정답출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;

            // dfs
            answer = new int[N];
            visited = new boolean[N];
            visited[v] = true;
            dfs(v, MAX);

            int count = 0;
            for (int videos : answer) {
                if (videos > 0) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int now, int min) {
        for (Edge edge : adjList[now]) {
            if (!visited[edge.dest] && edge.price >= k){ // 방문하지않고 k 이상일 때만 탐색하도록
                int m = (int) Math.min(min, edge.price);
                answer[edge.dest] = m;
                visited[edge.dest] = true;
                dfs(edge.dest, m);
            }
        }
    }
}
