package programmers;

// https://programmers.co.kr/learn/courses/30/lessons/12899
// 부분집합, 제곱수로 순서를 계산하여 몇자리 부분집합인지 알고 인덱스 매핑을 이용해서 124로 정답 부분집합 만들기 => 시간초과 실패

// 1, 2,4 
public class 나라의숫자124Fail {
	final static int[] arr124 = { 1, 2, 4 };
	static int whereIsN = 3; // 3제곱의 범위 -> 이유: 1자리수 3 = 3, 2자리수 3*3 = 9, 3자리수 3*3*3 자리수에대한 3개의 원소로 이루어진
								// 부분집합으로 떨어지기 때문

	public String solution(int n) {
		String answer = "";
		int count = 1; // 몇 자리 수인지 = 몇 제곱까지 인지

		// n이 3의 몇제곱수 범위에 있는지 확인
		while (n > whereIsN) {
			count++;
			whereIsN += Math.pow(3, count);
		}

		int[] subset = new int[count];
		whereIsN -= Math.pow(3, count);
		whereIsN++; // 이 과정가지 거치면 whereIsN은 count 자리수 시작하는 첫 인덱스를 가리킴
		answer = makeSubset(0, subset, n, count);

		return answer;
	}

	// 부분 집합을 이용해서 답 만들기
	private String makeSubset(int stack, int[] subset, int n, int count) {
		String answer = "";
		if (whereIsN > n || stack == count) { // 정답 인덱스 n을 지나친 경우나 부분집합을 만들 수 있는 자리수 초과한 경우 탈출
			return "";
		}
		for (int j = 0; j < 3; j++) {
			subset[stack] = arr124[j]; // 부분집합 값 채워넣기
			if (stack == count - 1) { // 부분집합이 완성되면
//				System.out.println(" whereIsN: "+ whereIsN + " n: " + n);
				if (whereIsN == n) { // 정답 인덱스와 일치한경우 -> 이것으로 만든 부분집합이 정답이니까 정답처리
					String tempStr = "";
					for (int i = 0; i < count; i++) {
						tempStr += subset[i];
					}
					answer = tempStr;
					whereIsN++;
					return answer;
				} else {
					whereIsN++; // 정답이 아니면 그냥 다음 부분집합 계속 생산
				}
			}
			String tempAnswer = makeSubset(stack + 1, subset, n, count); // 재귀로 부분집합 만들기
			if (!tempAnswer.equals("")) {
				answer = tempAnswer;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		나라의숫자124Fail s = new 나라의숫자124Fail();
		int n = 16;
		String answer = s.solution(n);
		System.out.println(answer);
	}
}
