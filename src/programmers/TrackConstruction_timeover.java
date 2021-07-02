import java.util.*;

class Solution {
    static int[][] board;
    static int[][] visited;
    static int len, tmp;
    static int min = 10000000;

    public static int solution(int[][] board) {
        int answer = 0;

        Solution.board = board;
        len = board.length;
        visited = new int[len][len];

        dfs(0,0,"R",0);
        dfs(0,0,"D",0);

        tmp = 0;
        answer = min;
        return answer;
    }

    static void dfs(int ax, int ay, String dir, int cnt) {
        if (ax == len - 1 && ay == len - 1) {
            if (cnt < min) {
                min = cnt;
            }
            return;
        }
        if (tmp > 0) {
            return;
        }
        if (cnt >= min) {
            return;
        }
        if (ax < 0 || ax >= len || ay < 0 || ay >= len) {
            return;
        }

        visited[ax][ay] = 1; // 방문처리
        //오른쪽 방향
        if (dir.equals("R")) {
            //오른쪽이 비었을 경우
            if (ay + 1 < len && visited[ax][ay + 1] == 0 && board[ax][ay + 1] == 0) {
                dfs(ax, ay + 1, "R", cnt + 100);
                visited[ax][ay + 1] = 0;
            }
            // 아래쪽이 비었을 경우
            if (ax + 1 < len && visited[ax + 1][ay] == 0 && board[ax + 1][ay] == 0) {
                dfs(ax + 1, ay, "D", cnt + 600);
                visited[ax + 1][ay] = 0;
            }
            // 위쪽이 비었을 경우
            if (ax - 1 >= 0 && visited[ax - 1][ay] == 0 && board[ax - 1][ay] == 0) {
                dfs(ax - 1, ay, "U", cnt + 600);
                visited[ax - 1][ay] = 0;
            }
        }
        //왼쪽 방향
        if (dir.equals("L")) {
            //왼쪽이 비었을 경우
            if (ay - 1 >= 0 && visited[ax][ay - 1] == 0 && board[ax][ay - 1] == 0) {
                dfs(ax, ay - 1, "L", cnt + 100);
                visited[ax][ay - 1] = 0;
            }
            // 아래쪽이 비었을 경우
            if (ax + 1 < len && visited[ax + 1][ay] == 0 && board[ax + 1][ay] == 0) {
                dfs(ax + 1, ay, "D", cnt + 600);
                visited[ax + 1][ay] = 0;
            }
            // 위쪽이 비었을 경우
            if (ax - 1 >= 0 && visited[ax - 1][ay] == 0 && board[ax - 1][ay] == 0) {
                dfs(ax - 1, ay, "U", cnt + 600);
                visited[ax - 1][ay] = 0;
            }
        }
        //위쪽 방향
        if (dir.equals("U")) {
            // 위쪽이 비었을 경우
            if (ax - 1 >= 0 && visited[ax - 1][ay] == 0 && board[ax - 1][ay] == 0) {
                dfs(ax - 1, ay, "U", cnt + 100);
                visited[ax - 1][ay] = 0;
            }
            // 오른쪽이 비었을 경우
            if (ay + 1 < len && visited[ax][ay + 1] == 0 && board[ax][ay + 1] == 0) {
                dfs(ax, ay + 1, "R", cnt + 600);
                visited[ax][ay + 1] = 0;
            }
            // 왼쪽이 비었을 경우
            if (ay - 1 >= 0 && visited[ax][ay - 1] == 0 && board[ax][ay - 1] == 0) {
                dfs(ax, ay - 1, "L", cnt + 600);
                visited[ax][ay - 1] = 0;
            }
        }
        //아래쪽 방향
        if (dir.equals("D")) {
            // 아래쪽이 비었을 경우
            if (ax + 1 < len && visited[ax + 1][ay] == 0 && board[ax + 1][ay] == 0) {
                dfs(ax + 1, ay, "D", cnt + 100);
                visited[ax + 1][ay] = 0;
            }
            // 오른쪽이 비었을 경우
            if (ay + 1 < len && visited[ax][ay + 1] == 0 && board[ax][ay + 1] == 0) {
                dfs(ax, ay + 1, "R", cnt + 600);
                visited[ax][ay + 1] = 0;
            }
            // 왼쪽이 비었을 경우
            if (ay - 1 >= 0 && visited[ax][ay - 1] == 0 && board[ax][ay - 1] == 0) {
                dfs(ax, ay - 1, "L", cnt + 600);
                visited[ax][ay - 1] = 0;
            }
        }
    }
}