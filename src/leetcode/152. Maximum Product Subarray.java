import java.util.*;

public class Solution {
    static int max;

    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        max = 0;
        //1.0의 유무를 검사한다.
        List<Integer> zeroList = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                zeroList.add(i);
            }
        }

        //0이 없을 경우
        if (zeroList.size() == 0) {
            if (nums.length == 1 && nums[0] <= 0) {
                return max;
            }

            //검사
            Solution s = new Solution();
            s.calculate(nums);
        }
        //0이 있을 경우
        if (zeroList.size() > 0) {
            //배열자르기
            for (int i = 0; i <= zeroList.size(); i++) {
                int[] cur_arr;
                if (i == 0) {
                    cur_arr = Arrays.copyOfRange(nums, 0, zeroList.get(i));
                } else if (i == zeroList.size()) {
                    cur_arr = Arrays.copyOfRange(nums, zeroList.get(i-1)+1, nums.length);
                } else {
                    cur_arr = Arrays.copyOfRange(nums, zeroList.get(i-1)+1, zeroList.get(i));
                }

                //검사
                Solution s = new Solution();
                s.calculate(cur_arr);
            }
        }

        return max;
    }

    private void calculate(int[] arr) {
        //1.들어온 배열의 음수의 개수를 구한다.
        //    1) 배열의 크기 및 음수의 개수가 1개일 경우 => 0
        //    2) 음수의 개수가 짝수개일 경우 => 모두 곱한다.
        //    3) 음수의 개수가 홀수개일 경우
        int m = 0;
        int product = 1;
        int len = arr.length;
        List<Integer> mList = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            int num = arr[i];
            if (num < 0) {
                m++;
                mList.add(i);
            }
            product *= num;
        }

        if (len == 0) {
            return;
        }
        if (len == 1 && m == 1) {
            return;
        }

        if (m % 2 == 0) {
            max = Math.max(product, max);
        } else if (m % 2 == 1) {
            int first = mList.get(0);
            int last = mList.get(mList.size() - 1);

            //product값 초기화
            product = 1;
            for (int i = first+1; i < len; i++) {
                product *= arr[i];
            }
            max = Math.max(product, max);

            //product값 초기화
            product = 1;
            for (int i = 0; i < last; i++) {
                product *= arr[i];
            }
            max = Math.max(product, max);
        }
    }

}