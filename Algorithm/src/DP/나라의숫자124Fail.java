package Algorithm.src.DP;

// https://programmers.co.kr/learn/courses/30/lessons/12899
// �κ�����, �������� ������ ����Ͽ� ���ڸ� �κ��������� �˰� �ε��� ������ �̿��ؼ� 124�� ���� �κ����� ����� => �ð��ʰ� ����

// 1, 2,4 
public class �����Ǽ���124Fail {
	final static int[] arr124 = { 1, 2, 4 };
	static int whereIsN = 3; // 3������ ���� -> ����: 1�ڸ��� 3 = 3, 2�ڸ��� 3*3 = 9, 3�ڸ��� 3*3*3 �ڸ��������� 3���� ���ҷ� �̷����
								// �κ��������� �������� ����

	public String solution(int n) {
		String answer = "";
		int count = 1; // �� �ڸ� ������ = �� �������� ����

		// n�� 3�� �������� ������ �ִ��� Ȯ��
		while (n > whereIsN) {
			count++;
			whereIsN += Math.pow(3, count);
		}

		int[] subset = new int[count];
		whereIsN -= Math.pow(3, count);
		whereIsN++; // �� �������� ��ġ�� whereIsN�� count �ڸ��� �����ϴ� ù �ε����� ����Ŵ
		answer = makeSubset(0, subset, n, count);

		return answer;
	}

	// �κ� ������ �̿��ؼ� �� �����
	private String makeSubset(int stack, int[] subset, int n, int count) {
		String answer = "";
		if (whereIsN > n || stack == count) { // ���� �ε��� n�� ����ģ ��쳪 �κ������� ���� �� �ִ� �ڸ��� �ʰ��� ��� Ż��
			return "";
		}
		for (int j = 0; j < 3; j++) {
			subset[stack] = arr124[j]; // �κ����� �� ä���ֱ�
			if (stack == count - 1) { // �κ������� �ϼ��Ǹ�
//				System.out.println(" whereIsN: "+ whereIsN + " n: " + n);
				if (whereIsN == n) { // ���� �ε����� ��ġ�Ѱ�� -> �̰����� ���� �κ������� �����̴ϱ� ����ó��
					String tempStr = "";
					for (int i = 0; i < count; i++) {
						tempStr += subset[i];
					}
					answer = tempStr;
					whereIsN++;
					return answer;
				} else {
					whereIsN++; // ������ �ƴϸ� �׳� ���� �κ����� ��� ����
				}
			}
			String tempAnswer = makeSubset(stack + 1, subset, n, count); // ��ͷ� �κ����� �����
			if (!tempAnswer.equals("")) {
				answer = tempAnswer;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		�����Ǽ���124Fail s = new �����Ǽ���124Fail();
		int n = 16;
		String answer = s.solution(n);
		System.out.println(answer);
	}
}
