import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N, cnt, tmp;
    static int[][] map;
    static int[][] visited;
    static int[] l = {-1, 1, 0, 0};
    static int[] r = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        cnt = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tmp = 0;
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    dfs(i, j);
                    cnt++;
                    list.add(tmp);
                }
            }
        }
        Collections.sort(list);

        System.out.println(cnt);
        for (int num : list) {
            System.out.println(num);
        }
    }

    private static void dfs(int cx, int cy) {
        visited[cx][cy] = 1;
        tmp++;

        for (int i = 0; i < 4; i++){
            int nx = cx + l[i];
            int ny = cy + r[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0 && map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

}