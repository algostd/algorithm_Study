package Algorithm.src.Permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 신기한소수 {

    private static int N;
    private static int[] intArr;
    private static ArrayList<Integer> answerArr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        intArr = new int[N];
        answerArr = new ArrayList<>();
        int[] initArr = {2, 3, 5, 7};
        int[] nextArr = {1, 3, 7, 9};

        if (N == 1) {
            for (int i : initArr) {
                answerArr.add(i);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                intArr[0] = initArr[i];
                getPermutation(1, nextArr);
            }
        }
        for (Integer answer : answerArr) {
            System.out.println(answer);
        }

    }

    private static void getPermutation(int start, int[] nextArr) {
        for (int i = 0; i < 4; i++) {
            intArr[start] = nextArr[i]; // 다음자릿수에 값 넣음
            int nowInt = 0; // 다음 값
            for (int j = start, k = 0; j >= 0; j--,k++) {
                nowInt += Math.pow(10, j) * intArr[k];
            }

            // 다음값이 소수 인지 검사
            int half = (nowInt / 2) + 1;
            boolean sosuCheck = true;
            for (int j = 2; j <= half; j++) {
                if (nowInt % j == 0) {
                    sosuCheck = false;
                    break;
                }
            }
            if (sosuCheck) { // 소수 이면
                if (start + 1 == N) {
                    answerArr.add(nowInt);
                    continue;
                }
                getPermutation(start + 1, nextArr); // 다음자리 채우러 출발
            }

        }
    }
}
