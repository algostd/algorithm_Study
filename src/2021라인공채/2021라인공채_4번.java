import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Solution {

    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        s.solution(1000000);
    }

    public int[] solution(int n) {
        int[] answer = {};

        //배열생성
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i-1] = i;
        }

        //알고리즘
        int before = n;
        int len = n;
        int cnt = 0;
        while (len > 1) {
            cnt++;
            //주어진 배열의 길이를 나누었을때 나머지가 0이 되는 가장 작은 소수 찾기
            int p = 1;
            for (int i = 2; i <= len; i++) {
                if (isPrime(i) && (len%i == 0)) {
                    p = i;
                    break;
                }
            }

            before = len;
            len = len / p;
            Queue<Integer> qu = new LinkedList<Integer>();
            for (int i = 0; i < n; i++) {
                qu.add(arr[i]);
            }

            for (int k = 0; k*before < n; k++) {
                for (int i = 0; i < len; i++) {
                    for (int j = i; j < before; j += len) {
                        arr[j + k*before] = qu.poll();
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        return answer;
    }

    private boolean isPrime(int num) {
        boolean check = true;
        int end = (int) Math.sqrt(num);
        for (int i = 2; i <= end; i++) {
            if (num % i != 0) {
                check = false;
                break;
            }
        }
        return check;
    }

}
