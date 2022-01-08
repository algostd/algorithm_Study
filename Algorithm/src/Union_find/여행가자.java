import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 여행가자 {

    private static int[] parentArr;

    static class Edge{
        int nodeNum1;
        int nodeNum2;

        public Edge(int nodeNum1, int nodeNum2) {
            this.nodeNum1 = nodeNum1;
            this.nodeNum2 = nodeNum2;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 모든 도시 수
        int M = Integer.parseInt(br.readLine()); // 여행 계획 도시수

        // 연결된 Edge정보를 담는 Edges 만들기
        ArrayList<Edge> edges = new ArrayList<>();
        String[] split;
        for (int row = 0; row < N; row++) {
            split = br.readLine().split(" ");
            for (int col = row + 1; col < N; col++) {
                int connectStatus = Integer.parseInt(split[col]);
                if (col > row && connectStatus == 1) {
                    edges.add(new Edge(row, col));
                }
            }
        }
        // parent 초기화 (자기 자신)
        parentArr = new int[N];
        for (int i = 0; i < N; i++) {
            parentArr[i] = i;
        }

        // Union-Find
        for (Edge edge : edges) {
            union(edge.nodeNum1, edge.nodeNum2);
        }

        // 정답 도출
        split = br.readLine().split(" ");
        String answer = "YES";
        int now = Integer.parseInt(split[0]) - 1;
        for (int i = 1; i < M; i++) {
            int next = Integer.parseInt(split[i]) - 1;
            if (find(now) != find(next)) {
                answer = "NO";
            }
            next = now;
        }

        System.out.println(answer);
    }

    private static int find(int nodeNum) {
        if (nodeNum == parentArr[nodeNum]) {
            return nodeNum;
        }

        return parentArr[nodeNum] = find(parentArr[nodeNum]);
    }

    private static void union(int nodeNum1, int nodeNum2) {
        int parent1 = find(nodeNum1);
        int parent2 = find(nodeNum2);

        if (parent1 == parent2) {
        } else if (parent1 < parent2) {
            parentArr[parent2] = parent1;
        } else {
            parentArr[parent1] = parent2;
        }
    }
}
