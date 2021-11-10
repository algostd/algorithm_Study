import java.io.*;
import java.util.*;

public class Main {
    private static int[] LIS;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //6
        //4 2 6 3 1 5

        int[] arr = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        //LIS
        //이분탐색
        LIS = new int[N];
        LIS[0] = arr[0];
        int cur = 0;
        for (int k = 1; k < N; k++) {
            int num = arr[k];

            //만약 num이 LIS배열의 맨 뒤의 숫자보다 크다면 맨 뒤에 숫자를 넣는다.
            //이 경우에만 cur++을 한다.
            if (num > LIS[cur]) {
                LIS[cur+1] = num;
                cur++;
            } else {
                //이분탐색 진행
                //이 경우에는 숫자가 덮어 쓰여지기 때문에 cur을 갱신하지 않는다.
                int idx = binarySearch(0, cur, num);
                LIS[idx] = num;
            }
        }

        System.out.println(cur+1);
    }

    public static int binarySearch(int left, int right, int target) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (LIS[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}