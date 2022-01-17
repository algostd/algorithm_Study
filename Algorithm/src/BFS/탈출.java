package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 탈출 {

    static class Info {
        int row;
        int col;
        int count;

        public Info(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer;

    private static int R;
    private static int C;
    private static char[][] map;
    private static boolean[][] visited;

    private static Queue<Info> waters = new LinkedList<>();
    private static Queue<Info> goQueue = new LinkedList<>();

    private static int[] dr = new int[]{-1, 1, 0, 0};
    private static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        getInfo();
        visited = new boolean[R][C];

        answer = -1;
        getAnswer();

        if (answer == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    private static void getAnswer() {
        for (int count = 0; count < 2501; count++) {
            doBfs(waters, 0, count);
            boolean result = doBfs(goQueue, 1, count);

            // for exit
            if (result || goQueue.isEmpty()) {
                break;
            }
        }
    }

    private static boolean doBfs(Queue<Info> queue, int token, int count) {
        while (!queue.isEmpty()) {
            if (queue.peek().count > count) {
                break;
            }
            Info now = queue.poll();

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int tr = now.row + dr[i];
                int tc = now.col + dc[i];

                // if: 물 BFS
                // else if: 고슴도치가 D 발견
                // else if: 고슴도치 BFS
                if (tr >= 0 && tc >= 0 && tr < R && tc < C
                        && token == 0
                        && map[tr][tc] == '.') {
                    map[tr][tc] = '*';
                    queue.add(new Info(tr, tc, now.count + 1));
                } else if (tr >= 0 && tc >= 0 && tr < R && tc < C
                        && token == 1
                        && !visited[tr][tc]
                        && map[tr][tc] == 'D') {
                    answer = ++count;
                    return true;
                } else if (tr >= 0 && tc >= 0 && tr < R && tc < C
                        && token == 1
                        && !visited[tr][tc]
                        && map[tr][tc] == '.') {
                    visited[tr][tc] = true;
                    queue.add(new Info(tr, tc, now.count + 1));
                }
            }
        }
        return false;
    }

    private static void getInfo() throws IOException {
        String[] strArr;
        String str;
        strArr = br.readLine().split(" ");
        R = Integer.parseInt(strArr[0]);
        C = Integer.parseInt(strArr[1]);

        map = new char[R][C];

        for (int row = 0; row < R; row++) {
            str = br.readLine();
            for (int col = 0; col < C; col++) {
                map[row][col] = str.charAt(col);
                if (map[row][col] == '*') {
                    waters.add(new Info(row, col, 0));
                } else if (map[row][col] == 'S') {
                    goQueue.add(new Info(row, col, 0));
                }
            }
        }
    }
}
