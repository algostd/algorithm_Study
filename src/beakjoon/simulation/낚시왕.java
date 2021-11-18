import java.io.*;
import java.util.*;

public class Main {
    private static int R, C, M;
    private static Shark[][] arr, newArr;
    private static int[] l = {0,-1,1,0,0};
    private static int[] r = {0,0,0,1,-1};

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //4 6 8
        //4 1 3 3 8
        //1 3 5 2 9
        //2 4 8 4 1
        //4 5 0 1 4
        //3 3 1 2 7
        //1 5 8 4 3
        //3 6 2 1 2
        //2 2 2 3 5

        //낚시왕이 오른쪽으로 한 칸 이동한다.
        //낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
        //상어가 이동한다.

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);

        //상어의 위치는 배열로 관리한다.
        arr = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] = new Shark(-1,-1,-1,-1,-1); //초기화
            }
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0])-1;
            int c = Integer.parseInt(input[1])-1;
            int s = Integer.parseInt(input[2]);
            int d = Integer.parseInt(input[3]);
            int z = Integer.parseInt(input[4]);
            Shark shark = new Shark(r, c, s, d, z);
            arr[r][c] = shark;
        }

        //move
        Main m = new Main();
        int answer = 0;

        for (int b = 0; b < C; b++) {

            //상어낚시를 시작한다.
            for (int a = 0; a < R; a++) {
                if (arr[a][b].getX() != -1) {
                    answer += arr[a][b].getZ();
                    arr[a][b] = new Shark(-1,-1,-1,-1,-1);

                    //System.out.println("a: " + a + ", b: " + b + ", answer: " + answer);
                    break;
                }
            }

            //새배열 생성
            newArr = new Shark[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    newArr[i][j] = new Shark(-1,-1,-1,-1,-1); //초기화
                }
            }

            //move
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    Shark curShark = arr[i][j];
                    if (curShark.getX() == -1) {
                        continue;
                    }

                    m.move(curShark);
                }
            }

            //이동이 끝났으면 newArr을 arr에 옮긴다.
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    arr[i][j] = newArr[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    private void move(Shark shark) {
        int cx = shark.getX();
        int cy = shark.getY();
        int s = shark.getS();
        int d = shark.getD();
        int z = shark.getZ();

        for (int k = 1; k <= s; k++) {
            if (d == 1 && cx == 0) {
                d = 2;
            } else if (d == 2 && cx == R-1) {
                d = 1;
            } else if (d == 3 && cy == C-1) {
                d = 4;
            } else if (d == 4 && cy == 0) {
                d = 3;
            }

            cx = cx + l[d];
            cy = cy + r[d];
        }

        if (z > newArr[cx][cy].getZ()) {
            newArr[cx][cy] = new Shark(cx,cy,s,d,z);
        }
    }
}

class Shark {
    private int x;
    private int y;
    private int s;
    private int d;
    private int z;

    Shark (int x, int y, int s, int d, int z) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getS() {
        return s;
    }

    public int getD() {
        return d;
    }

    public int getZ() {
        return z;
    }

    public void updateXY(int nx, int ny) {
        this.x = nx;
        this.y = ny;
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