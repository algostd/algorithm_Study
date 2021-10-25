import java.util.*;

public class Solution {
    private static int max, N, K;
    private static int[] arr;
    private static int[][] dungeons_arr;

    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        //유저의 현재 피로도 k
        N = dungeons.length;
        K = k;
        max = 0;
        arr = new int[N];
        dungeons_arr = dungeons;

        //알고리즘
        int[] visited = new int[N];
        recur(0, visited);

        answer = max;
        return answer;
    }

    //모든 순열을 구한다.
    private void recur(int idx, int[] visited) {
        if (idx == N) {
            //주어진 순열대로 탐색한다.
            int tmp = K;
            int cnt = 0;
            for (int num : arr) {
                int num1 = dungeons_arr[num][0];
                int num2 = dungeons_arr[num][1];
                if (tmp >= num1) {
                    tmp -= num2;
                    cnt++;
                } else {
                    break;
                }
            }
            max = Math.max(cnt, max);

            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                arr[idx] = i;
                recur(idx+1, visited);
                visited[i] = 0;
            }
        }
    }

}
