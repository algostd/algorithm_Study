import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오등큰수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    private static int[] intArr;

    public static void main(String[] args) throws IOException {
        // 입력
        int N = Integer.parseInt(br.readLine());
        intArr = new int[N];
        String[] strArr = br.readLine().split(" ");
        int[] countInfo = new int[1000001];
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
            countInfo[intArr[i]]++;
        }
        // 알고리즘
        int[] pointer = new int[N];
        pointer[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            // 현재값개수와 이전값의 개수 비교
            int now = countInfo[intArr[i]];
            int before = countInfo[intArr[i + 1]];
            if (now < before) {
                pointer[i] = i + 1;
            } else if (now > before) {
                int nextPointer = pointer[i + 1];
                int pointedValue = countInfo[intArr[nextPointer]];
                while (pointedValue <= now) {
                    if (nextPointer == pointer[nextPointer]) {
                        break;
                    }
                    nextPointer = pointer[nextPointer];
                    pointedValue = countInfo[intArr[nextPointer]];
                }
                if (pointedValue > now) {
                    pointer[i] = nextPointer;
                } else {
                    pointer[i] = i;
                }
            } else {
                if (pointer[i + 1] == i + 1) {
                    pointer[i] = i;
                } else{
                    pointer[i] = pointer[i + 1];
                }
            }
        }
        // pointer 가 가르키는 값으로 정답 도출
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            if (intArr[i] == intArr[pointer[i]]) {
                answer[i] = -1;
            } else {
                answer[i] = intArr[pointer[i]];
            }
        }
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

}
