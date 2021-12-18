import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬만들기 {
    private static String s;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        N = s.length();
        int answer = N * 2;
        int start = 0;
        // 검사 시작 통째로 -> 왼쪽에 인덱스 하나씩 빼면서 검사
        while (start < N) {
            if (checkPalindrome(start)) {
                answer = N + start;
                break;
            }
            start++;
        }

        System.out.println(answer);
    }
    public static boolean checkPalindrome(int left) {
        int right = N - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
