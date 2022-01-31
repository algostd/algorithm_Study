package ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/3020 (골드5) (누적합)
public class 개똥벌레 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N, H;
    static int[] zong, suk;
    static int min, minCnt;

    public static void main(String[] args) throws IOException {
        getInfo();
        getAnswer();
        System.out.println(min + " " + minCnt);
    }

    private static void getAnswer() {
        min = Integer.MAX_VALUE;
        minCnt = 1;
        for (int i = 1; i <= H; i++) {
            int cnt = suk[i] + zong[H + 1 - i];
            if (min > cnt) {
                min = cnt;
                minCnt = 1;
            } else if (min == cnt) {
                minCnt++;
            }
        }
    }

    public static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        zong = new int[H + 1];
        suk = new int[H + 1];
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) { // 석순 (아래에서 나옴)
                suk[h]++;
            } else { // 종유석
                zong[h]++;
            }
        }
        for (int i = H; i > 1; i--) {
            suk[i - 1] += suk[i];
            zong[i - 1] += zong[i];
        }
    }
}
