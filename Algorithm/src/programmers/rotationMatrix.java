package programmers;

class rotationMatrix {
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];

		// 맵만들기
		int[][] map = new int[rows][columns];
		int num = 1;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				map[row][col] = num++;
			}
		}

		// 문제 해결 쿼리별
		for (int i = 0; i < queries.length; i++) {
			int row1 = queries[i][0] - 1;
			int col1 = queries[i][1] - 1;
			int row2 = queries[i][2] - 1;
			int col2 = queries[i][3] - 1;

			// 문제 해결 하나의 쿼리 안의 문제 해결
			int min = 10001;

			int memory1 = map[row1][col1];
			int memory2 = map[row1][col2];
			int memory3 = map[row2][col2];
			int memory4 = map[row2][col1];

			// -> 오른쪽으로 가는 블록
			int temp1 = map[row1][col1];
			for (int col = col1; col < col2; col++) {
				if (map[row1][col] < min) {
					min = map[row1][col];
				}
				int temp2 = map[row1][col + 1];
				map[row1][col + 1] = temp1;
				temp1 = temp2;

			}
			for (int j = 0; j < map.length; j++) {
				for (int j2 = 0; j2 < map.length; j2++) {
					System.out.printf("%2d ", map[j][j2]);
				}
				System.out.println();
			}
			System.out.println();

			// 아래쪽 으로 가는블록
			temp1 = map[row1][col2];
			for (int row = row1; row < row2; row++) {
				if (map[row][col2] < min) {
					min = map[row][col2];
				}
				int temp2 = map[row + 1][col2];
				map[row + 1][col2] = temp1;
				temp1 = temp2;
			}
			map[row1 + 1][col2] = memory2;

			for (int j = 0; j < map.length; j++) {
				for (int j2 = 0; j2 < map.length; j2++) {
					System.out.printf("%2d ", map[j][j2]);
				}
				System.out.println();
			}
			System.out.println();

			// <- 왼쪽으로 가는 블록
			temp1 = map[row2][col2];
			for (int col = col2; col > col1; col--) {
				if (map[row2][col] < min) {
					min = map[row2][col];
				}
				int temp2 = map[row2][col - 1];
				map[row2][col - 1] = temp1;
				temp1 = temp2;
			}
			map[row2][col2 - 1] = memory3;

			for (int j = 0; j < map.length; j++) {
				for (int j2 = 0; j2 < map.length; j2++) {
					System.out.printf("%2d ", map[j][j2]);
				}
				System.out.println();
			}
			System.out.println();

			// 위쪽 으로 가는블록
			temp1 = map[row2][col1];
			for (int row = row2; row > row1; row--) {
				if (map[row][col1] < min) {
					min = map[row][col1];
				}
				int temp2 = map[row - 1][col1];
				map[row - 1][col1] = temp1;
				temp1 = temp2;
			}
			map[row2 - 1][col1] = memory4;

			for (int j = 0; j < map.length; j++) {
				for (int j2 = 0; j2 < map.length; j2++) {
					System.out.printf("%2d ", map[j][j2]);
				}
				System.out.println();
			}
			System.out.println();
			answer[i] = min;
			System.out.println("zzzzzzzzzzz");
		}

		return answer;

	}

	public static void main(String[] args) {
		rotationMatrix r = new rotationMatrix();
		int[][] query = { { 1, 1, 3, 3 }, { 2, 2, 4, 5 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		int[] sol = r.solution(6, 6, query);
		for (int i = 0; i < sol.length; i++) {
			System.out.println(sol[i]);
		}
	}
}