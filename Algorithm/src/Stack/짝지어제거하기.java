package Algorithm.src.Stack;

import java.util.Stack;

// Stack ������ Ǯ�� O(N)���� Ǯ �� ���� 
// ���� for�� 2���� Ǯ�� ���� �ð� ���⵵ �߻�

public class ¦���������ϱ� {
	public int solution(String s) {
		int answer = -1;
		// Ȧ���� ����
		if (s.length() % 2 == 1) {
			return 0;
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char nowChar = s.charAt(i);
        	if(!stack.isEmpty()){ // �Ⱥ���ְ�
                if(stack.peek() == nowChar) { // ���ӵǸ� 
                	stack.pop();
                } else { // ���ӵ��� ������
                	stack.push(nowChar);
                }
            } else{ // ���������
                stack.push(s.charAt(i));
            }
        }
        
        if (stack.isEmpty()) {
			answer = 1;
		} else {
			answer = 0;
		}
		return answer;
	}
	public static void main(String[] args) {
		¦���������ϱ� s = new ¦���������ϱ�();
		String str = "abcdeedcbagg";
		int answer = s.solution(str);
		System.out.println(answer);
	}
}
