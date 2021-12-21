import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 오큰수2 {
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
        int[] pointers = new int[N];
        pointers[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            // 현재값개수와 이전값의 개수 비교
            int now = intArr[i];
            int before = intArr[i + 1];
            if (now < before) {
                pointers[i] = i + 1;
            } else if (now > before) {
                int nextPointer = pointers[i + 1];
                int pointedValue = intArr[nextPointer];
                while (pointedValue <= now) {
                    if (nextPointer == pointers[nextPointer]) {
                        break;
                    }
                    nextPointer = pointers[nextPointer];
                    pointedValue = intArr[nextPointer];
                }
                if (pointedValue > now) {
                    pointers[i] = nextPointer;
                } else {
                    pointers[i] = i;
                }
            } else {
                if (pointers[i + 1] == i + 1) {
                    pointers[i] = i;
                } else{
                    pointers[i] = pointers[i + 1];
                }
            }
        }

        // 정답 도출
        for (int i = 0; i < N; i++) {
            int pointer = pointers[i];
            if (i == pointer) {
                sb.append(-1).append(" ");
            } else{
                sb.append(intArr[pointer]).append(" ");
            }
        }
        for (int i : pointers) {

        }
        System.out.println(sb);
    }
}
