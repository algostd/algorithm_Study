
public class 匙飘况农 {

	public int find(int[] answerList, int child) {
		if (answerList[child] == child) {
			return child;
		}
		return find(answerList, answerList[child]);
	}

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

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (computers[i][j] == 1) {
					union(answerList, i, j);
				}
			}
		}

		int[] checkParentCount = new int[n];
		for (int i = 0; i < n; i++) {
			int parent = find(answerList, i);
			checkParentCount[parent]++;
		}

		for (int i = 0; i < n; i++) {
			if (checkParentCount[i] > 0) {
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		匙飘况农 s = new 匙飘况农();
		int[][] computers = { { 1 } };
		// int[][] computers = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 0
		// }, { 0, 0, 1, 1, 0 },
		// { 0, 0, 0, 0, 1 } };
		int answer = s.solution(1, computers);
		System.out.println(answer);
	}
}
