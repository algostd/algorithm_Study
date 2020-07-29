package beakjoon.stack;

// 1935 후위표기식 2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostfixExpression2 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis(); // 1970. 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        Map<Character, Double> map = new HashMap<>();
        String input = br.readLine();
        
        char key = 'A';
        for (int i = 0; i < N; i++) {
            map.put(key, Double.parseDouble(br.readLine()));
            key++;
        }
        
        Stack<Double> stack = new Stack<>();
        
        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                stack.push(map.get(c));
            } else {
                double temp = calculate(stack.pop(), stack.pop(), c);
                stack.push(temp);
            }
        }
        
        System.out.printf("%.2f", stack.pop());
        System.out.println();
        System.out.println(System.currentTimeMillis() - startTime + " ms");
        
    } // end of main

    private static double calculate(Double num1, Double num2, char c) {
        
        switch (c) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                return -1;
        } // end of switch
    } // end of method

} // end of class
