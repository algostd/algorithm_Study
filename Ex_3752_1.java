package SWE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_3752_1 {
	// 3752. 가능한 시험점수 (d4)
	// 첫번째 풀이. 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()); // test_case 개수

		for (int test_case = 1; test_case <= T; test_case++) {

			// 점수 개수
			int N = Integer.parseInt(br.readLine());

			int[] score = new int[N]; // 받은 시험점수 분포 배열
			int sum = 0;

			// 데이터 정리
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				score[i] = Integer.parseInt(str[i]);
				sum += score[i];
			}

			int[] AllScore = new int[sum + 1]; // 가능한 시험점수 분포
			AllScore[0]++;

			// 정렬
			// Arrays.sort(score);

			// 검사
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = AllScore.length - 1; j >= 0; j--) {
					if (AllScore[j] == 1) {
						AllScore[j + score[i]] = 1;
					}
				}
			}

			// 개수 세기
			for (int j = 0; j < AllScore.length; j++) {
				if (AllScore[j] > 0) {
					count++;
				}
			}

			bw.write("#" + test_case + " " + count + "\n");
		}
		bw.flush();
		br.close();
		bw.close();

	}
}
