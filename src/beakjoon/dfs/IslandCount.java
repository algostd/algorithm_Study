import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static int w, h, cnt;
    static int[][] map, visited;
    static int[] l = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] r = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w];
            visited = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            cnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && visited[i][j] == 0) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }

            bw.write(cnt + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    private static void dfs(int cx, int cy) {
        visited[cx][cy] = 1;

        for (int i = 0; i < 9; i++) {
            int nx = cx + l[i];
            int ny = cy + r[i];
            if (nx >= 0 && nx < h && ny >= 0 && ny < w && visited[nx][ny] == 0 && map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }



}