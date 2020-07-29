package 백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Ex_1935 {
	// 1935 후위 표기식2(실버3)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 피연산자 개수
		int N = Integer.parseInt(br.readLine());

		// 후위 표기식
		String str = br.readLine();

		// 스택 선언, 정수값 저장
		Stack<Double> stack = new Stack<Double>();

		int[] arr = new int[27];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 검사

		for (int i = 0; i < str.length(); i++) {
			if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
				stack.push((double) (arr[str.charAt(i) - 65]));
			}
			
			else {
				double op2 = stack.pop();
				double op1 = stack.pop();
				switch (str.charAt(i)) {
				case '+':
					stack.push(op1 + op2);
					break;
				case '-':
					stack.push(op1 - op2);
					break;
				case '*':
					stack.push(op1 * op2);
					break;
				case '/':
					stack.push(op1 / op2);
					break;
				case '%':
					stack.push(op1 % op2);
					break;
				default:
					break;
				}
			}
		}
		bw.write(String.format("%.2f", stack.pop()));
		bw.flush();
		br.close();
		bw.close();

	}
}
