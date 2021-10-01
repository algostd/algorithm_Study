import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class 서강그라운드 {
    static class Edge {
        int dest;
        int price;

        public Edge(int dest, int price) {
            this.price = price;
            this.dest = dest;
        }

    }

    static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String[] strArr;
        strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]); // 노드 개수
        int M = Integer.parseInt(strArr[1]); // 수색범위
        int E = Integer.parseInt(strArr[2]); // 길의ㅏ개수
        int[] items = new int[N + 1];
        // 아이템
        strArr = br.readLine().split(" ");
        ArrayList<Edge>[] arrayList = new ArrayList[N + 1]; // 인접리스트
        for (int i = 1; i <= N; i++) { // 아이탬수
            items[i] = Integer.parseInt(strArr[i - 1]);
            arrayList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) { // 간선 정보
            strArr = br.readLine().split(" ");
            int start = Integer.parseInt(strArr[0]);
            int dest = Integer.parseInt(strArr[1]);
            int price = Integer.parseInt(strArr[2]);
            arrayList[start].add(new Edge(dest, price));
            arrayList[dest].add(new Edge(start, price));
        }

        // 다익스트라 시작
        int[][] minDp = new int[N + 1][N + 1];
        int answer = 0;
        for (int i = 0; i <= N; i++) {
            Arrays.fill(minDp[i], MAX);
            minDp[0][i] = 0;
            minDp[i][i] = 0;
        }

        // 시작
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>(Comparator.comparingInt(a -> a.price));
        for (int start = 1; start <= N; start++) { // (0 -> 시작노드 (price = 0))
            queue.add(new Edge(start, 0));
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                if (minDp[start][edge.dest] < edge.price) { // 갱신필요 x
                    continue;
                }
                for (Edge nextEdge : arrayList[edge.dest]) {
                    int nextPrice = minDp[start][edge.dest] + nextEdge.price;
                    if (nextPrice <= M && minDp[start][nextEdge.dest] > nextPrice) {
                        minDp[start][nextEdge.dest] = nextPrice;
                        queue.add(new Edge(nextEdge.dest, nextPrice));
                    }
                }
            }
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (minDp[start][j] != MAX) {
                    sum += items[j];
                }
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
