package programmers;

// https://programmers.co.kr/learn/courses/30/lessons/12899
// 에초에 제곱과 관련이 있는 걸 알았으면 3으로 계속 나누면서 나머지를 셰산하는게 더  좋았을 것으로 판단 

public class 나라의숫자124 {
	String solution(int n) {
		StringBuilder answer = new StringBuilder();
		int rest;
		while (n != 0) {
			rest = n % 3;
			n /= 3;

			if (rest == 0) {
				n -= 1;
				answer.insert(0, 4); 
			} else {
				answer.insert(0, rest); 
			}
		}
		String strAnswer = answer.toString();
		return strAnswer;
	}

	public static void main(String[] args) {
		나라의숫자124 s = new 나라의숫자124();
		int n = 16;
		String answer = s.solution(n);
		System.out.println(answer);
	}
}
