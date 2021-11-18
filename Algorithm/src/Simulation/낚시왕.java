package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class 낚시왕 {

    static class Shark {
        int row;
        int col;
        int speed;
        int direct;
        int size;

        public Shark(int row, int col, int speed, int direct, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direct = direct;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String[] strArr;
        int answer = 0;

        strArr = br.readLine().split(" ");
        Map<String, Shark> sharkMap = new HashMap<>();

        int R = Integer.parseInt(strArr[0]);
        int C = Integer.parseInt(strArr[1]);
        int M = Integer.parseInt(strArr[2]);

        int[][] map = new int[R][C];

        for (int i = 0; i < M; i++) {
            strArr = br.readLine().split(" ");
            int row = Integer.parseInt(strArr[0]) - 1;
            int col = Integer.parseInt(strArr[1]) - 1;
            int speed = Integer.parseInt(strArr[2]);
            int direct = Integer.parseInt(strArr[3]);
            int size = Integer.parseInt(strArr[4]);
            if (direct == 1 || direct == 2) {
                speed = speed % (2 * (R - 1));
            } else {
                speed = speed % (2 * (C - 1)); // 제자리로 돌아오는 것보다 더 길게하는경우 줄여줌
            }
            sharkMap.put("r" + row + "c" + col, new Shark(row, col, speed, direct, size));
            map[row][col]++;
        }

        for (int col = 0; col < C; col++) { // 1. 상어왕 이동

            // 2. 낚시왕이 있는 열에 땅과 제일 가까운 상어를 잡는다.
            for (int row = 0; row < R; row++) {
                if (map[row][col] != 0) {
                    String key = "r" + row + "c" + col;
                    Shark shark = sharkMap.get(key);
                    answer += shark.size;
                    sharkMap.remove(key);
                    break;
                }
            }

            // 상어가 이동한다.
            // d: 1인경우 위, 2인경우 아래, 3인경우 오른쪽, 4인 경우 왼쪽
            // 1,2인경우 row를 확인, 3,4인경우 col확인
            Map<String, Shark> temp = new HashMap<>();
            for (int[] ints : map) {
                Arrays.fill(ints, 0);
            }
            for (Shark shark : sharkMap.values()) {
                // 이동
                int sRow = shark.row;
                int sCol = shark.col;
                int sDirect = shark.direct;
                for (int i = 0; i < shark.speed; i++) {
                    if (sDirect == 1) { // 위
                        if (sRow == 0) {
                            sRow = 1;
                            sDirect = 2;
                        } else {
                            sRow--;
                        }
                    } else if (sDirect == 2) { // 아래
                        if (sRow == R - 1) {
                            sRow = R - 2;
                            sDirect = 1;
                        } else {
                            sRow++;
                        }
                    } else if (sDirect == 3) { // 오른쪽
                        if (sCol == C - 1) {
                            sCol = C - 2;
                            sDirect = 4;
                        } else {
                            sCol++;
                        }
                    } else if (sDirect == 4) { // 왼쪽
                        if (sCol == 0) {
                            sCol = 1;
                            sDirect = 3;
                        } else {
                            sCol--;
                        }
                    }
                }
                String key = "r" + sRow + "c" + sCol;
                if (temp.containsKey(key)) { // 이미 있는경우
                    if (temp.get(key).size < shark.size) {
                        temp.put(key, new Shark(sRow, sCol, shark.speed, sDirect, shark.size));
                    }
                } else {
                    temp.put(key, new Shark(sRow, sCol, shark.speed, sDirect, shark.size));
                }
                map[sRow][sCol] = 1;
            }
            sharkMap = temp;
        }

        System.out.println(answer);
    }
}
