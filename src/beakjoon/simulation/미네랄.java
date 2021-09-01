import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int R, C, cnt;
    private static int[][] arr, visited;
    private static int[] attack;
    private static int[] l = {-1,1,0,0};
    private static int[] r = {0,0,-1,1};
    private static boolean check;

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                if (input[j].equals("x")) {
                    arr[i][j] = 1;
                }
            }
        }

        //1은 미네랄, 0은 빈칸
        String str = br.readLine();
        int n = Integer.parseInt(str);
        attack = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            attack[i] = Integer.parseInt(input[i]);
        }

        //algorithm
        Main m = new Main();
        int c = -1;
        for (int i = 0; i < n; i++) {
            int attack_num = attack[i];
            attack_num = R - attack_num;

            //짝수일때 왼쪽 > 오른쪽
            if (i % 2 == 0) {
                for (int column = 0; column < C; column++) {
                    if (arr[attack_num][column] == 1) {
                        c = column;
                        break;
                    }
                }
            } else {
                for (int column = C-1; column >= 0; column--) {
                    if (arr[attack_num][column] == 1) {
                        c = column;
                        break;
                    }
                }
            }

            if (c != -1) {
                arr[attack_num][c] = 0;
                //미네랄을 파괴했으면 후처리를 한다.
                m.after();
            }
        }

        //출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("x");
                }
            }
            System.out.println();
        }
    }

    private void dfs(int cx, int cy) {
        visited[cx][cy] = cnt;
        for (int i = 0; i < 4; i++) {
            int nx = cx + l[i];
            int ny = cy + r[i];

            //공중에 떠있는지 검사
            if (nx == R) {
                check = true;
            }

            if (nx >= 0 && nx < R && ny >= 0 && ny < C && arr[nx][ny] == 1 && visited[nx][ny] == 0) {
                dfs(nx, ny);
            }
        }
    }

    private void after() {
        //공중에 떠있는 클러스터 찾기
        int cx = -1;
        cnt = 0;
        visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 1 && visited[i][j] == 0) {
                    check = false;
                    cnt++;
                    dfs(i, j);
                    if (check == false) {
                        cx = i;
                        break;
                    }
                }
            }
            if (cx != -1) {
                break;
            }
        }
        if (check == true) {
            return;
        }

        //공중에 떠있는 컬럼들을 조사한다.
        Set<Integer> column_set = new HashSet<Integer>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] == cnt) {
                    column_set.add(j);
                }
            }
        }

        //컬럼을 대상으로 공중에 얼만큼 떠있는지 조사한다.
        int min = 101;
        for (int column : column_set) {
            //해당되는 컬럼을 대상으로
            //맨 밑은 확인할 필요가 없으므로 R-2부터 시작
            for (int row = R-2; row >= 0; row--) {
                if (visited[row][column] == cnt && visited[row+1][column] != cnt) {
                    int cur = 0;
                    boolean check = false;

                    for (int k = row+1; k < R; k++) {
                        if (visited[k][column] == cnt) {
                            check = true;
                            break;
                        }

                        if (arr[k][column] == 1) {
                            break;
                        } else {
                            cur++;
                        }
                    }

                    if (check == true) {
                        continue;
                    }

                    if (cur != 0 && cur < min) {
                        min = cur;
                    }
                }
            }
        }

        //min만큼 해당 컬럼을 땡겨줘야한다.
        int[][] tmp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] != cnt) {
                    tmp[i][j] = arr[i][j];
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] == cnt) {
                    tmp[i+min][j] = arr[i][j];
                }
            }
        }

        //복사
        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] = tmp[i][j];
            }
        }
    }
}
