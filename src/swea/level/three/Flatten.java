import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {

            // 입력
            int answer = 0;
            int N = Integer.parseInt(br.readLine()); // 덤프 횟수
            String[] s = br.readLine().split(" "); // 100개의 숫자

            int[] arr = new int[101];
            for (String str : s) {
                int num = Integer.parseInt(str);
                arr[num]++;
            }

            int minP = 1;
            int maxP = 100;
            while (N > 0) {
                if (maxP - minP <= 1) {
                    break;
                }

                while (true) {
                    if (arr[minP] != 0) {
                        break;
                    } else {
                        minP++;
                    }
                }
                while (true) {
                    if (arr[maxP] != 0) {
                        break;
                    } else {
                        maxP--;
                    }
                }

                arr[maxP]--;
                arr[maxP - 1]++;

                arr[minP]--;
                arr[minP + 1]++;

                N--;
            }

            //마지막에 한번더 실행한다.
            while (true) {
                if (arr[minP] != 0) {
                    break;
                } else {
                    minP++;
                }
            }
            while (true) {
                if (arr[maxP] != 0) {
                    break;
                } else {
                    maxP--;
                }
            }

            answer = maxP - minP;

            bw.write("#");
            bw.write(String.valueOf(test_case));
            bw.write(" ");
            bw.write(String.valueOf(answer));
            bw.write("\r\n");
            bw.flush();

            // System.out.println("#" + test_case + " " + answer);
        }

        br.close();
    }

}
