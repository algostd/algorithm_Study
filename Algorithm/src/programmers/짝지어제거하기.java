package programmers;

import java.util.Stack;

public class 짝지어제거하기 {
	public int solution(String s) {
		int answer = -1;
		// 홀수면 리풋
		if (s.length() % 2 == 1) {
			return 0;
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char nowChar = s.charAt(i);
        	if(!stack.isEmpty()){ // 안비어있고
                if(stack.peek() == nowChar) { // 연속되면 
                	stack.pop();
                } else { // 연속되지 않으면
                	stack.push(nowChar);
                }
            } else{ // 비어있으면
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
		짝지어제거하기 s = new 짝지어제거하기();
		String str = "abcdeedcbagg";
		int answer = s.solution(str);
		System.out.println(answer);
	}
}
