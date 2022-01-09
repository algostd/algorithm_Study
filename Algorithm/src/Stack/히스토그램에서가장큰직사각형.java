import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 히스토그램에서가장큰직사각형 {
    private static StringBuilder sb = new StringBuilder();
    private static int N;
    private static String[] split;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            split = br.readLine().split(" ");
            if (split[0].equals("0")) {
                break;
            }
            N = Integer.parseInt(split[0]);
            getAnswer();
        }
        System.out.println(sb);
    }

    private static void getAnswer() {
        int[] left = new int[N + 2];
        int[] right = new int[N + 2];
        // 맨 끝값 초기화
        left[N + 1] = N + 1;
        right[N + 1] = N + 1;

        // height 초기화
        int[] height = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(split[i]);
        }

        // 입력과 left monotoneStack
        Stack<Integer> monotoneStack = new Stack<>();
        for (int now = 0; now <= N; now++) {
            if (height[now] == 0) {
                monotoneStack.clear();
                monotoneStack.push(now);
                continue;
            }
            if (height[monotoneStack.peek()] < height[now]) { // 현재값이 이전 값보다 크면
                monotoneStack.push(now);
                left[now] = now - 1;
                continue;
            }
            while (height[monotoneStack.peek()] >= height[now]) { // 모노톤 스택 유지
                monotoneStack.pop();
            }
            left[now] = monotoneStack.peek();
            monotoneStack.push(now);
        }

        // right monotoneStack 과 answer 구하기
        monotoneStack.clear();
        long answer = 0;
        for (int now = N + 1; now >= 0; now--) {
            if (height[now] == 0) { // 0일때 스택 초기화 및 해당 인덱스 push
                monotoneStack.clear();
                monotoneStack.push(now);
                continue;
            }
            if (height[monotoneStack.peek()] < height[now]) { // 현재값이 이전 값보다 크면
                monotoneStack.push(now);
                right[now] = now + 1;
                answer = Math.max(answer, (long) (right[now] - left[now] - 1) * height[now]);
                continue;
            }
            while (height[monotoneStack.peek()] >= height[now]) { // 모노톤 스택 유지
                monotoneStack.pop();
            }
            right[now] = monotoneStack.peek();
            monotoneStack.push(now);

            // 정답 구하기
            answer = Math.max(answer, (long) (right[now] - left[now] - 1) * height[now]);
        }

        sb.append(answer).append("\n");
    }
}
