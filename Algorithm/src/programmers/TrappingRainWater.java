import java.util.Arrays;

// https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {
    public int trap(int[] height) {
        int answer = 0;
        int[] dp = new int[100001];
        Arrays.fill(dp, -1);
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            if(h == 0){
                continue;
            }
            for (int j = 1; j <= h; j++) {
                if(dp[j] != -1){ // 방문했던 적이 있다면
                    answer += i - dp[j] - 1;
                }
                dp[j] = i;
            }
        }
        return answer;
    }
}
