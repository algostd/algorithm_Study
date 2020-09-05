import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 404ms
 *
 */
public class 욕심쟁이판다 {

	static int[][] map, memo;
	static int K = 0, N;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new int[N][N];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j] = -1;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (memo[i][j] == -1) {
					K = Math.max(K, search(i, j));
				}
			}
		}

		System.out.println(K);
	}

	private static int search(int r, int c) {
		if (memo[r][c] != -1) {
			return memo[r][c];
		}
		
		int K = 1;
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if (0 <= nr && nr < N && 0 <= nc && nc < N && map[r][c] < map[nr][nc]) {
				K = Math.max(K, search(nr, nc) + 1);
			}
		}
		return memo[r][c] = K;
	}
}