package Algorithm.src.Union_find;
// https://programmers.co.kr/learn/courses/30/lessons/43162
// union-find�� Ǯ����. -> dfs�� Ǯ ���� �ִ�.

public class ��Ʈ��ũ {

	// �θ� ��带 ã����
	public int find(int[] answerList, int child) {
		if (answerList[child] == child) {
			return child;
		}
		return find(answerList, answerList[child]);
	}
	
	// i�� j�� �θ��带 ã�� i�� �θ��带 j�� �θ�� �����༭ ���� ������
	public void union(int[] answerList, int i, int j) {
		i = find(answerList, i);
		j = find(answerList, j);
		answerList[j] = i;
	}

	public int solution(int n, int[][] computers) {
		int answer = 0;
		int[] answerList = new int[n];
		for (int i = 0; i < answerList.length; i++) {
			answerList[i] = i;
		}
		answerList[0] = 0;
		
		// map�� �밢�� �Ʒ��κи� Ž���ؼ� i,j�� ���� �θ� ��������(�θ� ��忡 ���� �׷��� ����)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (computers[i][j] == 1) {
					union(answerList, i, j);
				}
			}
		}
		
		// �ش� �θ��尡 �� �� ��忡 �Ҵ� �ƴ����� ���� ++
		int[] checkParentCount = new int[n];
		for (int i = 0; i < n; i++) {
			int parent = find(answerList, i);
			checkParentCount[parent]++;
		}
		
		// �θ��尡 �Ҵ�� ���� 1�� �λ��� �͸� üũ�ϸ� �׷��� ������ üũ�ϴ� ��
		for (int i = 0; i < n; i++) {
			if (checkParentCount[i] > 0) {
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		��Ʈ��ũ s = new ��Ʈ��ũ();
		int[][] computers = { { 1 } };
		// int[][] computers = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 0
		// }, { 0, 0, 1, 1, 0 },
		// { 0, 0, 0, 0, 1 } };
		int answer = s.solution(1, computers);
		System.out.println(answer);
	}
}
