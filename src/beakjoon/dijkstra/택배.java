import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] curDist, answer;
    private static List<ArrayList<Edge>> adjList;
    private static int INF = 1000000;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adjList = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int num1 = Integer.parseInt(input[0])-1;
            int num2 = Integer.parseInt(input[1])-1;
            int dist = Integer.parseInt(input[2]);
            adjList.get(num1).add(new Edge(num2, dist));
            adjList.get(num2).add(new Edge(num1, dist));
        }

        for (int start = 0; start < N; start++) {
            PriorityQueue<Edge> qu = new PriorityQueue<Edge>();
            qu.add(new Edge(start, 0));

            curDist = new int[N];
            answer = new int[N];
            for (int i = 0; i < N; i++) {
                curDist[i] = INF;
                answer[i] = -1;
            }
            curDist[start] = 0;

            while (!qu.isEmpty()) {
                //newEdge 경유노드
                Edge edge = qu.poll(); //거리가 가장 적은 노드 꺼내기
                int dest = edge.getDest();
                int val = edge.getVal();

                ArrayList<Edge> edgeList = adjList.get(dest);
                for (Edge newEdge : edgeList) {
                    int finalDest = newEdge.getDest();
                    int newVal = newEdge.getVal();

                    if (val + newVal < curDist[finalDest]) {
                        curDist[finalDest] = val + newVal;
                        qu.add(new Edge(finalDest, curDist[finalDest]));

                        if (answer[dest] != -1) {
                            answer[finalDest] = answer[dest];
                        } else {
                            answer[finalDest] = finalDest + 1;
                        }
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                if (answer[i] == -1) {
                    System.out.print("- ");
                } else {
                    System.out.print(answer[i] + " ");
                }
            }
            System.out.println();
        }
    }
}

class Edge implements Comparable<Edge>{
    private int dest;
    private int val;

    Edge(int dest, int val) {
        this.dest = dest;
        this.val = val;
    }

    public int getDest() {
        return dest;
    }

    public int getVal() {
        return val;
    }

    public int compareTo(Edge o) {
        return this.val - o.val;
    }
}
