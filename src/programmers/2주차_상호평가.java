import java.util.*;

class Solution {
    public String solution(int[][] scores) {
        int n = scores.length;
        List<String> answerList = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            int[] arr = new int[101];
            int max = -1;
            int min = 101;
            int sum = 0;
            int tmp = n;
            for (int j = 0; j < n; j++) {
                int num = scores[j][i];
                max = Math.max(num, max);
                min = Math.min(num, min);
                sum += num;
                arr[num]++;
            }

            //유일한 최고점
            if (scores[i][i] == max && arr[max] == 1) {
                sum -= max;
                tmp--;
            }
            //유일한 최저점
            if (scores[i][i] == min && arr[min] == 1) {
                sum -= min;
                tmp--;
            }

            double score = (double) sum / tmp;
            answerList.add(change(score));
        }

        String answer = "";
        for (String score : answerList) {
            answer += score;
        }

        return answer;
    }

    private String change(double score) {
        if (score >= 90.0) {
            return "A";
        } else if (score >= 80.0) {
            return "B";
        } else if (score >= 70.0) {
            return "C";
        } else if (score >= 50.0) {
            return "D";
        } else {
            return "F";
        }
    }
}