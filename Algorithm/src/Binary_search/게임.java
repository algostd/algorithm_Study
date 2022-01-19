package Binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1072 (실버3)
public class 게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String[] strArr = br.readLine().split(" ");
        double x = Double.parseDouble(strArr[0]);
        double y = Double.parseDouble(strArr[1]);
        double z = Math.floor(y * 100 / x);

        if (z >= 99) {
            System.out.println(-1);
        } else {
            int left = 1;
            int right = 1000000000;
            int mid = 0;
            while (left <= right) {
                mid = (right + left) / 2;
                if(Math.floor((y + mid) * 100 / (x + mid)) > z){
                    right = mid - 1;
                } else{
                    left = mid + 1;
                }
            }
            System.out.println(right + 1);
        }
    }
}
