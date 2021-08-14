import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int V, E;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        List<Edge> list = new ArrayList<Edge>();
        for (int i = 0; i < E; i++) {
            input = br.readLine().split(" ");
            list.add(new Edge(
                    Integer.parseInt(input[0]),
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2])));
        }

        //리스트를 val을 기준으로 오름차순 정렬
        Collections.sort(list, new Comparator<Edge>() {
            public int compare(Edge o1, Edge o2) {
                if(o1.getVal() > o2.getVal()) {
                    return 1;
                } else if (o1.getVal() < o2.getVal()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        //각 정점이 포함된 그래프가 어디인지 저장
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        Main m = new Main();
        //모든 간선에 대해 차례로 수행
        int sum = 0;
        for (int i = 0; i < E; i++) {
            //사이클이 발생하지 않는 경우 그래프에 포함
            if(!m.findParent(parent, list.get(i).getA() - 1, list.get(i).getB() - 1)) {
                sum += list.get(i).getVal();
                m.unionParent(parent, list.get(i).getA() - 1, list.get(i).getB() - 1);
            }
        }

        System.out.println(sum);
    }

    //부모 노드를 찾는 함수
    private int getParent(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = getParent(parent, parent[x]);
        return parent[x];
    }

    //두 부모 노드를 합치는 함수
    private void unionParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    //같은 부모를 가지는지 확인
    private boolean findParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a == b) {
            return true;
        } else {
            return false;
        }
    }


}

//간선 클래스 선언
class Edge {
    int a;
    int b;
    int val;

    Edge(int a, int b, int val) {
        this.a = a;
        this.b = b;
        this.val = val;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getVal() {
        return val;
    }
}
