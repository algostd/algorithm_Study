import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1197
public class 최소스패닝트리 {

    private static int V;
    private static int E;
    private static long answer;
    private static int[] vertexes;

    static class Edge implements Comparable<Edge> {
        int vtx1;
        int vtx2;
        int price;
        public Edge(int vtx1, int vtx2, int price) {
            this.vtx1 = vtx1;
            this.vtx2 = vtx2;
            this.price = price;
        }

        @Override
        public int compareTo(Edge o) {
            return this.price - o.price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        V = Integer.parseInt(strArr[0]);
        E = Integer.parseInt(strArr[1]);
        answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue();
        for (int i = 0; i < E; i++) {
            strArr = br.readLine().split(" ");
            pq.add(new Edge(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]), Integer.parseInt(strArr[2])));
        }

        // union-find를 위한 배열
        vertexes = new int[V+1];
        for (int i = 1; i <= V; i++) {
            vertexes[i] = i;
        }
        Edge edge = pq.poll();
        vertexes[edge.vtx2] = edge.vtx1;
        answer += edge.price;

        // 크루스칼
        while(!pq.isEmpty()){
            edge = pq.poll();
            int vtx1Parent = find(edge.vtx1);
            int vtx2Parent = find(edge.vtx2);
            // Union
            if(vtx1Parent == vtx2Parent){
                continue;
            } else if(vtx1Parent < vtx2Parent){
                vertexes[vtx2Parent] = vtx1Parent;
            } else {
                vertexes[vtx1Parent] = vtx2Parent;
            }
            answer += edge.price;
        }

        System.out.println(answer);
    }

    private static int find(int vtx){
        if(vertexes[vtx] == vtx){
            return vtx;
        }
        return vertexes[vtx] = find(vertexes[vtx]);
    }
}

