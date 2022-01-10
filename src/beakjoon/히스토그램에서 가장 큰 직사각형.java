import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	private static int N;
	private static int[] height, left, right;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//left[i]: 해당 index 기준으로 왼쪽에서 heigh[index]보다 높이가 작으면서 가장 가까운 index
		//right[i]: 해당 index 기준으로 오른쪽에서 heigh[index]보다 높이가 작으면서 가장 가까운 index

		while (true) {
			//입력
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			if (N == 0) {
				break;
			}
			height = new int[N + 2];
			left = new int[N + 2];
			right = new int[N + 2];
			for (int i = 1; i <= N; i++) {
				height[i] = Integer.parseInt(input[i]);
			}

			//알고리즘
			//왼쪽
			Stack<Integer> stack = new Stack<>();
			stack.push(0);
			for (int i = 1; i <= N; i++) {
				while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
					stack.pop();
				}
				if (!stack.isEmpty()) {
					left[i] = stack.peek();
				}
				stack.push(i);
			}

			//오른쪽
			stack = new Stack<>();
			stack.push(N + 1);
			for (int i = N; i >= 1; i--) {
				while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
					stack.pop();
				}
				if (!stack.isEmpty()) {
					right[i] = stack.peek();
				}
				stack.push(i);
			}

			long max = 0;
			for (int i = 1; i <= N; i++) {
				max = Math.max(max, (long) height[i] * (right[i] - left[i] - 1));
			}

			System.out.println(max);
		}

	}

}