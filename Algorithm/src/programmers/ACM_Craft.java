import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ACM_Craft {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            String[] strArr = br.readLine().split(" ");
            int N = Integer.parseInt(strArr[0]);
            int K = Integer.parseInt(strArr[1]);
            int[] dp = new int[N + 1];
            ArrayList<Integer>[] rules = new ArrayList[N + 1]; // 4, 2-3이 만족되야함
            ArrayList<Integer>[] going = new ArrayList[N + 1]; // 1 -> 2,3 으로 갈 수 있음
            boolean[] visited = new boolean[N + 1];
            for (int i = 1; i < N + 1; i++) {
                rules[i] = new ArrayList<>();
                going[i] = new ArrayList<>();
            }
            // 건물비용 초기값 대입
            strArr = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                dp[i] = Integer.parseInt(strArr[i - 1]);
            }
            // K개의 규칙 적용
            for (int i = 0; i < K; i++) {
                strArr = br.readLine().split(" ");
                int start = Integer.parseInt(strArr[0]);
                int dest = Integer.parseInt(strArr[1]);
                rules[dest].add(start);
                going[start].add(dest);
            }
            // 루트 노드 찾기
            ArrayList<Integer> initArr = new ArrayList<>();
            for (int i = 1; i < N+1; i++) {
                if(rules[i].isEmpty()){
                    initArr.add(i);
                }
            }
            // W
            int W = Integer.parseInt(br.readLine());

            // 알고리즘 시작(TopDown)
            for (Integer init : initArr) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(init);
                while (!queue.isEmpty()) {
                    int now = queue.poll();
                    if(visited[now]){
                        continue;
                    }
                    visited[now] = true;
                    outer:
                    for (Integer next : going[now]) {
                        for (Integer before : rules[next]) {
                            if (!visited[before]) { // 다른놈이 올때까지 기다려야됨
                                continue outer;
                            }
                        }
                        // 준비됐으면
                        int max = 0;
                        for (Integer before : rules[next]) {
                            if (max < dp[before]) {
                                max = dp[before];
                            }
                        }

                        if (now != W) {
                            dp[next] += max;
                            queue.add(next);
                        }

                    }
                }
            }
            sb.append(dp[W] + "\n");
        }
        System.out.println(sb);
    }
}
