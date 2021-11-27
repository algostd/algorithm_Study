import java.io.*;
import java.util.*;

public class Main {
    private static int mid, cnt, idx;
    private static PriorityQueue<Integer> qu1, qu2;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int M = Integer.parseInt(br.readLine());
            System.out.println(M/2+1);

            // 3
            // 9
            // 1 2 3 4 5 6 7 8 9

            //초기화
            qu1 = new PriorityQueue<>(Collections.reverseOrder());
            qu2 = new PriorityQueue<>();
            cnt = 0;
            idx = -1;
            mid = Integer.MAX_VALUE;

            int end = (M / 10) + 1;
            for (int i = 0; i < end; i++) {
                String[] input = br.readLine().split(" ");
                updateAndPrintMidNumber(Integer.parseInt(input[0]));

                //항상 qu1이 qu2보다 개수가 1개 더 많아야한다.
                int len = input.length;
                for (int j = 1; j < len; j++) {
                    updateAndPrintMidNumber(Integer.parseInt(input[j]));
                }
            }
            System.out.println();
        }
    }

    private static void updateAndPrintMidNumber(int num) {
        idx++;

        if (mid > num) {
            qu1.add(num);
        } else {
            qu2.add(num);
        }

        if (idx % 2 == 1) {
            return;
        }

        //조정
        if (qu2.size()+1 > qu1.size()) {
            qu1.add(qu2.poll());
        }
        if (qu2.size()+1 < qu1.size()) {
            qu2.add(qu1.poll());
        }
        mid = qu1.peek();

        //출력
        cnt++;
        System.out.print(mid + " ");
        if (cnt >= 10) {
            System.out.println();
            cnt = 0;
        }
    }
}