package 백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ex_9466 {
	// 텀 프로젝트(골드4)
	private static boolean[] visited;
	private static boolean[] finished;
	private static Queue<Integer> queue = new LinkedList<Integer>();
	private static int answer = 0;
	private static int[] adj;

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
				dfs(node, 1);
			}
			// 정답 출력
			bw.write(N - answer + "\n");

			answer = 0;
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int node, int depth) {
		// 방문 검사
		if (visited[node] || finished[node]) {
			queue.clear();
			return;
		}
		
		// 방문 처리
		visited[node] = true;

		// 자기 자신과 cycle인 경우 검사
		if (adj[node] == node) {
			finished[node] = true;
			answer++;
			queue.clear();
			return;
		}
		
		// 배열에 담겨있는 노드들을 확인하면서, 싸이클 확인
		if (!queue.isEmpty() && queue.contains(adj[node])) {
			int temp = queue.poll();
			while (temp != adj[node]) {
				depth--;
				temp = queue.poll();
			}
			answer += depth;
			queue.clear();
			return;
		}
		queue.offer(node);
		dfs(adj[node], depth + 1);
		
		finished[node] = true;
	}
}
