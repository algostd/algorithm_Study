package Algorithm.src.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 뮤탈리스크 {

    private static int N;
    private static int answer;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] scvs = new int[3];
        String[] strArr = br.readLine().split(" ");
        int max = 0;
        for (int i = 0; i < N; i++) {
            scvs[i] = Integer.parseInt(strArr[i]);
            max = Math.max(scvs[i], max);
        }
        max += 1;
        visited = new boolean[max][max][max];
        for (int i = N; i < 3; i++) {
            scvs[i] = 0;
        }

        answer = Integer.MAX_VALUE;
        dfs(scvs[0], scvs[1], scvs[2], 0);
        System.out.println(answer);
    }

    private static void dfs(int scv1, int scv2, int scv3, int count) {
        if (scv1 <= 0 && scv2 <= 0 && scv3 <= 0) {
            if (count < answer) {
                answer = count;
            }
            return;
        }

        scv1 = Math.max(0, scv1);
        scv2 = Math.max(0, scv2);
        scv3 = Math.max(0, scv3);
        int max = Math.max(scv1, Math.max(scv2, scv3));
        int min = Math.min(scv1, Math.min(scv2, scv3));
        int mid = scv1 + scv2 + scv3 - max - min;

        if (visited[max][mid][min] || answer <= count) {
            return;
        }
        visited[max][mid][min] = true;

        dfs(max - 9, mid - 3, min - 1, count + 1);
        dfs(max - 9, mid - 1, min - 3, count + 1);
        dfs(max - 3, mid - 9, min - 1, count + 1);
        dfs(max - 3, mid - 1, min - 9, count + 1);
        dfs(max - 1, mid - 9, min - 3, count + 1);
        dfs(max - 1, mid - 3, min - 9, count + 1);
    }


}
