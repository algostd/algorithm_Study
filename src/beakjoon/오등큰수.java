import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        //algorithm
        //1. F를 계산하여 Map에 담는다. F(1) = 3, F(2) = 2,
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }

        //2. F 값끼리 비교한다.
        int[] dp = new int[N];
        dp[N - 1] = -1;
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[N - 1]);
        for (int i = N - 1; i >= 0; i--) {
            int num = arr[i];
            int curF = map.get(num);

            while (!stack.isEmpty()) {
                int preNum = stack.peek();
                int preF = map.get(preNum);

                if (curF < preF) {
                    dp[i] = preNum;
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) {
                dp[i] = -1;
            }
            stack.push(num);
        }

        //output
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(dp[i]));
            bw.write(" ");
        }
        bw.flush();
    }
}
