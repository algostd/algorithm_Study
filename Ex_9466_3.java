package 백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Ex_9466_3 {
	// 텀 프로젝트(골드4)
	private static int[] adj;
	private static boolean[] visited;
	private static boolean[] finished;
	private static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());

			// 인접 리스트
			adj = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int node = 1; node < N + 1; node++) {
				adj[node] = Integer.parseInt(st.nextToken());
			}

			visited = new boolean[N + 1];
			finished = new boolean[N + 1];

			for (int node = 1; node < N + 1; node++) {
				dfs(node);
			}
			// 정답 출력
			bw.write(N - answer + "\n");

			answer = 0;
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int node) {
		if (visited[node])
			return;
		visited[node] = true;
		int nextNode = adj[node];

		if (!visited[nextNode]) {
			dfs(nextNode);
		} else {
			if (!finished[nextNode]) {
				answer++;
				for (int i = nextNode; i != node; i = adj[i]) {
					answer++;
				}
			}
		}
		finished[node] = true;
	}
}
