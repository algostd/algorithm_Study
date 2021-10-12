import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1719
public class 택배 {
    static class Edge {
        int dest;
        int price;

        public Edge(int dest, int price) {
            this.dest = dest;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]);
        int E = Integer.parseInt(strArr[1]);
        // 인접리스트 만들기
        int[][] answer = new int[N + 1][N + 1];
        int[] dp = new int[N + 1];
        ArrayList<Edge>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            strArr = br.readLine().split(" ");
            int start = Integer.parseInt(strArr[0]);
            int dest = Integer.parseInt(strArr[1]);
            int price = Integer.parseInt(strArr[2]);
            adjList[start].add(new Edge( dest, price));
            adjList[dest].add(new Edge( start, price));
        }

        // 다익스트라
        final int MAX = 200001;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> {
            return a.price - b.price;
        });

        for (int start = 1; start <= N; start++) { // 모든 노드가 시작점이 되봄. 다익스트라
            Arrays.fill(dp, MAX);
            dp[start] = 0;
            pq.add(new Edge(start, 0));
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (dp[edge.dest] < edge.price) {
                    continue;
                }
                for (Edge nextEdge : adjList[edge.dest]) {
                    int nextPrice = edge.price + nextEdge.price;
                    if (nextPrice < dp[nextEdge.dest]) {
                        dp[nextEdge.dest] = nextPrice;
                        pq.add(new Edge(nextEdge.dest, nextPrice));
                        if(answer[start][edge.dest] != 0){
                            answer[start][nextEdge.dest] = answer[start][edge.dest];
                        } else{
                            answer[start][nextEdge.dest] = nextEdge.dest;
                        }
                    }
                }
            }
        }
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (row == col) {
                    sb.append("- ");
                } else {
                    sb.append(answer[row][col] + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
