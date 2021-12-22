import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static long[] dp;
	private static int N, M, W;
	private static List<Edge> edges;

	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < TC; test_case++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			W = Integer.parseInt(input[2]);

			dp = new long[N];
			for (int i = 0; i < N; i++) {
				dp[i] = INF;
			}

			edges = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				input = br.readLine().split(" ");
				int node1 = Integer.parseInt(input[0]) - 1;
				int node2 = Integer.parseInt(input[1]) - 1;
				int time = Integer.parseInt(input[2]);
				edges.add(new Edge(node1, node2, time));
				edges.add(new Edge(node2, node1, time));
			}

			for (int i = 0; i < W; i++) {
				input = br.readLine().split(" ");
				edges.add(new Edge(
					Integer.parseInt(input[0]) - 1,
					Integer.parseInt(input[1]) - 1,
					(-1) * Integer.parseInt(input[2])));
			}

			boolean negetive_cycle = bf(0);

			if (negetive_cycle) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}
	}

	private static boolean bf(int start) {
		dp[start] = 0;
		for (int i = 0; i < N; i++) {
			for (Edge edge : edges) {
				int cur = edge.node1;
				int nextNode = edge.node2;
				int time = edge.time;

				if (dp[nextNode] > dp[cur] + time) {
					dp[nextNode] = dp[cur] + time;
					if (i == N - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

}

class Edge {
	int node1;
	int node2;
	int time;

	Edge(int node1, int node2, int time) {
		this.node1 = node1;
		this.node2 = node2;
		this.time = time;
	}
}
