import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 이모티콘 {

    final static int MAX = 1000;
    private static int[] min;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 최소 걸리는 시간을 다루는 배열
        min = new int[1002];
        Arrays.fill(min, MAX);

        min[1] = 1;

        // 점화식: min[현재] = Min(min[현재-1] + 1, 현재짝수인경우 - min[현재/2] + 2, min[현재+1] + 1)
        for (int i = 2; i <= 1001; i++) {
            getMin(i);

            // 배수 만큼으로 초기화
            for (int j = 2; j <= 1001; j++) {
                int multipleIndex = i * j; // 배수
                if (multipleIndex > 1001) {
                    break;
                }
                min[multipleIndex] = Math.min(min[multipleIndex], min[i] + j);
                System.out.println("min[" + multipleIndex + "] = " + min[multipleIndex]);
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println("i: " + i + " min[i]: " + min[i]);
        }
        System.out.println(min[N]);
    }

    static private void getMin(int i) {
        // 자기보다 1 작은 값과 비교
        min[i] = Math.min(min[i], min[i - 1] + 1);

        // 자기보다 1큰 값과 비교
        if (i < N) {
            min[i] = Math.min(min[i], min[i + 1] + 1);
        }
    }
}
