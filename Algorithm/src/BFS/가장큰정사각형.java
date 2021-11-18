package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ����ū���簢�� {

	private class Pair {
		int row;
		int col;
		int stage;

		public Pair(int row, int col, int stage) {
			super();
			this.row = row;
			this.col = col;
			this.stage = stage;
		}

	}

	static int[] checkHowMany;
	static boolean visited[][];

	public int solution(int[][] board) {
		int answer = 0;
		visited = new boolean[board.length][board[0].length];

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				
				for (int i = 0; i < board.length; i++) {
					Arrays.fill(visited[i], false);
				}
				int tempAnswer = -1;
				if (board[row][col] != 0) {
					tempAnswer = findMaxSquare(board, row, col);
				}
				if(answer < tempAnswer) {
					answer = tempAnswer;
				}

			}
		}
		answer = answer * answer;
		return answer;
	}

	int findMaxSquare(int[][] board, int row, int col) {
		// ť�� ������
		Queue<Pair> queue = new LinkedList<Pair>();
				
		// stage�ܰ踦 üũ�ϴ� �迭 checkMany�� 1 3 5 7 9 ...
		checkHowMany = new int[1000];
		for (int i = 0; i < 1000; i++) {
			checkHowMany[i] = (i+1) * 2 -1;
		}
		
		// �湮 & ť�� �ֱ�
		int stage = 0;
		visited[row][col] = true;
		queue.add(new Pair(row, col, stage));
		
		while(!queue.isEmpty()) {
			// ������ Ȯ��
			Pair nowPair = queue.poll();
			checkHowMany[nowPair.stage]--;
			
			// ���� ������ bfs�ϱ� ���� �ڵ�
			int[] dr = {1, 0, 1};
			int[] dc = {0, 1, 1};
			
			for (int i = 0; i < dc.length; i++) {
				int tr = nowPair.row + dr[i];
				int tc = nowPair.col + dc[i];
				if(tr < board.length && tc < board[0].length && !visited[tr][tc] && board[tr][tc] == 1) { // �湮���� �ʾ����� 
					queue.add(new Pair(tr, tc, nowPair.stage + 1));
					visited[tr][tc] = true;
				}
			}
		}
		int tempAnswer = -1;
		for (int i = 0; i < 4; i++) {
			if(checkHowMany[i] == 0) {
				tempAnswer = i + 1;
			} else {
				break;
			}
		}
		
		return tempAnswer;
	}

	public static void main(String[] args) {
		����ū���簢�� s = new ����ū���簢��();
		int[][] board = { { 1, 1, 1, 1 }, { 1, 0, 1, 1 }, { 1, 1, 1, 1 },{ 1, 1, 1, 1 }  };
		int answer = s.solution(board);
		System.out.println(answer);
	}

}
