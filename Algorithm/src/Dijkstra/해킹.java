import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 해킹 {

    static class Edge{
        int dest;
        int price;

        public Edge(int dest, int price) {
            this.dest = dest;
            this.price = price;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String[] strArr;
        int N, D, start;

        for (int testCase = 0; testCase < T; testCase++) {
            strArr = br.readLine().split(" ");
            N = Integer.parseInt(strArr[0]);
            D = Integer.parseInt(strArr[1]);
            start = Integer.parseInt(strArr[2]) - 1;

            // 인접리스트 만들기
            ArrayList<Edge>[] adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<Edge>();
            }
            for (int i = 0; i < D; i++) {
                strArr = br.readLine().split(" ");
                int dest = Integer.parseInt(strArr[0]) - 1;
                int startNode = Integer.parseInt(strArr[1]) - 1;
                int price = Integer.parseInt(strArr[2]);
                adjList[startNode].add(new Edge(dest, price));
            }

            // 다익스트라
            final int MAXIMUM = 1000000001;
            int[] answerList = new int[N];
            Arrays.fill(answerList, MAXIMUM);
            answerList[start] = 0;

            PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->{
                return a.price - b.price;
            });
            pq.add(new Edge(start, 0));
            while (!pq.isEmpty()) {
                Edge nowEdge = pq.poll();
                if (nowEdge.price > answerList[nowEdge.dest]) {
                    continue;
                }
                for (Edge nextEdge : adjList[nowEdge.dest]) {
                    if (answerList[nextEdge.dest] > answerList[nowEdge.dest] + nextEdge.price) {
                        answerList[nextEdge.dest] = answerList[nowEdge.dest] + nextEdge.price;
                        pq.add(new Edge(nextEdge.dest, answerList[nextEdge.dest]));
                    }
                }
            }

            // 정답 출력
            int reverseCount = 0;
            int time = 0;
            for (int minTime : answerList) {
                if (minTime == MAXIMUM) {
                    reverseCount++;
                    continue;
                }
                if (time < minTime) {
                    time = minTime;
                }
            }
            int count = N - reverseCount;
            sb.append(count).append(" ").append(time).append("\n");
        }
        System.out.println(sb);
    }
}
