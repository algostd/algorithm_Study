class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int answer = 1;

        char[] chArr = s.toCharArray();
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        for (int i = 0; i < len; i++) {
            int cnt = 1;
            int[] aschi = new int[10001];

            char ch1 = chArr[i];
            int num1 = (int)ch1;
            aschi[num1]++;

            for (int j = i + 1; j < len; j++) {
                char ch2 = chArr[j];
                int num2 = (int)ch2;

                if (aschi[num2] == 0) {
                    aschi[num2]++;
                    cnt++;
                } else {
                    break;
                }
            }
            if (answer < cnt) {
                answer = cnt;
            }

        }

        return answer;
    }
}