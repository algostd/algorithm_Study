import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 히스토그램 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 배열 선언과 초기화
        int[] left = new int[N + 2];
        int[] right = new int[N + 2];
        int[] height = new int[N + 2];
        left[N + 1] = N + 1;
        right[N + 1] = N + 1;


        // 입력과 left monotoneStack
        Stack<Integer> monotoneStack = new Stack<>();
        monotoneStack.push(0);
        for (int now = 1; now <= N; now++) {
            height[now] = Integer.parseInt(br.readLine());
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

        // max로 answer 구하기 && right monotoneStack
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

        System.out.println(answer);
    }
}