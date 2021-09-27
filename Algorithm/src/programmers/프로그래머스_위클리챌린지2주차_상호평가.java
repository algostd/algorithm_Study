public class 프로그래머스_위클리챌린지2주차_상호평가 {
    public String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();
        int N = scores.length;
        int[][] dp = new int[2][2]; // 0행 max, 1행 min, 0열 값, 1열 개수
        for (int col = 0; col < N; col++) {
            dp[0][0] = -1;
            dp[1][0] = 101;
            int sum = 0;
            for (int row = 0; row < N; row++) {
                // 최대값
                if (scores[row][col] > dp[0][0]) {
                    dp[0][0] = scores[row][col];
                    dp[0][1] = 1;
                } else if (scores[row][col] == dp[0][0]) {
                    dp[0][1]++;
                }
                // 최소값
                if (scores[row][col] < dp[1][0]) {
                    dp[1][0] = scores[row][col];
                    dp[1][1] = 1;
                } else if (scores[row][col] == dp[1][0]) {
                    dp[1][1]++;
                }

                sum += scores[row][col];
            }
            // 본인값과 비교
            int avg = 0;
            if (scores[col][col] == dp[0][0] && dp[0][1] == 1
                    || scores[col][col] == dp[1][0] && dp[1][1] == 1) { // 자기 점수 제외
                avg = sum - scores[col][col];
                avg /= (N - 1);
            } else { // 자기 점수 포함
                avg = sum / N;
            }

            // 학점 구하기
            if (avg >= 90) {
                sb.append("A");
            } else if (avg >= 80) {
                sb.append(("B"));
            } else if (avg >= 70) {
                sb.append("C");
            } else if (avg >= 50) {
                sb.append("D");
            } else {
                sb.append("F");
            }
        }

        return sb.toString();
    }
}
