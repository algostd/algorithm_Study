import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] arr;
    private static boolean final_check;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        final_check = false;

        Main m = new Main();
        m.dfs(0);

        String answer = "";
        for (int  i = 0; i < N; i++) {
            answer += arr[i];
        }

        System.out.println(answer);
    }

    //1. 해당 인덱스에 1,2,3 순서대로 숫자를 넣어본다.
    //2. 나쁜 순열인지 검사한다.
    //3. 나쁜 순열이 아니라면 다음 인덱스로 넘어간다.
    //4. 마지막 인덱스까지 모두 통과했을 경우 함수를 종료한다.
    private void dfs(int idx) {
        if (idx >= N) {
            final_check = true;
            return;
        }

        if (final_check == false) {
            arr[idx] = 1;
            //조건검사
            if (check_goodPermutation(idx) == true) {
                dfs(idx+1);
            }
        }

        if (final_check == false) {
            arr[idx] = 2;
            //조건검사
            if (check_goodPermutation(idx) == true) {
                dfs(idx+1);
            }
        }

        if (final_check == false) {
            arr[idx] = 3;
            //조건검사
            if (check_goodPermutation(idx) == true) {
                dfs(idx+1);
            }
        }
    }

    private boolean check_goodPermutation(int idx) {
        if (idx == 0) {
            return true;
        }

        int end = (idx+1)/2;
        for (int i = 1; i <= end; i++) {
            int start1 = idx;
            int start2 = idx-i;
            boolean check = true;
            for (int j = 1; j <= i; j++) {
                if (arr[start1] != arr[start2]) {
                    check = false;
                    break;
                }
                start1--;
                start2--;
            }

            if (check == true) {
                return false;
            }
        }
        return true;
    }
}
