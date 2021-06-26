package programmers;

import java.util.Stack;

// Stack 문제로 풀면 O(N)으로 풀 수 있음 
// 만약 for문 2개로 풀면 어마어마한 시간 복잡도 발생

public class 짝지어제거하기 {
	public int solution(String s) {
		int answer = -1;
		// 홀수면 리턴
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
