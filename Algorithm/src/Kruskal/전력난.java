import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class 전력난 {
    private static int[] parent;

    static class Edge {
        int home1;
        int home2;
        int price;

        public Edge(int home1, int home2, int price) {
            this.home1 = home1;
            this.home2 = home2;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder answerSb = new StringBuilder();
        String[] strArr;

        while (true) {
            strArr = br.readLine().split(" ");
            int M = Integer.parseInt(strArr[0]); // 집의 수 (노드)
            int N = Integer.parseInt(strArr[1]); // 길의 수 (엣지)
            if (M == 0 && N == 0) {
                break;
            }
            // 간선 정보 받기
            int home1 = 0;
            int home2 = 0;
            int price = 0;
            int totalPrice = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> {
                return a.price - b.price;
            });
            for (int i = 0; i < N; i++) {
                strArr = br.readLine().split(" ");
                home1 = Integer.parseInt(strArr[0]);
                home2 = Integer.parseInt(strArr[1]);
                price = Integer.parseInt(strArr[2]);
                pq.add(new Edge(home1, home2, price));
                totalPrice += price;
            }

            // 크루스칼
            // Union-find 를 위한 배열 만들기
            parent = new int[M];
            for (int i = 0; i < M; i++) { // 본인의 부모는 일단 본인
                parent[i] = i;
            }
            int count = 0;
            int minPrice = 0;
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (find(edge.home1) != find(edge.home2)) {
                    union(edge.home1, edge.home2);
                    minPrice += edge.price;
                }
                if (count == M - 1) { // 끝내고 정답 카운트 하도록
                    break;
                }
            }

            int answer = totalPrice - minPrice;
            answerSb.append(answer).append("\n");
        }
        System.out.println(answerSb);
    }

    public static int find(int home) {
        if (home == parent[home]) {
            return home;
        }

        return parent[home] = find(parent[home]);
    }

    public static void union(int home1, int home2) {
        int parent1 = find(home1);
        int parent2 = find(home2);

        if (parent1 == parent2) {
            return;
        } else if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
}
