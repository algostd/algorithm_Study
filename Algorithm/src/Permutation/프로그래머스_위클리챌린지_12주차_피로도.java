package Algorithm.src.Permutation;

public class 프로그래머스_위클리챌린지_12주차_피로도 {
    private int answer;
    private int K;
    private int[][] dgs;
    private boolean[] visited;
    private int[] list;

    public int solution(int k, int[][] dungeons) {
        answer = 0;
        K = k;
        dgs = dungeons;
        visited = new boolean[dungeons.length];
        list = new int[dungeons.length];

        // 순열 탐색 시작
        dfs(0, k, 0);
        return answer;

    }

    private void dfs(int depth, int k, int count) {
        for (int i = 0; i < dgs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (k >= dgs[i][0]) { // 돌수 있는 던전이면 돌고 count++
                    k -= dgs[i][1];
                    list[depth] = i;
                    dfs(depth + 1, k, count + 1);
                    k += dgs[i][1];
                } else { // 못도는 던전임 count 그대로
                    dfs(depth + 1, k, count);
                }
                visited[i] = false;
            }
        }
        if (count > answer) {
            answer = count;
        }
    }
}
