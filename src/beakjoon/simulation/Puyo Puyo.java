import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int[][] arr, visited;
    private static int[] l = {-1, 1, 0, 0};
    private static int[] r = {0, 0, -1, 1};
    private static int max;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[12][6];
        for (int i = 0; i < 12; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 6; j++) {
                String str = input[j];
                Main m = new Main();
                arr[i][j] = m.translate(str);
            }
        }

        int answer = 0;
        while(true) {
            visited = new int[12][6];
            boolean check = false;

            //bfs를 돌며 arr[i][j] != 0 && visited[i][j] == 0이라면 방문한다.
            Main m = new Main();
            for (int cx = 0; cx < 12; cx++) {
                for (int cy = 0; cy < 6; cy++) {
                    if (arr[cx][cy] == 0) {
                        continue;
                    }
                    max = 0;

                    int[][] tmp = new int[12][6];
                    for (int a = 0; a < 12; a++) {
                        for (int b = 0; b < 6; b++) {
                            tmp[a][b] = visited[a][b];
                        }
                    }

                    m.bfs(cx, cy, arr[cx][cy], 1);
                    if (max < 4) {
                        //dfs전으로 원복
                        for (int a = 0; a < 12; a++) {
                            for (int b = 0; b < 6; b++) {
                                visited[a][b] = tmp[a][b];
                            }
                        }
                    }
                }
            }

            //visited 처리된 부분을 모두 0으로 바꾼다.
            for (int cx = 0; cx < 12; cx++) {
                for (int cy = 0; cy < 6; cy++) {
                    if (visited[cx][cy] == 1) {
                        check = true;
                        arr[cx][cy] = 0;
                    }
                }
            }

            if (check == false) {
                break;
            }

            //아래쪽으로 모두 붙힌다.
            for (int cy = 0; cy < 6; cy++) {
                List<Integer> list = new ArrayList<Integer>();
                for (int cx = 11; cx >= 0; cx--) {
                    if (arr[cx][cy] != 0) {
                        list.add(arr[cx][cy]);
                        arr[cx][cy] = 0;
                    }
                }

                int cx = 11;
                for (int num : list) {
                    arr[cx][cy] = num;
                    cx--;
                }
            }

            answer++;
        }

        System.out.println(answer);
    }

    private int translate(String str) {
        int result = 0;
        if (str.equals(".")) {
            result = 0;
        }
        if (str.equals("R")) {
            result = 1;
        }
        if (str.equals("G")) {
            result = 2;
        }
        if (str.equals("B")) {
            result = 3;
        }
        if (str.equals("P")) {
            result = 4;
        }
        if (str.equals("Y")) {
            result = 5;
        }
        return result;
    }

    private void bfs(int cx, int cy, int num, int cnt) {
        if (visited[cx][cy] == 1) {
            return;
        }

        Queue<Node> qu = new LinkedList<Node>();
        qu.add(new Node(cx, cy));
        while (!qu.isEmpty()) {
            Node node = qu.poll();
            cx = node.x;
            cy = node.y;

            visited[cx][cy] = 1;
            for (int i = 0; i < 4; i++) {
                int nx = cx + l[i];
                int ny = cy + r[i];
                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && visited[nx][ny] == 0 && arr[nx][ny] == num) {
                    cnt++;
                    max = Math.max(cnt, max);
                    qu.add(new Node(nx, ny));
                }
            }
        }
    }
}

class Node {
    int x;
    int y;

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
