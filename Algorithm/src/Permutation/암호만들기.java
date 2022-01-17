package Permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// C가지 중에 L개 골라서 순열을 만들어 내서 암호 추측하기

public class 암호만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answerSb = new StringBuilder();

    private static int N;
    private static int C;
    private static boolean[] visited;
    private static char[] charInfo;
    private static ArrayList<String> answerList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        getInfo();
        visited = new boolean[C];

        // 순열 만들기
        getPermutation(0, new char[N]);

        // 정렬
        Collections.sort(answerList);

        // 출력
        for (String s : answerList) {
            answerSb.append(s)
                    .append("\n");
        }
        System.out.println(answerSb);
    }

    private static boolean checkVowel(char c) {
        return c == 'a' || c == 'e' || c == 'u' || c == 'i' || c == 'o';
    }

    private static void getPermutation(int depth, char[] chars) {
        if (depth == N) { // 정답 확인 (목적지 인가?, 체크아웃)
            checkAnswer(chars);
            return;
        }

        for (int i = 0; i < C; i++) {
            if (visited[i]) {
                continue;
            }
            // 연결된 곳을 순회 (오름차순 가능하다면)
            if (depth == 0 || (depth > 0 && chars[depth - 1] < charInfo[i])) {
                chars[depth] = charInfo[i];
                visited[i] = true;
                getPermutation(depth + 1, chars);
                visited[i] = false;
            }
        }
    }

    private static void checkAnswer(char[] chars) {
        int vowelCnt = 0;
        int consonantCnt = 0;
        for (char c : chars) {
            if (checkVowel(c)) {
                vowelCnt++;
            } else {
                consonantCnt++;
            }
        }
        if (vowelCnt >= 1 && consonantCnt >= 2) {
            answerList.add(new String(chars));
        }
    }

    private static void getInfo() throws IOException {
        // N C
        String[] strArr;
        strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        C = Integer.parseInt(strArr[1]);

        // C개의 소문자
        strArr = br.readLine().split(" ");
        charInfo = new char[C];
        for (int i = 0; i < C; i++) {
            charInfo[i] = strArr[i].charAt(0);
        }
    }
}
