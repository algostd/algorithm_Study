package Algorithm.src.Stack;

public class ¦���������ϱ�Fail {
	// Fail ���� �迭�� ������ �ݺ��ؼ� Ǫ�°��� ����
	
	public int solution(String s) {
		// Ȧ���� ��ǲ
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
		¦���������ϱ�Fail s = new ¦���������ϱ�Fail();
		String str = "abcdeedcbagg";
		int answer = s.solution(str);
		System.out.println(answer);
	}
}
