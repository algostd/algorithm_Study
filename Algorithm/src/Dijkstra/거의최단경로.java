package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/5719 (플레5) (Dijkstra 활용)
public class 거의최단경로 {

    static class Node {
        int node;
        int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static int N, M;
    static ArrayList<Node>[] adjList;
    static ArrayList<Integer>[] tracking;
    static boolean[][] isShortest; // isShortest[a][b] = true, a > b 로 가는 간선이 최단거리에 속한다.
    static int[] distance;
    static int start, destination;
    static final int INF = Integer.MAX_VALUE;

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            if (getInfo()) {
                break;
            }

            // 첫번째 다익스트라 실행
            findShortestPath();
            if (distance[destination] == INF) { // 경로가 없는경우
                sb.append(-1).append("\n");
                continue;
            }
            // 백트래킹으로 최단 경로의 방문 엣지들 체크하기
            findShortestEdge(destination, start);

            // 최단 경로의 방문 노드들을 제외하고 다익스트라 (거의최단경로)
            findShortestPath();

            // 정답 도출
            printAnswer();
        }
        System.out.println(sb);
    }

    private static void printAnswer() {
        if (distance[destination] == INF) {
            sb.append(-1);
        } else {
            sb.append(distance[destination]);
        }
        sb.append("\n");
    }

    public static boolean getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N == 0 && M == 0) {
            return true;
        }
        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        distance = new int[N];
        isShortest = new boolean[N][N];

        // 인접리스트 만들기
        tracking = new ArrayList[N];
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<Node>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, distance));
        }
        return false;
    }

    private static void findShortestEdge(int now, int end) {
        if (now == end) {
            return;
        }
        for (Integer next : tracking[now]) {
            if (!isShortest[next][now]) {
                isShortest[next][now] = true;
                findShortestEdge(next, end);
            }
        }
    }

    // 다익스트라 알고리즘
    private static void findShortestPath() {
        for (int i = 0; i < N; i++) {
            tracking[i] = new ArrayList<>();
        }

        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (distance[now.node] < now.distance) {
                continue;
            }
            for (Node next : adjList[now.node]) {
                if (isShortest[now.node][next.node]) {
                    continue;
                }
                if (distance[next.node] > distance[now.node] + next.distance) {
                    distance[next.node] = distance[now.node] + next.distance;
                    tracking[next.node].clear();
                    tracking[next.node].add(now.node);
                    pq.add(new Node(next.node, distance[next.node]));
                }
                if(distance[next.node] == distance[now.node] + next.distance){
                    tracking[next.node].add(now.node);
                }
            }
        }
    }


}