package SWE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Ex_9229 {
	final int max = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// n m
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			// 과자무게 데이터
			String[] str = br.readLine().split(" ");
			ArrayList<Integer> arr = new ArrayList<Integer>(n);
			for (int i = 0; i < str.length; i++) {
				arr.add(Integer.parseInt(str[i]));
			}

			// 정렬
			// arr.sort(null);

			// 최대무게 구하기 검사
			boolean check = false;
			int max = -1;
			for (int i = arr.size() - 1; i > 0; i--) {
				for (int j = 1; j <= i; j++) {
					int temp = arr.get(i) + arr.get(i - j);
					if (temp <= m && temp > max)
						max = temp;
				}
			}
			
			if (max == -1) {
				bw.write("#" + test_case + " " + -1 + "\n");
			} else
				bw.write("#" + test_case + " " + max + "\n");
		}
		br.close();
		bw.close();
	}
}
