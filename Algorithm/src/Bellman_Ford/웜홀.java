import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 웜홀 {

    static class Edge {
        int from;
        int to;
        int price;

        public Edge(int from, int to, int price) {
            this.from = from;
            this.to = to;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        String[] strArr;
        for (int tc = 0; tc < T; tc++) {
            strArr = br.readLine().split(" ");
            int N = Integer.parseInt(strArr[0]);
            int M = Integer.parseInt(strArr[1]);
            int W = Integer.parseInt(strArr[2]);

            // 간선 정보 담기
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                strArr = br.readLine().split(" ");
                int from = Integer.parseInt(strArr[0]) - 1;
                int to = Integer.parseInt(strArr[1]) - 1;
                int price = Integer.parseInt(strArr[2]);
                edges.add(new Edge(from, to, price));
                edges.add(new Edge(to, from, price));
            }
            for (int i = 0; i < W; i++) {
                strArr = br.readLine().split(" ");
                int from = Integer.parseInt(strArr[0]) - 1;
                int to = Integer.parseInt(strArr[1]) - 1;
                int price = Integer.parseInt(strArr[2]);
                edges.add(new Edge(from, to, -price));
            }

            // 벨만포드 알고리즘

            int[] minPrice = new int[N];
            Arrays.fill(minPrice, 2500001);
            minPrice[0] = 0;

            // 각노드에서
            boolean check = false;
            outer:
            for (int searchCnt = 0; searchCnt < N; searchCnt++) {
                for (Edge edge : edges) {
                    int from = edge.from;
                    int to = edge.to;
                    int price = edge.price;
                    if (minPrice[to] > minPrice[from] + price) {
                        minPrice[to] = minPrice[from] + price;
                        if (searchCnt == N - 1) {
                            check = true;
                            break outer;
                        }
                    }
                }
            }

            if (check) { // 음의 순환으로 끝난경우
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }
}
