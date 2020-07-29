package Algorithm.Spring.BOJ1935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Double> stack;

    static BufferedReader bufferedReader;

    static char op;
    static char array[];
    static int N;
    static String s;
    static double number[];

    public static void main(String[] args) throws IOException {

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        stack = new Stack<>();

        N = Integer.parseInt(bufferedReader.readLine());

        number = new double[N];

        s = bufferedReader.readLine();

        array = s.toCharArray();

        for (int i = 0; i < N; i++) {
            number[i] = Double.parseDouble(bufferedReader.readLine());
        }

        for (int i = 0; i < s.length(); i++) {

            op = array[i];

            switch (op) {
                case '+':
                case '-':
                case '*':
                case '/':
                    solution(stack, stack.pop(), stack.pop(), op);
                    break;
                default:
                    stack.push(number[op - 'A']);
                    break;
            }
        }

        System.out.format("%.2f", stack.peek());

    }

    public static void solution(Stack<Double> stack, double a, double b, char c) {

        switch (c) {
            case '+':
                stack.push(b + a);
                break;
            case '-':
                stack.push(b - a);
                break;
            case '*':
                stack.push(b * a);
                break;
            case '/':
                stack.push(b / a);
                break;
        }

    }
}
