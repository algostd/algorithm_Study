import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int N, K, remain;
    private static boolean[] visited;
    private static List<Character> word;
    private static String[] arr;
    private static int sum;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int answer = 0;
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        remain = K -5;

        arr = new String[N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            s = s.substring(4, s.length() - 4);
            arr[i] = s;
        }

        if (remain < 0) {
            System.out.println(0);
            return;
        }

        visited = new boolean[123];
        visited[97] = true;
        visited[99] = true;
        visited[105] = true;
        visited[110] = true;
        visited[116] = true;

        word = new ArrayList<Character>();
        word.add('a');
        word.add('c');
        word.add('i');
        word.add('n');
        word.add('t');

        sum = 0;
        max = Integer.MIN_VALUE;

        Main s = new Main();
        s.recur(98);

        answer = max;

        System.out.println(answer);

    }

    private void recur(int cur) {
        if (word.size() == K) {
            //검사
            check();
            return;
        }

        for (int i = cur; i <= 122; i++) {
            if (visited[i]) {
                continue;
            }

            char ch = (char) i;
            word.add(ch);
            visited[i] = true;
            recur(i + 1);
            word.remove(word.size() - 1);
            visited[i] = false;
        }
    }

    private void check() {
        for (String s : arr) {
            char[] ch_arr = s.toCharArray();

            boolean checkContains = true;
            for (char ch : ch_arr) {
                if (!word.contains(ch)) {
                    checkContains = false;
                    break;
                }
            }
            if (checkContains == true) {
                sum++;
            }
        }

        if (sum > max) {
            max = sum;
        }
        sum = 0;
    }
}