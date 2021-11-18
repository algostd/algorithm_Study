package Algorithm.src.Stack;

public class 짝지어제거하기Fail {
	// Fail 버전 배열로 여러번 반복해서 푸는것이 문제
	
	public int solution(String s) {
		// 홀수면 리풋
		if (s.length() % 2 == 1) {
			return 0;
		}

		String nextString = "--";
		int answer = -1;
		int pointer = 0;
		outer: while (true) {
			if (nextString.equals(s) || s.length() <= 1 || s.equals("")) {
				break outer;
			}
			nextString = s;
			for (int i = pointer; i < s.length(); i++) {
				char firstChar = s.charAt(i);
				char secondChar = ' ';
				if (i + 1 < s.length()) {
					secondChar = s.charAt(i + 1);
				}
				if (firstChar == secondChar) {
					if (i == 0) {
						s = s.substring(2, s.length());
					} else if (i == s.length() - 2) {
						s = s.substring(0, s.length() - 2);
						pointer = i - 1;
					} else {
						s = s.substring(0, i) + s.substring(i + 2, s.length());
						pointer = i - 1;
					}
					
					continue outer;
				}
			}
		}
		if (s.equals("")) {
			answer = 1;
		} else {
			answer = 0;
		}
		return answer;
	}

	public static void main(String[] args) {
		짝지어제거하기Fail s = new 짝지어제거하기Fail();
		String str = "abcdeedcbagg";
		int answer = s.solution(str);
		System.out.println(answer);
	}
}
