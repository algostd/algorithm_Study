import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class 정수삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        String[] strArr;
        int[][] triangle = new int[N][N];
        for (int i = 0; i < N; i++) {
            strArr = br.readLine().split(" ");
            for (int j = 0; j < strArr.length; j++) {
                triangle[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        // 알고리즘
        for (int i = N - 2; i >=0; i--) {
            // DP
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j+1]);
            }
        }
        int answer = triangle[0][0];
        System.out.println(answer);
    }
}
