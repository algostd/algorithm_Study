import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, max;
    private static int[][] l = {{0,-1},{-1,0},{-1,0},{0,1}};
    private static int[][] r = {{1,0},{0,-1},{0,1},{1,0}};
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        //알고리즘
        max = 0;
        Main m = new Main();
        m.recur(0, new int[N][M], 0, 0);

        System.out.println(max);
    }

    //0, 1, 2, 3
    private void recur(int sum, int[][] visited, int x, int y) {
        if (x == N) {
            if (sum > max) {
                max = sum;
            }
            return;
        }

        if (visited[x][y] == 1) {
            if (y != M-1) {
                recur(sum, visited, x, y+1);
            } else {
                recur(sum, visited, x+1, 0);
            }

            return;
        }

        //(x,y)는 부메랑의 중심부분
        for (int i = 0; i < 4; i++) {
            visited[x][y] = 1;
            int x1 = x + l[i][0];
            int y1 = y + l[i][1];
            int x2 = x + r[i][0];
            int y2 = y + r[i][1];

            if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < M && x2 >= 0 && x2 < N && y2 >= 0 && y2 < M && visited[x1][y1] == 0 && visited[x2][y2] == 0) {
                visited[x1][y1] = 1;
                visited[x2][y2] = 1;

                if (y != M-1) {
                    recur(sum + arr[x1][y1] + arr[x2][y2] + (arr[x][y]*2), visited, x, y+1);
                } else {
                    recur(sum + arr[x1][y1] + arr[x2][y2] + (arr[x][y]*2), visited, x+1, 0);
                }

                visited[x1][y1] = 0;
                visited[x2][y2] = 0;
            }
        }

        //그 지점을 부메랑으로 활용안하고 넘어갈때
        visited[x][y] = 0;
        if (y != M-1) {
            recur(sum, visited, x, y+1);
        } else {
            recur(sum, visited, x+1, 0);
        }
    }
}