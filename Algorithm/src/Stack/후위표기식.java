import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식 {

    /*
     1. Stack과 우선순위를 이용해서 출력순서를 결정한다.
      => Stack은 연산자를 기억하고 있는 메모리
     2. 각 형태에 따른 처리방식
      => 알파벳은 그냥 출력
      => 열린괄호는 stack 에 그냥 저장
      => 닫힌괄호는 stack에 저장된 열린괄호가 나올때까지 pop
      => 연산자는 우선순위에 따라 처리 결정
        ( 괄호: 0, +-: 1, *\/:2 이다
        stack에서 대기중인 친구들보다 큰 친구가오면 그냥 push
        (큰 친구가 먼저 pop되서 나가야 되기 때문)
        stack에서 대기중인 친구들보다 작은 친구가오면 pop. pop ..
        그리고 작은 친구 push
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder answerSb = new StringBuilder();

        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char now = chars[i];
            if (now == '(') {
                stack.push(now);
            } else if (now == ')') {
                while (!stack.isEmpty()) {
                    Character popped = stack.pop();
                    if (popped == '(') {
                        break;
                    }
                    answerSb.append(popped);
                }
            } else if ('A' <= now && now <= 'Z') {
                answerSb.append(now);
            } else { // 연산자인 경우
                while (!stack.isEmpty() &&
                        getPriority(stack.peek()) >= getPriority(now)) {
                    answerSb.append(stack.pop());
                }
                stack.push(now);
            }
        }
        while (!stack.isEmpty()) {
            answerSb.append(stack.pop());
        }
        System.out.println(answerSb);
    }

    public static int getPriority(char op) {
        if (op == '(') {
            return 0;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 2;
        }
    }
}
