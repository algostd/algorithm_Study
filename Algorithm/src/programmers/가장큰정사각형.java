package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 가장큰정사각형 {

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
		// 큐를 만들자
		Queue<Pair> queue = new LinkedList<Pair>();
				
		// stage단계를 체크하는 배열 checkMany에 1 3 5 7 9 ...
		checkHowMany = new int[1000];
		for (int i = 0; i < 1000; i++) {
			checkHowMany[i] = (i+1) * 2 -1;
		}
		
		// 방문 & 큐에 넣기
		int stage = 0;
		visited[row][col] = true;
		queue.add(new Pair(row, col, stage));
		
		while(!queue.isEmpty()) {
			// 꺼내서 확인
			Pair nowPair = queue.poll();
			checkHowMany[nowPair.stage]--;
			
			// 다음 곳으로 bfs하기 위한 코드
			int[] dr = {1, 0, 1};
			int[] dc = {0, 1, 1};
			
			for (int i = 0; i < dc.length; i++) {
				int tr = nowPair.row + dr[i];
				int tc = nowPair.col + dc[i];
				if(tr < board.length && tc < board[0].length && !visited[tr][tc] && board[tr][tc] == 1) { // 방문하지 않았으면 
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
		가장큰정사각형 s = new 가장큰정사각형();
		int[][] board = { { 1, 1, 1, 1 }, { 1, 0, 1, 1 }, { 1, 1, 1, 1 },{ 1, 1, 1, 1 }  };
		int answer = s.solution(board);
		System.out.println(answer);
	}

}
