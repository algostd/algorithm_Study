import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 합리적인이동경로 {
    private static int N;
    private static int M;
    private static int answer;
    private static ArrayList<Edge>[] adjList;
    private static int[] minPrice;

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
        String[] strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        M = Integer.parseInt(strArr[1]);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // adjList 만들기
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            strArr = br.readLine().split(" ");
            int start = Integer.parseInt(strArr[0]);
            int dest = Integer.parseInt(strArr[1]);
            int price = Integer.parseInt(strArr[2]);
            adjList[start].add(new Edge(dest, price));
            adjList[dest].add(new Edge(start, price));
        }

        // Dijkstra -> 출발 2번노드 도착 1번노드
        minPrice = new int[N + 1];
        Arrays.fill(minPrice, Integer.MAX_VALUE);
        minPrice[2] = 0;
        int[] dp = new int[N + 1];
        dp[2] = 1;
        pq.add(new Edge(2, 0));
        while (!pq.isEmpty()) {
            Edge nowEdge = pq.poll();
            int current = nowEdge.dest;
            // 최단거리가 아니라면 스킵
            if (minPrice[current] < nowEdge.price) {
                continue;
            }
            // 기존의 최소비용보다 더 저렴하다면 교체합니다.
            for (Edge nextEdge : adjList[current]) {
                int nextDistance = minPrice[current] + nextEdge.price;
                if (minPrice[nextEdge.dest] > nextDistance) {
                    minPrice[nextEdge.dest] = nextDistance;
                    pq.add(new Edge(nextEdge.dest, nextDistance));
                }
                // 교체하지 않아도.. 현재 노드의 값보다 다음노드의 값이 크다면 경로가 있는거니까 count++ (dp)
                if (minPrice[nextEdge.dest] > minPrice[current]){
                    dp[nextEdge.dest] += dp[current];
                }
            }
        }
//        dfs로 합리적인 이동경로 찾기
//        dfs(1); // -> 시간초과
        answer = dp[1];

        System.out.println(answer);

    }

//    private static void dfs(int start) {
//        for (Edge edge : adjList[start]) {
//            int next = edge.dest;
//            if(next == 2){
//                answer++;
//                continue;
//            }
//            if (minPrice[start] > minPrice[next]) { // 더 다음 노드가 2에 더 가까우면
//                dfs(next);
//            }
//        }
//    }
}
