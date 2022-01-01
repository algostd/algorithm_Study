import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 히스토그램에서가장큰직사각형 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] left = new int[N + 2];
        int[] right = new int[N + 2];
        int[] h = new int[N + 2];
        left[N + 1] = N + 1;
        right[N + 1] = N + 1;

        // 입력과 left monotoneStack
        Stack<Integer> monotoneStack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(br.readLine());
            if (!monotoneStack.isEmpty() && h[monotoneStack.peek()] < h[i]) {
                monotoneStack.push(i);
                left[i] = i - 1;
                continue;
            }
            while (!monotoneStack.isEmpty() && h[monotoneStack.peek()] > h[i]) { // 모노톤 스택 유지
                monotoneStack.pop();
            }
            if (monotoneStack.isEmpty()) {
                left[i] = 0;
            } else if (h[i] == h[monotoneStack.peek()]){
                left[i] = left[monotoneStack.peek()];
            } else {
                left[i] = monotoneStack.peek();
            }
            monotoneStack.push(i);
        }

        // max로 answer 구하기 && right monotoneStack
        monotoneStack.clear();
        int answer = -1;
        for (int i = N; i >= 0; i--) {
            if (!monotoneStack.isEmpty() && h[monotoneStack.peek()] < h[i]) {
                monotoneStack.push(i);
                right[i] = i + 1;
                answer = Math.max(answer, (right[i] - left[i] - 1) * h[i]);
                continue;
            }
            while (!monotoneStack.isEmpty() && h[monotoneStack.peek()] > h[i]) { // 모노톤 스택 유지
                monotoneStack.pop();
            }
            if (monotoneStack.isEmpty()) { // 스택에 없으면 값이 맨끝값으로
                right[i] = N + 1;
            } else if(h[i] == h[monotoneStack.peek()]){ // 값이 있는데 같으면 그 값은 오른쪽 제일 낮은곳으로
                right[i] = right[monotoneStack.peek()];
            } else { // 값이 있는데 나보다 낮으면 해당값이 right가 된다.
                right[i] = monotoneStack.peek();
            }
            monotoneStack.push(i);
            answer = Math.max(answer, (right[i] - left[i] - 1) * h[i]);
        }

        System.out.println(answer);
    }
}
