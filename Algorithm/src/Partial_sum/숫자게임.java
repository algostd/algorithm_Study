package Algorithm.src.Partial_sum;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12987
// ���� �����ϸ� Ǯ �� �ִ� ����
// ���� �ټ��� ���̷� ��� �̱� ���ΰ��� ���� ���

public class ���ڰ��� {
	public int solution(int[] A, int[] B) {
		int answer = 0;
		int n = A.length;

		// ����
		Arrays.sort(A);
		Arrays.sort(B);

		int indexA = n - 1;
		int indexB = n - 1;
		outer: while (indexA >= 0 && indexB >= 0) {
			while (A[indexA] >= B[indexB]) {
				indexA--;
				if (indexA < 0) {
					break outer;
				}
			}
			answer++;
			indexB--;
			indexA--;
		}

		return answer;
	}
}
