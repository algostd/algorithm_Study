import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final int INF = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);

		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = INF;
			}
		}

		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int node1 = Integer.parseInt(input[0]) - 1;
			int node2 = Integer.parseInt(input[1]) - 1;
			dp[node1][node2] = 0;
		}

		//플로이드 와샬
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dp[i][k] != INF && dp[k][j] != INF && dp[i][j] > dp[i][k] + dp[k][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}

		int S = Integer.parseInt(br.readLine());
		for (int i = 0; i < S; i++) {
			input = br.readLine().split(" ");
			int node1 = Integer.parseInt(input[0]) - 1;
			int node2 = Integer.parseInt(input[1]) - 1;

			//결과 출력
			if (dp[node1][node2] != INF) {
				System.out.println(-1);
			} else if (dp[node2][node1] != INF) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}
}