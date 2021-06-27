class Solution {
    static int[][] arr;
    static int INF = 10000;

    public static int solution(int n, int[][] results) {
        int answer = 0;
        arr = new int[n][n];

        //입력 [4,3]일 경우 arr[3,2] = 1
        for (int i = 0; i < results.length; i++) {
            int num1 = results[i][0];
            int num2 = results[i][1];

            arr[num1 - 1][num2 - 1] = 1;
        }
        //arr[i][j] != 1 이고 i != j 일경우 무한(=INF)를 넣는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = arr[i][j];
                if (i == j) {
                    continue;
                }
                if (num != 1) {
                    arr[i][j] = INF;
                }
            }
        }

        //플루이드 와샬 알고리즘
        // k = 거쳐가는 노드
        for (int k = 0; k < n; k++) {
            // i = 출발 노드
            for (int i = 0; i < n; i++) {
                // j = 도착 노드
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
        for (int num = 0; num < n; num++) {
            int input = 0;
            int output = 0;
            for (int i = 0; i < n; i++) {
                if (num == i) {
                    continue;
                }
                if(arr[i][num] != INF) {
                    input++;
                }
            }

            for (int j = 0; j < n; j++) {
                if (num == j) {
                    continue;
                }
                if(arr[num][j] != INF) {
                    output++;
                }
            }
            int sum = input + output;
            if (sum == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}