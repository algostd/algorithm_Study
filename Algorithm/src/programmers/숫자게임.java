package programmers;

import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/12987
// 생각 많이하면 풀 수 있는 문제
// 가장 근소한 차이로 어떻게 이길 것인가에 대한 고민

public class 숫자게임 {
	public int solution(int[] A, int[] B) {
		int answer = 0;
		int n = A.length;

		// 정렬
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
