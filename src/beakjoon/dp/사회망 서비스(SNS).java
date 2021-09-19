import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] dp;
    private static int[] visited;
    private static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //친구관계를 표현하는 인접리스트 생성
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] input = br.readLine().split(" ");
            int num1 = Integer.parseInt(input[0]);
            int num2 = Integer.parseInt(input[1]);

            adjList[num2].add(num1);
            adjList[num1].add(num2);
        }

        dp = new int[N+1][2];
        visited = new int[N+1];

        //알고리즘
        Main m = new Main();
        m.dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private void dfs(int node) {
        if (visited[node] != 0) {
            return;
        }
        visited[node] = 1; //방문처리
        dp[node][0] = 0;
        dp[node][1] = 1;

        //node와 연결된 모든 노드들을 탐색한다.
        for (int num : adjList[node]) {
            if (visited[num] == 0) {
                dfs(num);
                dp[node][1] += Math.min(dp[num][0], dp[num][1]);
                dp[node][0] += dp[num][1];
            }
        }
    }
}
