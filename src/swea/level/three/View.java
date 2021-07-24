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

            //입력
            List<Integer> list = new ArrayList<Integer>();
            int answer = 0;
            int N = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");

            for (String str : s) {
                int num = Integer.parseInt(str);
                list.add(num);
            }

            for (int cx = 2; cx < list.size() - 2; cx++) {
                int center = list.get(cx);
                int l2 = list.get(cx - 2);
                int l1 = list.get(cx - 1);
                int r2 = list.get(cx + 2);
                int r1 = list.get(cx + 1);

                int remain1 = center - l2;
                int remain2 = center - l1;
                int remain3 = center - r2;
                int remain4 = center - r1;

                int min = Math.min(remain1, Math.min(remain2, Math.min(remain3, remain4)));
                if (min > 0) {
                    answer += min;
                }

            }

            System.out.println("#" + test_case + " " + answer);
        }

        br.close();
    }
}