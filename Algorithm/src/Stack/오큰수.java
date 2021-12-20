import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 오큰수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    private static int[] intArr;

    public static void main(String[] args) throws IOException {
        // 입력
        int N = Integer.parseInt(br.readLine());
        intArr = new int[N];
        String[] strArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        // 알고리즘
        Stack<Integer> stack = new Stack<>();
        int[] answerArr = new int[N];
        for (int i = 0; i < N; i++) {
            int nowValue = intArr[i];
            while (!stack.isEmpty() && nowValue > intArr[stack.peek()]) {
                answerArr[stack.pop()] = nowValue;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            answerArr[stack.pop()] = -1;
        }

        // 정답 도출
        for (int i : answerArr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}