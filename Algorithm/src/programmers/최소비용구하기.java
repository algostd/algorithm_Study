import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소비용구하기 {
    private static int V;
    private static int E;

    static class Edge implements Comparable<Edge> {
        int dest;
        int price;

        public Edge(int dest, int price) {
            this.dest = dest;
            this.price = price;
        }

        @Override
        public int compareTo(Edge o) {
            return this.price - o.price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        String[] strArr;
        // 간선담기
        Vector<Edge>[] graph = new Vector[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new Vector<>();
        }
        for (int i = 0; i < E; i++) {
            strArr = br.readLine().split(" ");
            graph[Integer.parseInt(strArr[0]) - 1]
                    .add(new Edge(Integer.parseInt(strArr[1]) - 1, Integer.parseInt(strArr[2])));
        }
        // Start, End
        strArr = br.readLine().split(" ");
        int start = Integer.parseInt(strArr[0]) - 1;
        int end = Integer.parseInt(strArr[1]) - 1;

        // Dijkstra
        int[] minDist = new int[V];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            int current = pq.peek().dest;
            int distance = pq.peek().price;
            pq.poll();
            // 최단거리가 아닌 경우 스킵
            if (minDist[current] < distance) continue;
            for (int i = 0; i < graph[current].size(); i++) {
                // 선택된 노드의 인접노드
                int next = graph[current].get(i).dest;
                // 선택된 노드를 인접노드로 거쳐서 가는 비용
                int nextDistance = distance + graph[current].get(i).price;
                // 기존의 최소 비용보다 더 저렴하다면 교체
                if (nextDistance < minDist[next]) {
                    minDist[next] = nextDistance;
                    pq.add(new Edge(next, nextDistance));
                }
            }
        }
        System.out.println(minDist[end]);
    }
}
