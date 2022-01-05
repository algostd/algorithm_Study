import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 3
		// 1 2 3
		// 4 5 6
		// 4 9 0

		//입력
		int[][] arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		//알고리즘
		int[][][] dp = new int[N][3][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j][0] = Integer.MAX_VALUE;
			}
		}
		dp[0][0][0] = arr[0][0];
		dp[0][0][1] = arr[0][0];
		dp[0][1][0] = arr[0][1];
		dp[0][1][1] = arr[0][1];
		dp[0][2][0] = arr[0][2];
		dp[0][2][1] = arr[0][2];

		for (int i = 0; i < N - 1; i++) {

			//최솟값
			for (int j = 0; j < 3; j++) {
				int num = dp[i][j][0]; // 0,1
				if (j == 0) {
					dp[i + 1][0][0] = Math.min(dp[i + 1][0][0], num + arr[i + 1][0]);
					dp[i + 1][1][0] = Math.min(dp[i + 1][1][0], num + arr[i + 1][1]);
				}
				if (j == 1) {
					dp[i + 1][0][0] = Math.min(dp[i + 1][0][0], num + arr[i + 1][0]);
					dp[i + 1][1][0] = Math.min(dp[i + 1][1][0], num + arr[i + 1][1]);
					dp[i + 1][2][0] = Math.min(dp[i + 1][2][0], num + arr[i + 1][2]);
				}
				if (j == 2) {
					dp[i + 1][1][0] = Math.min(dp[i + 1][1][0], num + arr[i + 1][1]);
					dp[i + 1][2][0] = Math.min(dp[i + 1][2][0], num + arr[i + 1][2]);
				}
			}

			//최댓값
			for (int j = 0; j < 3; j++) {
				int num = dp[i][j][1]; // 0,1
				if (j == 0) {
					dp[i + 1][0][1] = Math.max(dp[i + 1][0][1], num + arr[i + 1][0]);
					dp[i + 1][1][1] = Math.max(dp[i + 1][1][1], num + arr[i + 1][1]);
				}
				if (j == 1) {
					dp[i + 1][0][1] = Math.max(dp[i + 1][0][1], num + arr[i + 1][0]);
					dp[i + 1][1][1] = Math.max(dp[i + 1][1][1], num + arr[i + 1][1]);
					dp[i + 1][2][1] = Math.max(dp[i + 1][2][1], num + arr[i + 1][2]);
				}
				if (j == 2) {
					dp[i + 1][1][1] = Math.max(dp[i + 1][1][1], num + arr[i + 1][1]);
					dp[i + 1][2][1] = Math.max(dp[i + 1][2][1], num + arr[i + 1][2]);
				}
			}
		}

		//출력
		int min = Math.min(Math.min(dp[N-1][0][0], dp[N-1][1][0]), dp[N-1][2][0]);
		int max = Math.max(Math.max(dp[N-1][0][1], dp[N-1][1][1]), dp[N-1][2][1]);

		System.out.println(max + " " + min);
	}
}