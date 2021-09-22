import java.io.IOException;
import java.util.*;

public class Solution {
    private static int n, answer;
    private static List<ArrayList<Node>> boardList, tableList;
    private static int[] l = {-1,1,0,0};
    private static int[] r = {0,0,-1,1};
    private static int[][] visited, board, curTable;

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length; //한 변의 길이
        board = game_board;
        curTable = table;
        answer = 0;

        //game_board의 빈칸을 인접리스트에 담는다.
        boardList = new ArrayList<ArrayList<Node>>();
        visited = new int[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //아직 방문하지 않았고 빈칸일 경우
                if (visited[i][j] == 0 && board[i][j] == 0) {
                    boardList.add(new ArrayList<Node>());
                    dfs_board(i, j, cnt);
                    cnt++;
                }
            }
        }

        //1.비교한다
        //2.회전한다.
        //1~2 과정을 4번 반복한다.
        //마지막엔 회전을 생략한다.
        for (int k = 0; k < 4; k++) {
            compare();
            if (k == 3) {
                break;
            }

            //회전한다.
            int[][] tmp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tmp[n-1-j][i] = curTable[i][j];
                }
            }

            //회전한 테이블 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    curTable[i][j] = tmp[i][j];
                }
            }
        }

        return answer;
    }

    private void dfs_board(int cx, int cy, int cnt) {
        visited[cx][cy] = 1;
        boardList.get(cnt).add(new Node(cx, cy));

        for (int i = 0; i < 4; i++) {
            int nx = cx + l[i];
            int ny = cy + r[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == 0 && board[nx][ny] == 0) {
                dfs_board(nx, ny, cnt);
            }
        }
    }

    private void dfs_table(int cx, int cy, int cnt) {
        visited[cx][cy] = 1;
        tableList.get(cnt).add(new Node(cx, cy));

        for (int i = 0; i < 4; i++) {
            int nx = cx + l[i];
            int ny = cy + r[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == 0 && curTable[nx][ny] == 1) {
                dfs_table(nx, ny, cnt);
            }
        }
    }

    private void compare() {
        //table의 블럭을 인접리스트에 담는다.
        tableList = new ArrayList<ArrayList<Node>>();
        visited = new int[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //아직 방문하지 않았고 블럭일 경우
                if (visited[i][j] == 0 && curTable[i][j] == 1) {
                    tableList.add(new ArrayList<Node>());
                    dfs_table(i, j, cnt);
                    cnt++;
                }
            }
        }

        //boardList와 tableList를 비교한다.
        for (int i = 0; i < boardList.size(); i++) {
            ArrayList<Node> curBoardList = boardList.get(i);
            for (int j = 0; j < tableList.size(); j++) {
                ArrayList<Node> curTableList = tableList.get(j);

                //크기가 같을 경우 비교한다.
                if (curBoardList.size() == curTableList.size()) {
                    //서로 간의 위치차이 저장
                    Node board1 = curBoardList.get(0);
                    Node table1 = curTableList.get(0);
                    int gapX = board1.getX() - table1.getX();
                    int gapY = board1.getY() - table1.getY();

                    //모든 노드가 gapX, gapY만큼 차이가 나야 한다.
                    boolean check = true;
                    for (int k = 0; k < curBoardList.size(); k++) {
                        Node curBoard = curBoardList.get(k);
                        Node curTable = curTableList.get(k);

                        if ((curBoard.getX()-curTable.getX() != gapX) || (curBoard.getY()-curTable.getY() != gapY)) {
                            check = false;
                            break;
                        }
                    }
                    if (check == true) {
                        //채워넣은 board의 빈칸 더하기
                        answer += boardList.get(i).size();
                        boardList.remove(i);
                        i--;

                        //사용한 블럭은 빈칸처리
                        ArrayList<Node> tmpList = tableList.get(j);
                        for (int k = 0; k < tmpList.size(); k++) {
                            Node tmpNode = tmpList.get(k);
                            curTable[tmpNode.getX()][tmpNode.getY()] = 0;
                        }
                        tableList.remove(j);
                        break;
                    }
                }
            }
        }
    }
}

class Node {
    private int x;
    private int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
