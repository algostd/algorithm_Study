import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[][] chess;
    private static int[] l = {0,0,-1,1};
    private static int[] r = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        //0은 흰색, 1은 빨간색, 2는 파란색
        chess = new int[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                chess[i][j] = Integer.parseInt(input[j]);
            }
        }

        //말의 위치는 Map으로 관리한다.
        //0번말 : Node(x, y, dir)
        Map<Integer, Node> map = new HashMap<Integer, Node>();
        int[][] chess_stack = new int[N][N];

        //(x,y) => chess_stack내 번호 1대1 대응 => chess_list map 대응
        int tmp_cnt = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp_cnt++;
                chess_stack[i][j] = tmp_cnt;
            }
        }
        Map<Integer, List<Integer>> chess_list = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i <= tmp_cnt; i++) {
            chess_list.put(i, new ArrayList<Integer>());
        }

        //우좌상하
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0])-1;
            int y = Integer.parseInt(input[1])-1;
            int dir = Integer.parseInt(input[2])-1;

            map.put(i, new Node(x, y, dir));

            chess_list.get(chess_stack[x][y]).add(i);
        }

        //0번부터 K-1번까지의 말
        int num = -1; //말의 번호
        int cnt = 1;
        while (cnt <= 1000) {
            num++;
            if (num >= K) {
                num = num % K;
                cnt++;
            }
            //System.out.println("cnt: " + cnt);

            //num번 말에 대응하는 Node
            Node curNode = map.get(num);
            int cx = curNode.getX();
            int cy = curNode.getY();
            int cdir = curNode.getDir();

            //가장 아래에 있는 말인지 확인한다.
            List<Integer> curList = chess_list.get(chess_stack[cx][cy]);
            if (curList.get(0) != num) {
                continue;
            }

            //다음에 가려는 칸
            int nx = cx + l[cdir];
            int ny = cy + r[cdir];

            //다음 이동하려는 칸이 흰색일 경우
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && chess[nx][ny] == 0) {
                //다음에 갈 지점(nx, ny)에 말을 그대로 옮긴다.
                for (int piece : curList) {
                    chess_list.get(chess_stack[nx][ny]).add(piece);
                    int piece_dir = map.get(piece).getDir();
                    map.put(piece, new Node(nx, ny, piece_dir));
                }

                //이전 칸(cx, cy)에 쌓여있던 말 초기화
                chess_list.put(chess_stack[cx][cy], new ArrayList<Integer>());

                //턴종료검사
                if (chess_list.get(chess_stack[nx][ny]).size() >= 4) {
                    break;
                }
            }

            //다음 이동하려는 칸(nx, ny)이 빨간색일 경우
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && chess[nx][ny] == 1) {
                List<Integer> reverseList = new ArrayList<Integer>();
                for (int k = curList.size()-1; k >= 0; k--) {
                    reverseList.add(curList.get(k));
                }

                //말을 그대로 옮긴다.
                for (int piece : reverseList) {
                    chess_list.get(chess_stack[nx][ny]).add(piece);
                    int piece_dir = map.get(piece).getDir();
                    map.put(piece, new Node(nx, ny, piece_dir));
                }

                //이전 칸(cx, cy)에 쌓여있던 말 초기화
                chess_list.put(chess_stack[cx][cy], new ArrayList<Integer>());

                //턴종료검사
                if (chess_list.get(chess_stack[nx][ny]).size() >= 4) {
                    break;
                }
            }

            //다음 이동하려는 칸이 파란색이거나 체스판 밖을 빠져나가는 경우
            if (!(nx >= 0 && nx < N && ny >= 0 && ny < N) || chess[nx][ny] == 2) {
                //해당 번호의 말만 방향을 반대로 한다.
                map.get(num).reverseDir();

                //--------------------------------------------------------------------------
                //코드반복 - 추후수정 필요
                //--------------------------------------------------------------------------

                cdir = curNode.getDir();

                //다음에 가려는 칸
                nx = cx + l[cdir];
                ny = cy + r[cdir];

                //다음 이동하려는 칸이 흰색일 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && chess[nx][ny] == 0) {
                    //말을 그대로 옮긴다.
                    for (int piece : curList) {
                        chess_list.get(chess_stack[nx][ny]).add(piece);
                        int piece_dir = map.get(piece).getDir();
                        map.put(piece, new Node(nx, ny, piece_dir));
                    }

                    //이전 칸에 쌓여있던 말 초기화
                    chess_list.put(chess_stack[cx][cy], new ArrayList<Integer>());

                    //턴종료검사
                    if (chess_list.get(chess_stack[nx][ny]).size() >= 4) {
                        break;
                    }
                }

                //다음 이동하려는 칸이 빨간색일 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && chess[nx][ny] == 1) {
                    List<Integer> reverseList = new ArrayList<Integer>();
                    for (int k = curList.size()-1; k >= 0; k--) {
                        reverseList.add(curList.get(k));
                    }

                    //말을 그대로 옮긴다.
                    for (int piece : reverseList) {
                        chess_list.get(chess_stack[nx][ny]).add(piece);
                        int piece_dir = map.get(piece).getDir();
                        map.put(piece, new Node(nx, ny, piece_dir));
                    }

                    //이전 칸에 쌓여있던 말 초기화
                    chess_list.put(chess_stack[cx][cy], new ArrayList<Integer>());

                    //턴종료검사
                    if (chess_list.get(chess_stack[nx][ny]).size() >= 4) {
                        break;
                    }
                }

                //다음 이동하려는 칸이 파란색이거나 체스판 밖을 빠져나가는 경우
                if (!(nx >= 0 && nx < N && ny >= 0 && ny < N) || chess[nx][ny] == 2) {
                }

            }
        }

        //출력
        if (cnt >= 1001) {
            System.out.println(-1);
        } else {
            System.out.println(cnt);
        }
    }
}

class Node {
    private int x;
    private int y;
    private int dir;

    Node(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDir() {
        return dir;
    }

    public void reverseDir() {
        if (this.dir == 0) {
            this.dir = 1;
        } else if (this.dir == 1) {
            this.dir = 0;
        } else if (this.dir == 2) {
            this.dir = 3;
        } else if (this.dir == 3) {
            this.dir = 2;
        }
    }
}
