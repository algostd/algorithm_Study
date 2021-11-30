import java.io.*;
import java.util.*;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int z = Integer.parseInt(input[2]);
            list.add(new Node(i, x, y, z));
        }

        // 5
        // 11 -15 -15
        // 14 -5 -15
        // -1 -1 -5
        // 10 -4 -1
        // 19 -4 19

        //1. x는 x끼리, y는 y끼리, z는 z끼리 인접한 노드간의 거리를 비교해본다. => Class 필요

        PriorityQueue<Edge> edgeList = new PriorityQueue<>();

        //x기준으로 오름차순
        Collections.sort(list, (o1, o2) -> o1.x - o2.x);
        for (int i = 0; i < N - 1; i++) {
            Node node1 = list.get(i);
            Node node2 = list.get(i+1);
            edgeList.add(new Edge(node1.idx, node2.idx, node2.x - node1.x));
        }

        //y기준으로 오름차순
        Collections.sort(list, (o1, o2) -> o1.y - o2.y);
        for (int i = 0; i < N - 1; i++) {
            Node node1 = list.get(i);
            Node node2 = list.get(i+1);
            edgeList.add(new Edge(node1.idx, node2.idx, node2.y - node1.y));
        }

        //x기준으로 오름차순
        Collections.sort(list, (o1, o2) -> o1.z - o2.z);
        for (int i = 0; i < N - 1; i++) {
            Node node1 = list.get(i);
            Node node2 = list.get(i+1);
            edgeList.add(new Edge(node1.idx, node2.idx, node2.z - node1.z));
        }

        //크루스칼 알고리즘
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int cnt = 0;

        Main m = new Main();
        int len = edgeList.size();
        while (cnt < N-1) {
            Edge edge = edgeList.poll();
            if (m.find(edge.idx1) != m.find(edge.idx2)) {
                m.union(edge.idx1, edge.idx2);
                answer += edge.dist;
                cnt++;
            }
        }

        System.out.println(answer);
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    //부모를 더 작은 값으로 설정
    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
}

class Node {
    int idx;
    int x;
    int y;
    int z;

    Node (int idx, int x, int y, int z) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Edge implements Comparable<Edge> {
    int idx1;
    int idx2;
    int dist;

    Edge(int idx1, int idx2, int dist) {
        this.idx1 = idx1;
        this.idx2 = idx2;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
        return this.dist - o.dist;
    }
}