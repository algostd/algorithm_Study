package Algorithm.src.DP;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        boolean check = false;
        Queue<Integer> zeroIndices = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                check = true;
                zeroIndices.add(i);
            }
        }

        int answer = Integer.MIN_VALUE;
        if (check) { // 0 있으면
            answer = 0;
            int start = 0;
            while (!zeroIndices.isEmpty()) { // 처음 ~ 0까지 그리고 0과 0사이의 모든 어레이들을 검사
                int end = zeroIndices.poll();
                if (end == start) {
                    start = end + 1;
                    continue;
                }
                int tempAnswer = getMax(nums, start, end - 1);
                if (answer < tempAnswer) {
                    answer = tempAnswer;
                }
                start = end + 1;
            }
            // 마지막 0부터 끝까지의 어레이를 검사
            if (start < nums.length) {
                int tempAnswer = getMax(nums, start, nums.length - 1);
                if (answer < tempAnswer) {
                    answer = tempAnswer;
                }
            }
        } else { // 0없으면
            answer = getMax(nums, 0, nums.length - 1);
        }


        return answer;
    }

    public static int getMax(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        // 음수 개수 찾기
        int count = 0;
        int firstMinus = -1; // 첫 번째 음수의 인덱스를 가리킴
        int lasttMinus = -1; // 두 번째 음수의 인덱스를 가리킴
        for (int i = start; i <= end; i++) {
            if (nums[i] < 0) {
                count++;
                if (firstMinus == -1) {
                    firstMinus = i;
                }
            }
        }
        for (int i = end; i >= start; i--) {
            if (nums[i] < 0) {
                lasttMinus = i;
                break;
            }
        }

        // 음수가 홀수 개이면
        int product = 1;
        if (count % 2 == 1) {
            int max = 1;
            for (int i = start; i < lasttMinus; i++) { // 1. 처음 ~ 마지막 음수 인덱스 - 1 까지
                max *= nums[i];
            }
            product = max;
            max = 1;
            for (int i = firstMinus + 1; i <= end; i++) { // 2. 처음 음수 인덱스 + 1 ~ 마지막 까지
                max *= nums[i];
            }
            if (max > product) {  // 1,2 비교
                product = max;
            }
        } else { // 짝수 개이면
            for (int i = start; i <= end; i++) {
                product *= nums[i];
            }
        }
        return product;
    }
}