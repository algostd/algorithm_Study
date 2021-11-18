package Algorithm.src.DP;

// https://programmers.co.kr/learn/courses/30/lessons/12899
// ���ʿ� ������ ������ �ִ� �� �˾����� 3���� ��� �����鼭 �������� �λ��ϴ°� ��  ������ ������ �Ǵ� 

public class �����Ǽ���124 {
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
		�����Ǽ���124 s = new �����Ǽ���124();
		int n = 16;
		String answer = s.solution(n);
		System.out.println(answer);
	}
}
