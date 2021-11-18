package Algorithm.src.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] charArr = br.readLine().toCharArray();
        char[] boomCharArr = br.readLine().toCharArray();
        int boomLength = boomCharArr.length;
        Stack<Character> stack = new Stack<>();
        outer:
        for (char c : charArr) {
            if (stack.size() >= boomLength - 1&& c == boomCharArr[boomLength - 1]) { // 마지막 문자열이 같으면
                stack.push(c);
                int stackLength = stack.size();
                for (int i = boomLength - 1, j = 1; i >= 0; i--, j++) { // 폭발 문자열만큼 검사
                    if (stack.get(stackLength - j) != boomCharArr[i]) {
                        continue outer; // 폭발 문자열이 아니면 다시 탐색 시작
                    }
                }
                for (int i = 0; i < boomLength; i++) {
                    stack.pop();
                }
            } else { // 마지막 문자가 아니면
                stack.push(c);
            }
        }
        String answer;
        if (stack.isEmpty()) {
            answer = "FRULA";
        } else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            answer = sb.reverse().toString();
        }
        System.out.println(answer);
    }
}