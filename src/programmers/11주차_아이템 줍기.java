import java.util.*;

public class Solution {
    private static int[][] arr, border, visited;
    private static int[] l = {-1,1,0,0};
    private static int[] r = {0,0,-1,1};
    private static int finalX, finalY, min;

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        s.solution(rectangle,1,3,7,8);
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        finalX = itemX * 2;
        finalY = itemY * 2;
        min = 100000;

        arr = new int[102][102]; //2배 스케일업

        //모든 직사각형을 2배로해서 그 안의 점들을 모두 1로 만든다.
        for (int[] curRec : rectangle) {
            int x1 = curRec[0] * 2;
            int y1 = curRec[1] * 2;
            int x2 = curRec[2] * 2;
            int y2 = curRec[3] * 2;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    arr[i][j] = 1;
                }
            }
        }

        //직사각형의 테두리를 제외한 점들을 모두 0으로 만든다.
        for (int[] curRec : rectangle) {
            int x1 = curRec[0] * 2;
            int y1 = curRec[1] * 2;
            int x2 = curRec[2] * 2;
            int y2 = curRec[3] * 2;
            for (int i = x1+1; i <= x2-1; i++) {
                for (int j = y1+1; j <= y2-1; j++) {
                    arr[i][j] = 0;
                }
            }
        }

        //test
        /*
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 22; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
         */

        visited = new int[102][102];
        dfs(characterX * 2, characterY * 2, 0);
        answer = min/2;

        return answer;
    }

    private void dfs (int cx, int cy, int cnt) {
        if (cx == finalX && cy == finalY) {
            min = Math.min(cnt, min);
            return;
        }

        visited[cx][cy] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = cx + l[i];
            int ny = cy + r[i];
            if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && arr[nx][ny] == 1 && visited[nx][ny] == 0) {
                dfs(nx, ny, cnt + 1);
            }
        }
    }

}

class Node {
    int x;
    int y;

    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}