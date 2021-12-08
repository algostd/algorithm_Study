import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class 점프 {

    private static int[][] map;
    private static long[][] countMap;
    private static int N;

    static int[][] d = {{0, 1}, {1, 0}}; // 오른쪽, 아래쪽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        countMap = new long[N][N];
        countMap[N - 1][N - 1] = 1;
        String[] strArr;
        for (int row = 0; row < N; row++) {
            strArr = br.readLine().split(" ");
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(strArr[col]);
            }
        }

        // dfs 백트래킹
        dfs(0, 0);
        System.out.println(countMap[0][0]);
    }

    public static long dfs(int row, int col) {
        if (countMap[row][col] != 0) {
            return countMap[row][col];
        }

        for (int i = 0; i < 2; i++) {
            int tr = row + d[i][0] * map[row][col];
            int tc = col + d[i][1] * map[row][col];
            if (map[row][col] == 0) {
                break;
            }
            if (tr < N && tc < N && tr >= 0 && tc >= 0){
                countMap[row][col] += dfs(tr, tc);
            }
        }
        return countMap[row][col];
    }
}
