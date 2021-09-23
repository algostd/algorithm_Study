import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class 프로그래머스_위클리챌린지3주차_퍼즐조각채우기 {
    private ArrayList<ArrayList<Pair>> tableArr;
    private boolean[][] visited;
    private int[][] static_table;
    private ArrayList<Pair> tableSync;
    private int N;
    private int minRow;
    private int minCol;

    static class Pair {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return row == pair.row && col == pair.col;
        }

        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        N = game_board.length;
        static_table = table;

        // game_board에서 빈공간 가져오기
        ArrayList<ArrayList<Pair>> gameBoardArr = new ArrayList<>();
        Stack<Pair> stack = new Stack<>();
        visited = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (game_board[row][col] == 0 && !visited[row][col]) {
                    minCol = 50;
                    minRow = 50;
                    visited[row][col] = true;
                    ArrayList<Pair> onePiece = new ArrayList<>();
                    onePiece.add(new Pair(row, col));
                    stack.add(new Pair(row, col));
                    while (!stack.isEmpty()) {
                        Pair pair = stack.pop();
                        minRow = Math.min(minRow, pair.row);
                        minCol = Math.min(minCol, pair.col);
                        for (int i = 0; i < 4; i++) {
                            int tr = pair.row + dr[i];
                            int tc = pair.col + dc[i];
                            if (tr >= 0 && tc >= 0 && tr < N && tc < N
                                    && game_board[tr][tc] == 0
                                    && !visited[tr][tc]) {
                                visited[tr][tc] = true;
                                stack.add(new Pair(tr, tc));
                                onePiece.add(new Pair(tr, tc));
                            }
                        }
                    }
                    // block 싱크 맞추기
                    for (Pair pair : onePiece) {
                        pair.row -= minRow;
                        pair.col -= minCol;
                    }
                    // block 추가
                    gameBoardArr.add(onePiece);
                }
            }
        }

        // 테이블에서 조각 가져오기 (회전까지)
        tableArr = new ArrayList<>();
        tableSync = new ArrayList<>();
        for (int i = 0; i < 4; i++) { // 회전화면
            tableArr.clear();
            tableSync.clear();
            visited = new boolean[N][N];
            getTablePieces(); // 테이블 조각 가져옴

            // 퍼즐끼우기
            outer: for (int j = 0; j < gameBoardArr.size(); j++) {
                for (int k = 0; k < tableArr.size(); k++) {
                    ArrayList<Pair> boardPiece = gameBoardArr.get(j);
                    ArrayList<Pair> tablePiece = tableArr.get(k);
                    // 퍼즐 끼울 수 있음
                    if (boardPiece.equals(tablePiece)) {
                        answer += boardPiece.size();
                        // board에서 제거
                        gameBoardArr.remove(boardPiece);

                        // table에서 제거
                        Pair sync = tableSync.get(k);
                        for (Pair pair : tablePiece) {
                            static_table[pair.row + sync.row][pair.col + sync.col] = 0;
                        }

                        // 다음으로
                        tableSync.remove(sync);
                        tableArr.remove(tablePiece);
                        j--;
                        continue outer;
                    }
                }
            }

            // 회전
            for (int j = 0; j < N / 2; j++) { // 위아래 뒤집음
                for (int k = 0; k < N; k++) {
                    int temp = table[j][k];
                    table[j][k] = table[N - 1 - j][k];
                    table[N - 1 - j][k] = temp;
                }
            }
            for (int j = 0; j < N; j++) { // 대각선으로 뒤집음
                for (int k = j; k < N; k++) {
                    int temp = table[j][k];
                    table[j][k] = table[k][j];
                    table[k][j] = temp;
                }
            }
        }

        return answer;
    }

    private void getTablePieces() {
        Stack<Pair> stack = new Stack<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (static_table[row][col] == 1 && !visited[row][col]) {
                    minCol = 50;
                    minRow = 50;
                    visited[row][col] = true;
                    ArrayList<Pair> onePiece = new ArrayList<>();
                    onePiece.add(new Pair(row, col));
                    stack.add(new Pair(row, col));
                    while (!stack.isEmpty()) {
                        Pair pair = stack.pop();
                        minRow = Math.min(minRow, pair.row);
                        minCol = Math.min(minCol, pair.col);
                        for (int i = 0; i < 4; i++) {
                            int tr = pair.row + dr[i];
                            int tc = pair.col + dc[i];
                            if (tr >= 0 && tc >= 0 && tr < N && tc < N
                                    && static_table[tr][tc] == 1
                                    && !visited[tr][tc]) {
                                visited[tr][tc] = true;
                                stack.add(new Pair(tr, tc));
                                onePiece.add(new Pair(tr, tc));
                            }
                        }
                    }
                    // block 싱크 맞추기
                    for (Pair pair : onePiece) {
                        pair.row -= minRow;
                        pair.col -= minCol;
                    }
                    tableSync.add(new Pair(minRow, minCol));
                    // block 추가
                    tableArr.add(onePiece);
                }
            }
        }
    }
}
