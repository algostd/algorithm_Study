package Floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11404 (골드4) (Floyd_Warshall)
public class 플로이드 {
    private static int N;
    private static int M;
    private static int[][] answer;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 10000000;

    public static void main(String[] args) throws IOException {
        getInfo();
        floyd();
        printAnswer();
    }

    private static void printAnswer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(answer[i][j] == MAX) {
                    answer[i][j] = 0;
                }
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void floyd() {
        // 거쳐가는 노드
        for (int k = 0; k < N; k++) {
            // 출발 노드
            for (int i = 0; i < N; i++) {
                // 도착노드
                for (int j = 0; j < N; j++) {
                    if (answer[i][j] > answer[i][k] + answer[k][j]) {
                        answer[i][j] = answer[i][k] + answer[k][j];
                    }
                }
            }
        }
    }

    private static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        answer = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(answer[i], MAX);
        }
        for (int i = 0; i < N; i++) {
            answer[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int price = Integer.parseInt(st.nextToken());
            if (answer[row][col] > price) {
                answer[row][col] = price;
            }
        }
    }
}