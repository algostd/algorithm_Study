package 백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ex_2565 {
	// 백준 2565 전깃줄(실버1)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 전선 개수
		int N = Integer.parseInt(br.readLine());

		// location 배열, 인덱스는 왼쪽 전봇대 번호, 값은 연결된 오른족 전봇대 번호
		int[] location = new int[501];

		int max = 0;
		for (int i = 1; i <= N; i++) {
			String[] str = br.readLine().split(" ");
			location[Integer.parseInt(str[0])] = Integer.parseInt(str[1]);
			if (max < Integer.parseInt(str[0]))
				max = Integer.parseInt(str[0]);
		}

		// 전깃줄 줄이기
		int cnt = 0;
		int answer1 = 0;
		int answer2 = 0;
		int answer = 0;
		for (int i = 1; i < max; i++) {
			int value = location[i];
			if (value != 0) {
				for (int j = i + 1; j <= max; j++) {
					if (location[j] != 0 && location[j] < value)
						cnt++;
				}

				if (cnt >= 1) {
					answer1++;
				}
				System.out.println("cnt : " + cnt + " answer : " + answer1);
				cnt = 0;
			}
		}

		for (int i = max; i > 1; i--) {
			int value = location[i];
			if (value != 0) {
				for (int j = i - 1; j >= 1; j--) {
					if (location[j] != 0 && location[j] > value)
						cnt++;
				}

				if (cnt >= 1) {
					answer2++;
				}
				System.out.println("cnt : " + cnt + " answer : " + answer2);
				cnt = 0;
			}
		}
		answer = answer1 > answer2 ? answer2 : answer1;
		bw.write(answer + "");
		bw.flush();
		bw.close();
		br.close();
	}
	/*
8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6

4
1 3
2 4
3 5
5 1

3
1 3
2 1
3 2

3
1 1
2 2
3 3

3
1 2
2 3
3 1
	 */
}
