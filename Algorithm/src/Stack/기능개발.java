package Algorithm.src.Stack;

import java.util.Stack;
import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/42586
// �����ϰ� ���� �ڷᱸ���� Ǫ�� ����

public class ��ɰ��� {
	public int[] solution(int[] progresses, int[] speeds) {
		int n = progresses.length;
		int[] finishDate = new int[n];

		// �۾��� (�ϳ���) �� ó���ؼ� �� �� �ɸ��� �� �˰� finishDate�� ����
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

		// ���� ���� �Ųٷ� ����, �׷� �������� �۾��� �տ� �ִ� ����� �����ϱ�
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = n - 1; i >= 0; i--) {
			stack.push(finishDate[i]);
		}

		// ���� ����
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
		// ���� ����ȯ(ArrayList -> int[])
		int[] answer = new int[answerArr.size()];
		int i = 0;
		for (Integer value : answerArr) {
			answer[i++] = value;
		}

		return answer;
	}
}
