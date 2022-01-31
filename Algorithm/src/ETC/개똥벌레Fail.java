package ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/3020 (골드5) (누적합)
public class 개똥벌레Fail {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, H;
    static int[] zong, suk;

    public static void main(String[] args) throws IOException {
        getInfo();

        // 석순
        int min = Integer.MAX_VALUE;
        int minCnt = 2;
        for (int i = 0; i < H; i++) {
            int cnt = suk[i] + zong[H - i - 1];
            if (min > cnt) {
                min = cnt;
                minCnt = 1;
            } else if (min == cnt) {
                minCnt++;
            }
        }
        System.out.println(min + " " + minCnt);
    }

    public static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        zong = new int[H];
        suk = new int[H];
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) { // 석순 (아래에서 나옴)
                suk[h]++;
            } else { // 종유석
                zong[h]++;
            }
        }
        for (int i = H - 1; i > 0; i--) {
            suk[i] = suk[i - 1];
            zong[i] = zong[i - 1];
        }
    }
}
