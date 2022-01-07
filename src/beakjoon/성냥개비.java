import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static String min;
	private static int startDigit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 4
		// 3
		// 6
		// 7

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			startDigit = ((N - 1) / 7) + 1;

			//최솟값
			//맨 처음 숫자는 0이 아닌 1부터 시작
			min = "";
			generateMin(min, startDigit, N);

			System.out.print(min + " ");

			//최댓값
			//1 또는 7
			if (N % 2 == 1) {
				System.out.print("7");
				N -= 3;
			}
			for (int i = 0; i < N; i += 2) {
				System.out.print("1");
			}
			System.out.println();
		}
	}

	public static void generateMin(String str, int digit, int stick) {
		if (startDigit == 1) {
			if (stick == 2) {
				str += "1";
			}
			if (stick == 3) {
				str += "7";
			}
			if (stick == 4) {
				str += "4";
			}
			if (stick == 5) {
				str += "2";
			}
			if (stick == 6) {
				str += "6";
			}
			if (stick == 7) {
				str += "8";
			}
			min = str;
			return;
		}

		if (digit == 1) {
			if (stick == 2) {
				str += "1";
			}
			if (stick == 3) {
				str += "7";
			}
			if (stick == 4) {
				str += "4";
			}
			if (stick == 5) {
				str += "2";
			}
			if (stick == 6) {
				str += "0";
			}
			if (stick == 7) {
				str += "8";
			}
			min = str;
			return;
		}

		if (stick % 7 == 0) {
			for (int i = 0; i < digit; i++) {
				str += "8";
			}
			min = str;
			return;
		}

		//맨 처음 자리일 경우 1부터 시작
		if (digit == startDigit) {
			if (stick - 2 <= (digit - 1) * 7) {
				str += "1";
				generateMin(str, digit - 1, stick - 2);
				return;
			}
			if (stick - 5 <= (digit - 1) * 7) {
				str += "2";
				generateMin(str, digit - 1, stick - 5);
				return;
			}
			if (stick - 4 <= (digit - 1) * 7) {
				str += "4";
				generateMin(str, digit - 1, stick - 4);
				return;
			}
			if (stick - 6 <= (digit - 1) * 7) {
				str += "6";
				generateMin(str, digit - 1, stick - 6);
				return;
			}
			if (stick - 3 <= (digit - 1) * 7) {
				str += "7";
				generateMin(str, digit - 1, stick - 3);
				return;
			}
			if (stick - 7 <= (digit - 1) * 7) {
				str += "8";
				generateMin(str, digit - 1, stick - 7);
				return;
			}
		}

		if (stick - 6 <= (digit - 1) * 7) {
			str += "0";
			generateMin(str, digit - 1, stick - 6);
			return;
		}
		if (stick - 2 <= (digit - 1) * 7) {
			str += "1";
			generateMin(str, digit - 1, stick - 2);
			return;
		}
		if (stick - 5 <= (digit - 1) * 7) {
			str += "2";
			generateMin(str, digit - 1, stick - 5);
			return;
		}
		if (stick - 4 <= (digit - 1) * 7) {
			str += "4";
			generateMin(str, digit - 1, stick - 4);
			return;
		}
		if (stick - 3 <= (digit - 1) * 7) {
			str += "7";
			generateMin(str, digit - 1, stick - 3);
			return;
		}
		if (stick - 7 <= (digit - 1) * 7) {
			str += "8";
			generateMin(str, digit - 1, stick - 7);
			return;
		}

	}

}