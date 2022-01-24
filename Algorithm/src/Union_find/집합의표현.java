package Union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1717 (골드4) (Union-Find)
public class 집합의표현 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] parent;
    static int N;
    static int M;


    public static void main(String[] args) throws IOException {
        getInfo();

        // 명령어 실행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String token = st.nextToken();
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if (token.equals("0")) {
                merge(num1, num2);
            } else {
                checkAndPrintSameGraph(num1, num2);
            }
        }
        System.out.println(sb);
    }

    private static void merge(int num1, int num2) {
        if (checkSameGraph(num1, num2)) {
            return;
        }
        int parent1 = find(num1);
        int parent2 = find(num2);
        union(parent1, parent2);
    }

    private static void checkAndPrintSameGraph(int num1, int num2) {
        if (checkSameGraph(num1, num2)) {
            sb.append("YES");
        } else {
            sb.append("NO");
        }
        sb.append("\n");
    }
    private static boolean checkSameGraph(int num1, int num2 ) {
        if (find(num1) == find(num2)) {
            return true;
        }
        return false;
    }

    private static void getInfo() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int child) {
        if (child == parent[child]) {
            return child;
        }
        return parent[child] = find(parent[child]);
    }

    private static void union(int parent1, int parent2) {
        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
}
