import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/15686
public class 치킨배달 {

    /*
        BruteForce
        전체 치킨집 N개 중 M개 선택 조합 (N C M)
        완성된 조합으로 모든 집에서 BFS해서 총비용을 구함
        answer = min(모든 조합 경우의 수의 총비용)
     */

    static class Node {
        int row;
        int col;
        int count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            this.count = 0;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer = Integer.MAX_VALUE;
    private static int N;
    private static int M;
    private static int[][] map;
    private static ArrayList<Node> chickens;
    private static ArrayList<Node> homes;

    public static void main(String[] args) throws IOException {
        getInfo();
        getCombinationChickens(0, 0, new int[M]);
        System.out.println(answer);
    }

    private static void getCombinationChickens(int depth, int start, int[] comb) {
        if (depth == M || depth == N) {
            // 조합만큼의 치킨만 남김
            ArrayList<Node> MChickens = new ArrayList<>();
            for (int i : comb) {
                MChickens.add(chickens.get(i));
            }

            // 남긴 치킨집 기준으로 모든 집에서의
            answer = Math.min(answer, getChickenDistance(MChickens));
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            comb[depth] = i;
            getCombinationChickens(depth + 1, i + 1, comb);
        }
    }

    private static int getChickenDistance(ArrayList<Node> mChickens) {
        int chickenDistance = 0;
        for (Node home : homes) {
            int distance = Integer.MAX_VALUE;
            for (Node chicken : mChickens) {
                distance = Math.min(distance, getDistance(home, chicken));
            }
            chickenDistance += distance;
        }
        return chickenDistance;
    }

    private static int getDistance(Node home, Node chicken) {
        return Math.abs(home.row - chicken.row) + Math.abs(home.col - chicken.col);
    }


    private static void getInfo() throws IOException {
        String[] strArr;
        strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        M = Integer.parseInt(strArr[1]);

        map = new int[N][N];
        chickens = new ArrayList<Node>();
        homes = new ArrayList<Node>();

        for (int row = 0; row < N; row++) {
            strArr = br.readLine().split(" ");
            for (int col = 0; col < N; col++) {
                int num = Integer.parseInt(strArr[col]);
                map[row][col] = num;
                if (num == 1) {
                    homes.add(new Node(row, col));
                } else if (num == 2) {
                    chickens.add(new Node(row, col));
                }
            }
        }
    }
}