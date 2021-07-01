package programmers;

import java.util.Arrays;

public class 순위 {
	public int solution(int n, int[][] results) {
		int answer = 0;
		int INF = 100;
		int[][] matrix = new int[n][n];

		// 행렬에 값 넣기
		for (int[] arr : matrix) {
			Arrays.fill(arr, INF);
		}
		for (int[] arr : results) {
			matrix[arr[0] - 1][arr[1] - 1] = 1;
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.printf("%3d ", matrix[i][j]);
			}
			System.out.println();
		}

		// 플로이드 와샬로 방문 -> 방문을 통해 정복할 수 있는 값들 채워넣기
		// ex) 2 -> 5를 통해 1,3,4 -> 5가 가능해짐
		for (int via = 0; via < n; via++) { // 거치는 노드(경로)
			for (int depart = 0; depart < n; depart++) { // 시작 노드
				for (int destin = 0; destin < n; destin++) { // 도착 노드
					if (matrix[depart][destin] > matrix[depart][via] + matrix[via][destin]) {
						matrix[depart][destin] = matrix[depart][via] + matrix[via][destin];
					}
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.printf("%3d ", matrix[i][j]);
			}
			System.out.println();
		}

		for (int row = 0; row < n; row++) {
			boolean check = true;
			for (int col = 0; col < n; col++) {
				if (row == col) {
					continue;
				}
				if (matrix[row][col] == INF && matrix[col][row] == INF) {
					check = false;
					break;
				}
			}
			if (check)
				answer++;
		}

		return answer;
	}

	public static void main(String[] args) {
		순위 s = new 순위();
		int[][] computers = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		int answer = s.solution(5, computers);
		System.out.println(answer);
	}
}
