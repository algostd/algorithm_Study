package Algorithm.src.Floyd_warshall;

import java.util.Arrays;

public class ���� {
	public int solution(int n, int[][] results) {
		int answer = 0;
		int INF = 100;
		int[][] matrix = new int[n][n];

		// ��Ŀ� �� �ֱ�
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

		// �÷��̵� �ͼ��� �湮 -> �湮�� ���� ������ �� �ִ� ���� ä���ֱ�
		// ex) 2 -> 5�� ���� 1,3,4 -> 5�� ��������
		for (int via = 0; via < n; via++) { // ��ġ�� ���(���)
			for (int depart = 0; depart < n; depart++) { // ���� ���
				for (int destin = 0; destin < n; destin++) { // ���� ���
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
		���� s = new ����();
		int[][] computers = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		int answer = s.solution(5, computers);
		System.out.println(answer);
	}
}
