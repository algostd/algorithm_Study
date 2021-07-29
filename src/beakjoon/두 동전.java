import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, x1, y1, x2, y2, cnt;
    private static int[][] arr;
    private static Queue<xy> qu;
    private static int[] l = {-1,1,0,0};
    private static int[] r = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        //지나갈 수 있는 곳은 0, 벽은 1로
        arr = new int[N][M];
        x1 = -1;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                if (input[j].equals(".")) {
                    arr[i][j] = 0;
                } else if (input[j].equals("#")) {
                    arr[i][j] = 1;
                } else if (input[j].equals("o")) {
                    arr[i][j] = 0;
                    if (x1 == -1) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }

        cnt = 0;
        qu = new LinkedList<xy>();
        xy start = new xy(x1, y1, x2, y2);
        qu.add(start);
        Main main = new Main();
        main.bfs(x1, y1, x2, y2);

        if (cnt > 10) {
            cnt = -1;
        }
        System.out.println(cnt);
    }

    private void bfs(int x1, int y1, int x2, int y2) {

        while(!qu.isEmpty()) {
            if (cnt > 10) {
                cnt = -1;
                return;
            }

            int size = qu.size();

            for (int i = 0; i < size; i++) {
                xy cur = qu.poll();
                int cx1 = cur.x1;
                int cy1 = cur.y1;
                int cx2 = cur.x2;
                int cy2 = cur.y2;

                // 상하좌우
                for (int k = 0; k < 4; k++) {
                    int nx1 = cx1 + l[k];
                    int ny1 = cy1 + r[k];
                    int nx2 = cx2 + l[k];
                    int ny2 = cy2 + r[k];

                    //낭떠러지일 경우
                    //개수를 세어본다.
                    int cliff = 0;
                    if (nx1 >= N || nx1 < 0 || ny1 >= M || ny1 < 0) {
                        cliff++;
                    }
                    if (nx2 >= N || nx2 < 0 || ny2 >= M || ny2 < 0) {
                        cliff++;
                    }
                    if (cliff == 2) {
                        continue;
                    } else if (cliff == 1) {
                        //정답
                        cnt++;
                        return;
                    }

                    //벽일 경우
                    //앞으로 나아가지 않는다.
                    if (arr[nx1][ny1] == 1) {
                        nx1 = cx1;
                        ny1 = cy1;
                    }
                    if (arr[nx2][ny2] == 1) {
                        nx2 = cx2;
                        ny2 = cy2;
                    }

                    qu.add(new xy(nx1, ny1, nx2, ny2));
                }
            }
            cnt++;
        }
    }

}

class xy {
    int x1;
    int y1;
    int x2;
    int y2;

    xy(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}