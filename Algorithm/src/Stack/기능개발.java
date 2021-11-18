package Algorithm.src.Stack;

import java.util.Stack;
import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/42586
// 정렬하고 스택 자료구조로 푸는 문제

public class 기능개발 {
	public int[] solution(int[] progresses, int[] speeds) {
		int n = progresses.length;
		int[] finishDate = new int[n];

		// 작업별 (하나씩) 다 처리해서 몇 일 걸리는 지 알고 finishDate에 넣음
		for (int i = 0; i < n; i++) {
			int progress = progresses[i];
			int speed = speeds[i];
			int count = 0;
			while (progress < 100) {
				progress += speed;
				count++;
			}
			finishDate[i] = count;
		}

		// 스택 만들어서 거꾸로 넣음, 그럼 꺼낼때는 작업이 앞에 있는 놈부터 꺼내니까
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = n - 1; i >= 0; i--) {
			stack.push(finishDate[i]);
		}

		// 스택 꺼냄
		ArrayList<Integer> answerArr = new ArrayList<Integer>();
		while (!stack.isEmpty()) {
			int count = 1;
			int now = stack.pop();
			while (!stack.isEmpty() && stack.peek() <= now) {
				stack.pop();
				count++;
			}
			answerArr.add(count);
		}
		// 정답 형변환(ArrayList -> int[])
		int[] answer = new int[answerArr.size()];
		int i = 0;
		for (Integer value : answerArr) {
			answer[i++] = value;
		}

		return answer;
	}
}
