import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class 계단오르기 {
    /*
    선형 탐색을 진행한다.
    [0] = 10
    [1] = 30
    [2] = Math(arr[0], arr[1]) + arr[2]
    [3부터] = MAX(이이이전 노드의 최대값 + 이전값, 이이전 노드 최대값)
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] max = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // DP 알고리즘
        int answer = 0;
        if (N == 1) {
            answer += arr[0];
        } else if (N == 2) {
            answer += arr[0];
            answer += arr[1];
        } else {
            max[0] = arr[0];
            max[1] = arr[0] + arr[1];
            max[2] = Math.max(arr[0], arr[1]) + arr[2];
            for (int i = 3; i < N; i++) {
                max[i] += Math.max(max[i - 2], max[i - 3] + arr[i - 1]) + arr[i];
            }
            answer = max[N - 1];
        }
        for (int i : max) {
            System.out.print(i + " ");
        }
        System.out.println(answer);
    }
}
