import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class 좋은수열 {
    private static int N;
    private static int[] arr;
    private static int[] num123 = new int[]{1, 2, 3};
    private static boolean answerCheck;
    private static  StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());
        answerCheck = false;
        arr = new int[N];

        getGoodSeq(0);
        // 정답출력
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }

    private static void getGoodSeq(int count) {
        if (count == N) {
            answerCheck = true;
            return;
        }

        int compCount = (count + 1) / 2; // 몇 개씩 비교

        nextNum: for (int num = 0; num < 3; num++) { // 1,2,3 순서로 우선순위
            if(answerCheck){
                return;
            }
            arr[count] = num123[num];
            for (int i = 1; i <= compCount; i++) { // 몇개씩 비교할 건지
                int pointer1 = count; // 5
                int pointer2 = count - i; // 2
                int samCount = 0;
                for (int j = 0; j < i; j++) { // 투 포인터 개수만큼 비교
                    if(arr[pointer1--] == arr[pointer2--]){
                        samCount++;
                    }
                }
                if(samCount == i){ // 같은 개수가 비교 개수랑 같으면 나쁜 수열
                    continue nextNum;
                }
            }
            // 다음으로
            getGoodSeq(count + 1);
        }
    }
}
