import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 출근기록 {
    private static char[] answer;
    private static int N;
    private static boolean check;
    private static boolean[][][][][] visited; // A-0 B-1 C-2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        N = S.length();
        check = false;
        visited = new boolean[50][50][50][3][3];
        int countA = 0;
        int countB = 0;
        int countC = 0;
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == 'A') {
                countA++;
            } else if (S.charAt(i) == 'B') {
                countB++;
            } else {
                countC++;
            }
        }
        getPermutation(new char[N], 0, countA, countB, countC, 0, 0);
        StringBuilder sb = new StringBuilder();
        if(check){
            for (char c : answer) {
                sb.append(c);
            }
        } else {
            sb.append(-1);
        }
        System.out.println(sb);

    }

    private static void getPermutation(char[] chars, int depth, int countA, int countB, int countC, int pre, int pre2) {
        if(check || visited[countA][countB][countC][pre][pre2]){
            return;
        }

        if (countA == 0 && countB == 0 && countC == 0) {
            check = true;
            answer = chars;
            return;
        }

        visited[countA][countB][countC][pre][pre2] = true;

        if (countC >= 1 && !check && pre != 2 && pre2 != 2) {
            chars[depth] = 'C';
            getPermutation(chars, depth + 1, countA, countB, countC - 1, 2 ,pre);
        }
        if (countB >= 1 && !check && pre != 1) {
            chars[depth] = 'B';
            getPermutation(chars, depth + 1, countA, countB - 1, countC, 1, pre);
        }
        if (countA >= 1 && !check) {
            chars[depth] = 'A';
            getPermutation(chars, depth + 1, countA - 1, countB, countC, 0, pre);
        }

    }
}
