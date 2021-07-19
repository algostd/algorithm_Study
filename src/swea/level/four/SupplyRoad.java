import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {
    public static int N;
    public static int[][] map;
    public static int[][] supply;
    public static int[] l = {-1, 1, 0, 0};
    public static int[] r = {0, 0, -1, 1};
    public static int min;
    public static Queue<loc> qu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            //입력
            N = Integer.parseInt(br.readLine());
            supply = new int[N + 1][N + 1];
            map = new int[N + 1][N + 1];
            qu = new LinkedList<loc>();

            min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                String[] s = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    supply[i][j] = Integer.parseInt(s[j]);
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = -1;
                }
            }

            bfs(0, 0);
            System.out.println("#" + test_case + " " + min);
        }

        br.close();
    }

    public static void bfs(int cx, int cy) {
        qu.add(new loc(cx, cy, 0));

        while (!qu.isEmpty()) {
            loc cur = qu.poll(); // (0, 0) sum = 0
            cx = cur.x;
            cy = cur.y;

            if (cx == N - 1 && cy == N - 1) {
                if (cur.sum < min) {
                    min = cur.sum;
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + l[i];
                int ny = cy + r[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (map[nx][ny] == -1) {
                        //지금까지 지나온 깊이의 합에 다음 가려는 지점의 깊이를 더한다.
                        qu.add(new loc(nx, ny, cur.sum + supply[nx][ny]));
                        map[nx][ny] = cur.sum + supply[nx][ny];
                    } else if (map[nx][ny] > cur.sum + supply[nx][ny]) {
                        qu.add(new loc(nx, ny, cur.sum + supply[nx][ny]));
                        map[nx][ny] = cur.sum + supply[nx][ny];
                    }
                }
            }

        }

    }
}

class loc {
    public int x;
    public int y;
    public int sum;

    loc (int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}