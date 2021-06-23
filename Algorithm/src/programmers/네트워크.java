package programmers;

public class 네트워크 {

	// 부모 노드를 찾아줌
	public int find(int[] answerList, int child) {
		if (answerList[child] == child) {
			return child;
		}
		return find(answerList, answerList[child]);
	}
	
	// i와 j의 부모노드를 찾고 i의 부모노드를 j의 부모로 시켜줘서 서로 연결함
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
		
		// map의 대각선 아래부분만 탐색해서 i,j에 대한 부모를 설정해줌(부모 노드에 따라 그래프 만듬)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (computers[i][j] == 1) {
					union(answerList, i, j);
				}
			}
		}
		
		// 해당 부모노드가 몇 개 노드에 할당 됐는지에 따라 ++
		int[] checkParentCount = new int[n];
		for (int i = 0; i < n; i++) {
			int parent = find(answerList, i);
			checkParentCount[parent]++;
		}
		
		// 부모노드가 할당된 값이 1개 인상인 것만 체크하면 그래프 개수를 체크하는 것
		for (int i = 0; i < n; i++) {
			if (checkParentCount[i] > 0) {
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		네트워크 s = new 네트워크();
		int[][] computers = { { 1 } };
		// int[][] computers = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 0
		// }, { 0, 0, 1, 1, 0 },
		// { 0, 0, 0, 0, 1 } };
		int answer = s.solution(1, computers);
		System.out.println(answer);
	}
}
