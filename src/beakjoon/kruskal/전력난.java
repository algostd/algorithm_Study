import java.io.*;
import java.util.*;

public class Main {
    private static int M, N;
    private static int[] parent;
    private static PriorityQueue<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            N = Integer.parseInt(input[1]);

            if (M == 0 && N == 0) {
                break;
            }

            // 7 11
            // 0 1 7
            // 0 3 5
            // 1 2 8
            // 1 3 9
            // 1 4 7
            // 2 4 5

            int sum = 0;
            edgeList = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                int dist = Integer.parseInt(input[2]);
                edgeList.add(new Edge(x, y, dist));
                sum += dist;
            }

            //크루스칼
            parent = new int[M];
            for (int i = 0; i < M; i++) {
                parent[i] = i;
            }

            int distSum = 0;
            int edgeCnt = 0;
            int len = edgeList.size();
            for (int i = 0; i < len; i++) {
                Edge edge = edgeList.poll();
                int x = edge.x;
                int y = edge.y;
                int dist = edge.dist;

                if (find(x) != find(y)) {
                    union(x, y);
                    edgeCnt++;
                    distSum += dist;
                }
            }

            System.out.println(sum - distSum);
        }

    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

}

class Edge implements Comparable<Edge> {
    int x;
    int y;
    int dist;

    Edge(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
        return this.dist - o.dist;
    }
}