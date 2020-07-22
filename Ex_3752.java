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

			// 배열
			int[] arr = new int[101]; // 받은 시험점수 분포 배열
			int[] temp = new int[10000]; // 가능한 시험점수 분포
			
			// 데이터 정리
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < str.length; i++) {
				arr[i] = Integer.parseInt(str[i]);
				temp[i] = Integer.parseInt(str[i]);
			}
			
			// 정렬
			Arrays.sort(arr);
			Arrays.sort(temp);
			
			// 검사
			int cnt = str.length + 1; // 1은 0의 개수를 더한것 
			for (int i = 0; i < arr.length - 1; i++) {
				for (int j = i + 1; j < str.length; j++) {
					
				}
			}

		}

	}
}
