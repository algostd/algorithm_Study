class Solution {
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int tmp = (brown - 4) / 2; // 10
        
        for (int sero = 1; sero <= tmp/2 + 1; sero++) {
            int garo = tmp - sero;
            if (sero*garo == yellow) {
                answer[0] = garo + 2;
                answer[1] = sero + 2;
                break;
            }
        }

        return answer;
    }
}