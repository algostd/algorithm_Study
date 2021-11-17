import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr, memo;
    private static int[] l = {-1, 1, 0, 0};
    private static int[] r = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = 0;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            test_case++;
            if (N == 0) {
                break;
            }

            //5 5 4
            //3 9 1
            //3 2 7

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(input[j]);
                    arr[i][j] = num;
                }
            }

            memo = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    memo[i][j] = 100000; //INF
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0, arr[0][0]));
            memo[0][0] = arr[0][0];

            while (!pq.isEmpty()) {
                Node curNode = pq.poll();
                int cx = curNode.getX();
                int cy = curNode.getY();
                int cval = curNode.getVal();

                //인접한 노드들 다익스트라 실행
                for (int k = 0; k < 4; k++) {
                    int nx = cx + l[k];
                    int ny = cy + r[k];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (memo[cx][cy] + arr[nx][ny] < memo[nx][ny]) {
                            memo[nx][ny] = memo[cx][cy] + arr[nx][ny];
                            pq.add(new Node(nx, ny, memo[nx][ny]));
                        }
                    }
                }
            }

            //test
            /*
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(memo[i][j] + " ");
                }
                System.out.println();
            }
            */

            bw.write("Problem ");
            bw.write(String.valueOf(test_case));
            bw.write(": ");
            bw.write(String.valueOf(memo[N-1][N-1]));
            bw.write("\n");
        }
        bw.flush();
    }
}


class Node implements Comparable<Node> {
    private int x;
    private int y;
    private int val;

    Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }

    public int getX () {
        return this.x;
    }

    public int getY () {
        return this.y;
    }

    public int getVal () {
        return this.val;
    }
}