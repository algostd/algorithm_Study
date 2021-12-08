import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백조의호수Fail {
    private static Queue<Pair>  queue;

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int R;
    private static int C;
    private static int[][] map;
    private static boolean[][] visited;
    private static int nodeNum;

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private static int swanNode1 = -1;
    private static int swanNode2 = -1;
    private static int[] parent;

    /*
        1. 물이 있는 곳에서 DFS 탐색으로 각 노드(숫자) 표기 - 다수의 그래프 만들기
        2. 물이 빙판을 녹인다. (BFS)
        3. 녹이고 나서 물끼리 닿았을 때, Union-Find로 각 백조가 속한 물(그래프)가
           닿았는지 확인
           => 안닿았다면 2,3과정의 방복
         */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받고 map, Queue 만들기
        String[] strArr = br.readLine().split(" ");
        R = Integer.parseInt(strArr[0]);
        C = Integer.parseInt(strArr[1]);
        map = new int[R][C];
        visited = new boolean[R][C];

        queue = new LinkedList<>();

        String str;
        for (int row = 0; row < R; row++) {
            str = br.readLine();
            for (int col = 0; col < C; col++) {
                if (str.charAt(col) == 'X') {
                    map[row][col] = -1;
                } else {
                    map[row][col] = str.charAt(col);
                }
            }
        }

        // DFS (단지 번호 붙이기)
        nodeNum = 0;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if ((map[row][col] == 'L' || map[row][col] == '.')
                        && !visited[row][col]) {
                    dfs(row, col);
                    nodeNum++;
                } else{

                }
            }
        }
        // 그래프 형성하기
        int answer = 0;
        parent = new int[nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            parent[i] = i;
        }

        while (true) {
            if (checkAnswer()) {
                break;
            }
            // 길이 1만큼 BFS 물이 빙판 녹이기
            initVisited();
            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                int row = pair.row;
                int col = pair.col;
                if (map[row][col] != -1 && !visited[row][col]) {
                    visited[row][col] = true;
                    // 1칸 이동
                    for (int i = 0; i < 4; i++) {
                        int tr = dr[i] + row;
                        int tc = dc[i] + col;
                        if (tr >= 0 && tc >= 0 && tr < R
                                && tc < C && !visited[tr][tc]
                                && map[tr][tc] == -1) {
                            map[tr][tc] = map[row][col];
                            visited[tr][tc] = true;
                            unionAdj(tr, tc);
                        }
                    }
                }
            }
            answer++;
        }
        System.out.println(answer);
    }

    public static void queueInit(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int tr = dr[i] + row;
            int tc = dc[i] + col;
            if (tr >= 0 && tc >= 0 && tr < R && tc < C
                    && map[tr][tc] != -1
                    ) {

            }
        }
    }
    public static void unionAdj(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int tr = dr[i] + row;
            int tc = dc[i] + col;
            if (tr >= 0 && tc >= 0 && tr < R
                    && tc < C && map[tr][tc] != -1
                    && map[tr][tc] != map[row][col]) {
                int p1 = find(map[row][col]);
                int p2 = find(map[tr][tc]);
                if (p1 != p2) {
                    union(p1, p2);
                }
            }
        }
    }

    public static boolean checkAnswer() {
        if (find(swanNode1) == find(swanNode2)) {
            return true;
        } else {
            return false;
        }
    }

    public static void initVisited() {
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
    }

    public static int find(int node) {
        if (node == parent[node]) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    public static void union(int p1, int p2) {
        if (p1 == p2) {
            return;
        } else if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }

    public static void dfs(int row, int col) {
        visited[row][col] = true;
        if (map[row][col] == 'L') {
            if (swanNode1 == -1) {
                swanNode1 = nodeNum;
            } else {
                swanNode2 = nodeNum;
            }
        }

        map[row][col] = nodeNum;

        for (int i = 0; i < 4; i++) {
            int tr = dr[i] + row;
            int tc = dc[i] + col;
            if (tr >= 0 && tc >= 0 && tr < R && tc < C && !visited[tr][tc] && map[tr][tc] != -1) {
                dfs(tr, tc);
            }
        }
    }
}