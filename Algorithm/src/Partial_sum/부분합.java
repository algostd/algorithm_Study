package Algorithm.src.Partial_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/1806
public class 부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]);
        int S = Integer.parseInt(strArr[1]);
        strArr = br.readLine().split(" ");

        int[] intArr = new int[N];
        int sum = 0;
        int nowInt = 0;
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        // 시작
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            sum += intArr[i];
            while (nowInt <= i) {
                int count = 1;
                if (sum >= S) { // 합이 S 이상이면 갱신하고 기준점을 옮김, 기준점 옮겨도 while문 안에서 반복적으로감사
                    count += i - nowInt;
                    answer = Math.min(answer, count);
                    sum -= intArr[nowInt];
                    nowInt++;
                } else {
                    break;
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
