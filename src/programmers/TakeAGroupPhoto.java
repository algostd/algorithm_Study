import java.util.*;

public class Solution {
    static int N, total;
    static boolean[] isSeleted;
    static int[] numbers;
    static String[] data;
    static HashMap<Character, Integer> mp;

    public static int solution(int n, String[] data) {
        N = n;
        isSeleted = new boolean[8];
        numbers = new int[8];
        total = 0;
        Solution.data = data;

        mp = new HashMap<Character, Integer>();
        mp.put('A', 0);
        mp.put('C', 1);
        mp.put('F', 2);
        mp.put('J', 3);
        mp.put('M', 4);
        mp.put('N', 5);
        mp.put('R', 6);
        mp.put('T', 7);

        permutation(0);

        int answer = total;
        return answer;
    }

    public static void permutation(int cnt) {
        if(cnt == 8) {
            //조건검사 알고리즘
            if(isRight(numbers)) {
                total++;
            }

            return;
        }
        for (int i = 0; i < 8; i++) {
            if (isSeleted[i]) continue;
            numbers[cnt] = i;
            isSeleted[i] = true;
            permutation(cnt + 1);
            isSeleted[i] = false;
        }
    }

    private static boolean isRight(int[] arr) {

        boolean result = false;
        for (int i = 0; i < N; i++) {
            String s = data[i];
            char[] chArr = s.toCharArray();

            char ch1 = chArr[0];
            char ch2 = chArr[2];

            int num1 = mp.get(ch1);
            int num2 = mp.get(ch2);
            int idx1 = -1;
            int idx2 = -1;
            for (int a = 0; a < 8; a++) {
                if(arr[a] == num1) {
                    idx1 = a;
                }
                if(arr[a] == num2) {
                    idx2 = a;
                }
            }
            int distance = Math.abs(idx1 - idx2) - 1;

            char check = chArr[3];
            int checkNum = chArr[4] - '0';
            if (check == '=') {
                if (distance == checkNum) {
                    result = true;
                } else {
                    result = false;
                }
            }else if (check == '>') {
                if (distance > checkNum) {
                    result = true;
                } else {
                    result = false;
                }
            }else if (check == '<') {
                if (distance < checkNum) {
                    result = true;
                } else {
                    result = false;
                }
            }

            if (result == false) {
                break;
            }
        }

        return result;
    }
}