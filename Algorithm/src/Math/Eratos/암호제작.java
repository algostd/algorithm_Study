package Math.Eratos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1837
public class 암호제작 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int badPrime = 0;
    private static boolean[] notPrime;
    private static int K;
    private static String P;

    public static void main(String[] args) throws IOException {
        getInfo();
        getPrimeArr();
        getAnswer();
    }

    private static void getAnswer() {
        for (int i = 2; i < K; i++) {
            if(!notPrime[i]){ // 소수 일때
                int prime = i;
                int mod = 0;
                for (int j = 0; j < P.length(); j++) {
                    int num = P.charAt(j) - '0';
                    mod = (mod * 10 + num) % prime;
                }
                if (mod == 0) {
                    System.out.println("BAD " + prime);
                    return;
                }
            }
        }
        System.out.println("GOOD");
    }

    private static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        P = st.nextToken();
        K = Integer.parseInt(st.nextToken());
    }

    private static void getPrimeArr() {
        notPrime = new boolean[K];
        for (int i = 2; i < K; i++) {
            if (notPrime[i]) {
                continue;
            }
            for (int j = i * 2; j < K; j += i) {
                notPrime[j] = true;
            }
        }
    }
}
