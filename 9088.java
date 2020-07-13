package SWE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class test {
	
	static int[] diamondsCounter = new int[10001];
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = sc.nextInt();
		int N, K;
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			
			Arrays.fill(diamondsCounter, 0);
			for (int i = 0; i < N; i++) {
				diamondsCounter[sc.nextInt()]++;
			}

			int max = 0;
			
			for (int j = 1; j <= 1 + K; j++) {
				max += diamondsCounter[j];
			}
			
			int sum = max;
			for (int i = 1; i < diamondsCounter.length - K - 1; i++) {
				sum = sum + diamondsCounter[i + K + 1] - diamondsCounter[i];
				if (max < sum)
					max = sum;
			}
			
			bw.write("#" + test_case + " " + max + "\n");
		}

		sc.close();
		bw.flush();
		bw.close();
	}
}