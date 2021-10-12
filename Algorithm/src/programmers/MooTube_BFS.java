import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// BFS 풀이버전
public class MooTube_BFS {

    static final int MAX = 1000000001;

    static class Edge {
        int dest;
        long price;
        int min;

        public Edge(int dest, long price) {
            this.dest = dest;
            this.price = price;
            min = MAX;
        }

        public Edge(int dest, long price, int min) {
            this.dest = dest;
            this.price = price;
            this.min = min;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 인접리스트 생성
        ArrayList<Edge>[] adjList = new ArrayList[N];
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

        int[][] answer = new int[N][N];
        Queue<Edge> pq = new LinkedList<>();
        for (int start = 0; start < N; start++) {

            // bfs
            boolean[] visited = new boolean[N];
            visited[start] = true;
            Edge startEdge = new Edge(start, 0);
            pq.add(startEdge);

            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                for (Edge nextEdge : adjList[edge.dest]) { // 다음 노드로
                    if(!visited[nextEdge.dest]){
                        int min = (int) Math.min(edge.min, nextEdge.price);
                        pq.add(new Edge(nextEdge.dest, nextEdge.price, min));
                        answer[start][nextEdge.dest] = min;
                        visited[nextEdge.dest] = true;
                    }
                }
            }
        }

        // 정답출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;

            int count = 0;
            for (int videos : answer[v]) {
                if(videos >= k){
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);

        for (int[] ints : answer) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
