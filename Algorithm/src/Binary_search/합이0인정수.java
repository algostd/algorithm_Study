package Algorithm.src.Binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 합이0인정수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] strArr;
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        int[] arr3 = new int[N];
        int[] arr4 = new int[N];

        for (int i = 0; i < N; i++) {
            strArr = br.readLine().split(" ");
            arr1[i] = Integer.parseInt(strArr[0]);
            arr2[i] = Integer.parseInt(strArr[1]);
            arr3[i] = Integer.parseInt(strArr[2]);
            arr4[i] = Integer.parseInt(strArr[3]);
        }

        int[] left = new int[N * N];
        int[] right = new int[N * N];
        int sum = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum = arr1[i] + arr2[j];
                left[leftIndex++] = sum;
                sum = arr3[i] + arr4[j];
                right[rightIndex++] = sum;
            }
        }

        Arrays.sort(left);
        Arrays.sort(right);

        long answer = 0;

        // leftIndex와 rightIndex는 이진 탐색을 위한 포인터
        for (int i = 0; i < N * N; i++) {
            leftIndex = 0;
            rightIndex = N * N - 1;
            int find = left[i] * -1;

            // 이진탐색
            while (leftIndex <= rightIndex) {
                int mid = (leftIndex + rightIndex) / 2;
                if (right[mid] == find) {
                    // left 개수 찾기
                    long leftCount = 1;
                    int index = i + 1;
                    while (index < N * N && left[i] == left[index]) {
                        leftCount++;
                        index++;
                    }
                    i = index - 1;

                    // right 개수 찾기
                    long rightCount = 1;

                    index = mid + 1;
                    while (index < N * N && right[mid] == right[index]) {
                        rightCount++;
                        index++;
                    }

                    index = mid - 1;
                    while (index >= 0 && right[mid] == right[index]) {
                        rightCount++;
                        index--;
                    }
                    // 가능한 개수의 조합 정답에 ++
                    answer += leftCount * rightCount;
                    break;

                } else if (right[mid] < find) {
                    leftIndex = mid + 1;
                } else {
                    rightIndex = mid - 1;
                }
            }
        }

        System.out.println(answer);
    }
}
