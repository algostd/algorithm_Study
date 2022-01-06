import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] strArr;

        // 맵 만들기 -> 임시로 min
        int[][] map = new int[N][3];
        for (int row = 0; row < N; row++) {
            strArr = br.readLine().split(" ");
            for (int col = 0; col < 3; col++) {
                map[row][col] = Integer.parseInt(strArr[col]);
            }
        }

        // 최솟값, 최댓값 DP를 이용해서 아래에서부터 구하기
        int[][] max = new int[N][3];
        int[][] min = new int[N][3];

        // 맨 아래값 복사
        for (int i = 0; i < 3; i++) {
            min[N - 1][i] = map[N - 1][i];
            max[N - 1][i] = map[N - 1][i];
        }

        for (int i = N - 1; i > 0; i--) {
            // 아래쪽행 max 값을 바로 위 행 값들에 쌓아준다는 느낌의 DP
            int leftMax = Math.max(max[i][0], max[i][1]);
            int rightMax = Math.max(max[i][1], max[i][2]);
            int allMax = Math.max(leftMax, rightMax);

            max[i - 1][0] = map[i - 1][0] + leftMax;
            max[i - 1][1] = map[i - 1][1] + allMax;
            max[i - 1][2] = map[i - 1][2] + rightMax;

            // 아래쪽행 min 값을 바로 위 행 값들에 쌓아준다는 느낌의 DP
            int leftMin = Math.min(min[i][0], min[i][1]);
            int rightMin = Math.min(min[i][1], min[i][2]);
            int allMin = Math.min(leftMin, rightMin);

            min[i - 1][0] = map[i - 1][0] + leftMin;
            min[i - 1][1] = map[i - 1][1] + allMin;
            min[i - 1][2] = map[i - 1][2] + rightMin;
        }

        // 정답 도출
        sb.append(getMax(max[0][0], max[0][1], max[0][2])).append(" ");
        sb.append(getMin(min[0][0], min[0][1], min[0][2]));
        System.out.println(sb);

    }

    private static int getMax(int a, int b, int c) {
        a = Math.max(a, b);
        return Math.max(a, c);
    }

    private static int getMin(int a, int b, int c) {
        a = Math.min(a, b);
        return Math.min(a, c);
    }
}
