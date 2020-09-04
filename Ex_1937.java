package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex_1937 {
	private static int[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[][] dp;

	// 욕심쟁이 판다 (골드3) dp, dfs
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 맵 만들기
		map = new int[N][N];
		dp = new int[N][N];
		for (int row = 0; row < map.length; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < map[0].length; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// max는 하나씩 들어갔을 때 최대 값을 찾는 값
		int max = 1;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				int temp = dfs(row, col);
				max = max < temp ? temp : max;
			}
		}
		System.out.println(max);
	}

	private static int dfs(int row, int col) {
		if (dp[row][col] != 0) { // dp는 visited의 역할도 하고, 갱신되면 들어갔던 노드의 최대 방문가능 노드의 수가 저장되어 있음
			return dp[row][col]; // 들어온 자리의 dp가 0이 아니면, dp값 반환 
		}
		dp[row][col] = 1;

		int num = map[row][col];
		int maxNV = 0; // 방문하지 않은 좌표로 갔을 때의 최댓값
		int maxV = 0; // 방문한 곳으로 갔을 때의 최댓값
		for (int i = 0; i < dc.length; i++) {
			int tr = row + dr[i];
			int tc = col + dc[i];

			if (tr >= 0 && tc >= 0 && tr < map.length && tc < map[0].length && map[tr][tc] > num) { // 상하좌우 중 큰 수가 있으면 찾아간다. 
				int visitedCnt = dp[tr][tc];
				if (visitedCnt != 0 && visitedCnt > maxV) { // visitedCnt != 0이면 방문한적이 있다는 것. 
					maxV = visitedCnt; // 방문한 곳들 중 최댓값을 찾아 maxV에 저장
				} else {
					int NVisitedCnt = dfs(tr, tc); // 방문하지 않았으면 재귀(dfs)
					if(NVisitedCnt > maxNV) { 
						maxNV = NVisitedCnt; // 방문하지 않았던 곳들 중 최댓값을 찾아 maxNV에 저장
					}
					
				}
			}
		}
		
		if(maxV >= maxNV) { // 방문한 곳중 최댓값, 방문하지 않은 곳중 최댓값을 서로 비교해서 큰수를 +1해서 반환. 1은 자기자신을 셀때.
			dp[row][col] = 1+ maxV;
			return 1 + maxV;
		}else {
			dp[row][col] = 1 + maxNV;
			return 1 + maxNV;
		}		
	}
}
