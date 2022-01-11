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

    private static void getLongestPreEqualSuf() {
        int equalCount = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (equalCount > 0 && pattern.charAt(i) != pattern.charAt(equalCount)) {
                equalCount = longestPreEqualSuf[equalCount - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(equalCount)) {
                equalCount++;
                longestPreEqualSuf[i] = equalCount;
            }
        }
    }

    private static int KMP() {
        int equalCount = 0;
        for (int i = 0; i < str.length(); i++) {
            while (equalCount > 0 && str.charAt(i) != pattern.charAt(equalCount)) {
                equalCount = longestPreEqualSuf[equalCount - 1];
            }

            if (str.charAt(i) == pattern.charAt(equalCount)) {
                if (equalCount == pattern.length() - 1) {
                    return 1;
                }
                equalCount++;
            }
        }
        return 0;
    }
}