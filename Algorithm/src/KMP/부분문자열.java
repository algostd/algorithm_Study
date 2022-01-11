package KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 부분문자열 {
    private static int[] longestPreEqualSuf;
    private static String str, pattern;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        pattern = br.readLine();

        longestPreEqualSuf = new int[pattern.length()];
        getLongestPreEqualSuf();
        int result = KMP();
        System.out.println(result);
    }

    // Pattern 문자열 기준, 0부터 해당 인덱스(i)까지 prefix 와 suffix 가 최대 몇 개 일치하는지
    private static void getLongestPreEqualSuf() {
        int equalLength = 0;
        for (int i = 0; i < pattern.length(); i++) {
            // prefix 와 suffix 가 어긋나는 경우, 일치했던 곳들을 확인학 roll back
            // 어디까지 ? => 어긋난 문자와 일치하는 곳까지 or 0까지
            while (equalLength > 0 && pattern.charAt(equalLength) != pattern.charAt(i)) {
                equalLength = longestPreEqualSuf[equalLength - 1];
            }

            // 인덱스의 문자와 equalLength 부분의 문자가 같으면
            if (pattern.charAt(i) == pattern.charAt(equalLength)) {
                equalLength++;
                longestPreEqualSuf[i] = equalLength;
            }
        }
    }

    private static int KMP() {
        int equalLength = 0;
        for (int i = 0; i < str.length(); i++) {
            // 찾는 문자가 다르면 roll back
            while (equalLength > 0 && str.charAt(i) != pattern.charAt(equalLength)) {
                equalLength = longestPreEqualSuf[equalLength - 1];
            }
            // 찾는 문자가 같으면
            if (str.charAt(i) == pattern.charAt(equalLength)) {
                if (equalLength == pattern.length() - 1) {
                    return 1;
                }
                equalLength++;
            }
        }
        return 0;
    }
}