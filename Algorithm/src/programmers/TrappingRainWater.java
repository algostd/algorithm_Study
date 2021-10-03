import java.util.Arrays;

// https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {
    public int trap(int[] height) {
        int answer = 0;
        int N = height.length;
        int[] left = new int[N];
        int[] right = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) { // left
            if (max < height[i]) {
                max = height[i];
            }
            left[i] = max;
        }
        max = 0;
        for (int i = N-1; i >= 0; i--) { // right
            if (max < height[i]) {
                max = height[i];
            }
            right[i] = max;
        }
        // 정답 도출
        for (int i = 0; i < height.length; i++) {
            answer += Math.min(right[i], left[i]) - height[i];
        }
        return answer;
    }
}
