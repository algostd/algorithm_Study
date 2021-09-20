import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T;
    private static int[] dp, arr, visited;
    private static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {

            //입력
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]); //건물의 개수
            int K = Integer.parseInt(input[1]);

            dp = new int[N+1];
            arr = new int[N+1];
            visited = new int[N+1];
            input = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(input[i-1]);
            }

            list = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= N; i++) {
                list.add(new ArrayList<Integer>());
            }

            //단방향리스트
            for (int i = 0; i < K; i++) {
                input = br.readLine().split(" ");
                int num1 = Integer.parseInt(input[0]); //건물의 개수
                int num2 = Integer.parseInt(input[1]);

                list.get(num2).add(num1);
            }

            //알고리즘
            int node = Integer.parseInt(br.readLine());
            Main m = new Main();
            m.dfs(node);

            System.out.println(dp[node]);
        }
    }

    private void dfs(int n) {
        visited[n] = 1; //방문처리

        //자식노드리스트
        List<Integer> curList = list.get(n);

        int max = 0;
        for (int num : curList) {
            //방문안한 노드를 대상으로
            if (visited[num] == 0) {
                dfs(num);
            }
            max = Math.max(dp[num], max);
        }
        dp[n] += max;
        dp[n] += arr[n];
    }
}
