import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        int[] b = new int[N];
        int[] c = new int[N];
        int[] d = new int[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            a[i] = Integer.parseInt(input[0]);
            b[i] = Integer.parseInt(input[1]);
            c[i] = Integer.parseInt(input[2]);
            d[i] = Integer.parseInt(input[3]);
        }

        int[] ab = new int[N*N];
        int[] cd = new int[N*N];

        //ab 및 cd조합
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[i*N + j] = a[i] + b[j];
                cd[i*N + j] = c[i] + d[j];
            }
        }

        //오름차순 정렬
        Arrays.sort(ab);
        Arrays.sort(cd);

        int last = N*N;
        int p1 = 0;
        int p2 = N*N-1;
        long cnt = 0;
        while (p1 < last && p2 >= 0) {
            long num1 = ab[p1];
            long num2 = cd[p2];
            long sum = num1 + num2;
            if (sum < 0) {
                p1++;
            } else if (sum > 0) {
                p2--;
            } else if (sum == 0) {
                //p1++;
                //p2--;
                //중복이 있을 수 있다.

                long cnt1 = 0;
                long cnt2 = 0;
                while (p1 < last && ab[p1] == num1) {
                    cnt1++;
                    p1++;
                }
                while (p2 >= 0 && cd[p2] == num2) {
                    cnt2++;
                    p2--;
                }

                cnt += cnt1*cnt2;
            }
        }

        System.out.println(cnt);
    }

}