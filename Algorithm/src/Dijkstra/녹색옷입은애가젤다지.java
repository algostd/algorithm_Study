package Algorithm.src.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 녹색옷입은애가젤다지 {

    static class Pair {
        int row;
        int col;
        int price;

        public Pair(int row, int col, int price) {
            this.row = row;
            this.col = col;
            this.price = price;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            String[] strArr;
            int[][] map = new int[N][N];
            int[][] minPrice = new int[N][N];

            for (int row = 0; row < N; row++) {
                strArr = br.readLine().split(" ");
                Arrays.fill(minPrice[row], 100000);
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(strArr[col]);
                }
            }

            // BFS
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> {
                return a.price - b.price;
            });
            pq.add(new Pair(0, 0, map[0][0]));
            minPrice[0][0] = map[0][0];

            while (!pq.isEmpty()) {
                Pair pair = pq.poll();
                if (pair.row == N - 1 && pair.col == N - 1) {
                    break;
                }
                // 4가지 방향
                for (int i = 0; i < 4; i++) {
                    int tr = pair.row + dr[i];
                    int tc = pair.col + dc[i];

                    if (tr >= 0 && tc >= 0 && tr < N && tc < N
                            && minPrice[tr][tc] > minPrice[pair.row][pair.col] + map[tr][tc]) {
                        minPrice[tr][tc] = pair.price + map[tr][tc];
                        pq.add(new Pair(tr, tc, minPrice[tr][tc]));
                    }
                }
            }

            sb.append("Problem " + testCase++ + ": ").append(minPrice[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }
}
