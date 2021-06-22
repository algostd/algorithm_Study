class Solution {
    static int[][] visited;
    static int n, cnt;
    static int[][] computers;

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new int[n][n];
        Solution.computers = computers;
        Solution.n = n;
        cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //두 노드가 연결되어있고 아직 방문하지 않았을경우
                if (computers[i][j] == 1 && visited[i][j] == 0) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        answer = cnt;
        return answer;
    }

    public static void dfs(int ax, int ay) {
        //아직 방문하지 않았을 때
        if (visited[ax][ay] == 0) {
            visited[ax][ay] = 1;
            visited[ay][ax] = 1;

            for (int j = 0; j < n; j++) {
                if (visited[ay][j] == 0 && computers[ay][j] == 1) {
                    dfs(ay, j);
                }
            }
        }


    }
}