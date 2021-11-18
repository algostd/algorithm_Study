package Algorithm.src.Simulation;

public class 키패드누르기 {

	private class Pair {
		int row;
		int col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	public int getDistance(Pair pair1, Pair pair2) {
		int count = 0;
		count += Math.abs(pair1.row - pair2.row);
		count += Math.abs(pair1.col - pair2.col);

		return count;
	}

	public String solution(int[] numbers, String hand) {
		String answer = "";
		int[] leftArr = { 1, 4, 7 };
		int[] rightArr = { 3, 6, 9 };
		Pair nowLeft = new Pair(3, 0);
		Pair nowRight = new Pair(3, 2);

		outer: for (int i = 0; i < numbers.length; i++) {

			// left
			for (int j = 0; j < leftArr.length; j++) {
				if (leftArr[j] == numbers[i]) {
					switch (numbers[i]) {
					case 1:
						nowLeft.row = 0;
						nowLeft.col = 0;
						break;
					case 4:
						nowLeft.row = 1;
						nowLeft.col = 0;
						break;
					case 7:
						nowLeft.row = 2;
						nowLeft.col = 0;
						break;
					default:
						break;
					}
					answer += "L";
					continue outer;
				}
			}

			// right
			for (int j = 0; j < rightArr.length; j++) {
				if (rightArr[j] == numbers[i]) {
					switch (numbers[i]) {
					case 3:
						nowRight.row = 0;
						nowRight.col = 2;
						break;
					case 6:
						nowRight.row = 1;
						nowRight.col = 2;
						break;
					case 9:
						nowRight.row = 2;
						nowRight.col = 2;
						break;
					default:
						break;
					}
					answer += "R";
					continue outer;
				}
			}

			switch (numbers[i]) {
			case 2:
				Pair pair1 = new Pair(0, 1);
				int L = getDistance(pair1, nowLeft);
				int R = getDistance(pair1, nowRight);
				if (L == R) {
					if (hand.equals("right")) {
						nowRight.row = 0;
						nowRight.col = 1;
						answer += "R";
					} else {
						nowLeft.row = 0;
						nowLeft.col = 1;
						answer += "L";
					}
				} else if (L < R) {
					nowLeft.row = 0;
					nowLeft.col = 1;
					answer += "L";
				} else {
					nowRight.row = 0;
					nowRight.col = 1;
					answer += "R";
				}

				break;
			case 5:
				pair1 = new Pair(1, 1);
				L = getDistance(pair1, nowLeft);
				R = getDistance(pair1, nowRight);
				if (L == R) {
					if (hand.equals("right")) {
						nowRight.row = 1;
						nowRight.col = 1;
						answer += "R";
					} else {
						nowLeft.row = 1;
						nowLeft.col = 1;
						answer += "L";
					}
				} else if (L < R) {
					nowLeft.row = 1;
					nowLeft.col = 1;
					answer += "L";
				} else {
					nowRight.row = 1;
					nowRight.col = 1;
					answer += "R";
				}

				break;
			case 8:
				pair1 = new Pair(2, 1);
				L = getDistance(pair1, nowLeft);
				R = getDistance(pair1, nowRight);
				if (L == R) {
					if (hand.equals("right")) {
						nowRight.row = 2;
						nowRight.col = 1;
						answer += "R";
					} else {
						nowLeft.row = 2;
						nowLeft.col = 1;
						answer += "L";
					}
				} else if (L < R) {
					nowLeft.row = 2;
					nowLeft.col = 1;
					answer += "L";
				} else {
					nowRight.row = 2;
					nowRight.col = 1;
					answer += "R";
				}

				break;
			case 0:
				pair1 = new Pair(3, 1);
				L = getDistance(pair1, nowLeft);
				R = getDistance(pair1, nowRight);
				if (L == R) {
					if (hand.equals("right")) {
						nowRight.row = 3;
						nowRight.col = 1;
						answer += "R";
					} else {
						nowLeft.row = 3;
						nowLeft.col = 1;
						answer += "L";
					}
				} else if (L < R) {
					nowLeft.row = 3;
					nowLeft.col = 1;
					answer += "L";
				} else {
					nowRight.row = 3;
					nowRight.col = 1;
					answer += "R";
				}

				break;

			default:
				break;
			}

		}
		return answer;
	}

	public static void main(String[] args) {
		키패드누르기 s = new 키패드누르기();
		int[] board = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String answer = s.solution(board, "right");
		System.out.println(answer);
	}
}
