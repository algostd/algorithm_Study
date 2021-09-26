import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height2 = {4,2,0,3,2,5};
        System.out.println("answer: " + s.trap(height2));
    }

    public int trap(int[] height) {
        int answer = 0;

        PriorityQueue<Integer> qu = new PriorityQueue<Integer>();

        int n = height.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (height[i] != 0) {
                start = i;
                break;
            }
        }

        //알고리즘
        int max = 0;
        for (int i = start; i < n; i++) {

            //1. 최대값이 갱신되는 경우
            if (height[i] >= max) {
                while(!qu.isEmpty()) {
                    int num = qu.poll();
                    int gap = max - num;
                    answer += gap;
                }
                //빈큐
                max = height[i];
            } else if (height[i] > height[i-1]) {

                //2. 최대값이 갱신되지 않았지만 이전높이보다 높은경우
                //홀이 채워진다.
                while(!qu.isEmpty()) {
                    int num = qu.peek();
                    if (num >= height[i]) {
                        break;
                    }

                    int curGap = height[i] - num;
                    answer += curGap;
                    qu.poll();
                    qu.add(height[i]);
                }
            }

            qu.add(height[i]);
        }

        return answer;
    }
}
