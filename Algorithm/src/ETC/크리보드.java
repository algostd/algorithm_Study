package Algorithm.src.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 크리보드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] longArr = new long[N];
        longArr[0] = 1;
        for (int i = 1; i < N; i++) {

            long num = longArr[i - 1];
            long temp = num;
            for (int j = i + 2; j < N; j++) {
                temp += num;
                longArr[j] = Math.max(temp, longArr[j]);
            }

            longArr[i] = Math.max(longArr[i-1] + 1, longArr[i]);
        }
        System.out.println(longArr[N-1]);
    }
}
