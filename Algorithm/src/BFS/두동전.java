package Algorithm.src.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 두동전 {
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map;
    private static int N;
    private static int M;
    private static int answer;
    private static Queue<Pair> queue;

    static class Pair{
        int row;
        int col;
        int depth;

        public Pair(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        M = Integer.parseInt(strArr[1]);
        map = new int[N][M];
        queue = new LinkedList<>();
        char[] charArr;
        for (int row = 0; row < N; row++) {
            charArr = br.readLine().toCharArray();
            for (int col = 0; col < M; col++) {
                // 벽: 1 빈칸, 동전: 0
                if(charArr[col] == '.'){
                    map[row][col] = 0;
                } else if(charArr[col] == '#'){
                    map[row][col] = 1;
                } else {
                    map[row][col] = 0;
                    queue.add(new Pair(row, col, 0));
                }
            }
        }

        answer = - 1;
        outer: while (!queue.isEmpty()){
            Pair firstCoin = queue.poll();
            Pair secondCoin = queue.poll();
//            System.out.println("firstCoin = " + firstCoin.row + " " +  firstCoin.col + " " + firstCoin.depth);
//            System.out.println("secondCoin = " + secondCoin.row + " " +  secondCoin.col + " " + secondCoin.depth);

            if(firstCoin.depth == 10 || secondCoin.depth == 10){
                break;
            }

            for (int i = 0; i < 4; i++) {
                int tr1 = firstCoin.row + dr[i];
                int tc1 = firstCoin.col + dc[i];

                int tr2 = secondCoin.row + dr[i];
                int tc2 = secondCoin.col + dc[i];


                 if (isOut(tr1, tc1) && !isOut(tr2, tc2)){ // 1만 나간경우
                    answer = firstCoin.depth + 1;
                    break outer;
                } else if (!isOut(tr1, tc1) && isOut(tr2, tc2)){ // 2만 나간경우
                    answer = secondCoin.depth + 1;
                    break outer;
                } else if (!isOut(tr1, tc1) && !isOut(tr2, tc2)){ // 둘다 안나간경우
                    goNext(firstCoin.row, firstCoin.col, tr1, tc1, firstCoin.depth + 1);
                    goNext(secondCoin.row, secondCoin.col, tr2, tc2, secondCoin.depth + 1);
                }
            }
        }
        System.out.println("answer = " + answer);

    }

    public static boolean isOut(int row, int col){
        if(row >= N || col >= M || row < 0 || col < 0){
            return true;
        }
        return false;
    }

    public static void goNext(int nowRow, int nowCol, int nextRow, int nextCol, int nextDepth){
        if(map[nextRow][nextCol] == 0){
            queue.add(new Pair(nextRow,nextCol, nextDepth));
        } else {
            queue.add(new Pair(nowRow, nowCol, nextDepth));
        }
    }

}
