package Algorithm.src.Sort;

import java.util.ArrayList;
import java.util.Collections;

// https://programmers.co.kr/learn/courses/30/lessons/42747
// for문 2개를 활용해서 전체 탐색, but 문제 이해가 중요했음

public class H_Index {

	public int solution(int[] citations) {
		int answer = 0;
		int n = citations.length;
		if (n == 1) {
			if (citations[0] == 1) {
				return 1;
			} else {
				return 0;
			}
		}

		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			arr.add(citations[i]);
		}

		Collections.sort(arr);
		int h = Math.min(n, 10000);

		while (h >= 0) {
			int upCount = 0;
			int downCount = 0;
			for (int i = 0; i < n; i++) {
				if (arr.get(i) >= h) {
					upCount++;
				} else {
					downCount++;
				}
			}
			if (upCount >= h && downCount <= h) {
				answer = h;
				break;
			}
			h--;
		}

		return answer;
	}

	public static void main(String[] args) {
		H_Index s = new H_Index();
		int[] clue = { 6, 5, 4, 1, 0 };
		int answer = s.solution(clue);
		System.out.println(answer);

	}
}
