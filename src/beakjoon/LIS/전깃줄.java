import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //8
        //1 8
        //3 9
        //2 2
        //4 1
        //6 4
        //10 10
        //9 7
        //7 6

        int[] arr = new int[N];
        Map<Integer, Integer> map = new HashMap<>();

        //배열에는 index를 담는다.
        //맵에는 index에 대응되는 값을 담는다.
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int num1 = Integer.parseInt(input[0]);
            int num2 = Integer.parseInt(input[1]);
            arr[i] = num1;
            map.put(num1, num2);
        }

        //오름차순 정렬
        Arrays.sort(arr);

        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            int val = map.get(arr[i]);
            arr2[i] = val;
        }

        //LIS
        int max = 0;
        int[] length = new int[N];
        for (int k = 0; k < N; k++) {
            length[k] = 1;
            for (int i = 0; i < k; i++) {
                if (arr2[i] < arr2[k]) {
                    length[k] = Math.max(length[i]+1, length[k]);
                }
            }
            max = Math.max(length[k], max);
        }

        System.out.println(N - max);
    }
}