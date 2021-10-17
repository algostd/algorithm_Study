import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        //알고리즘
        int cur = N; //맨 마지막 숫자부터 시작
        int[] answer = new int[N];

        int first = 0;
        int second = 1;
        int last = N-1;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(input[i]);
            if (num == 1) {
                answer[first] = cur;
                first = second;
                second++;
            } else if (num == 2) {
                //2번째 카드를 뽑을 때
                answer[second] = cur;
                second++;
            } else if (num == 3) {
                //마지막 카드를 뽑을 때
                answer[last] = cur;
                last--;
            }
            cur--;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //출력
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.write(" ");
        }
        bw.flush();
        bw.close();
    }
}