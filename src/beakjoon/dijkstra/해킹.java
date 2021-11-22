import java.io.*;
import java.util.*;

public class Main {
    private static int N, D, C;
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //2
        //3 2 2
        //2 1 5
        //3 2 5

        //3 3 1
        //2 1 2
        //3 1 8
        //3 2 4

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            //input
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            D = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]);

            List<ArrayList<Integer>> list = new ArrayList<>();
            for (int i = 0 ; i <= N; i++) {
                list.add(new ArrayList<>());
            }

            //a b s
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < D; i++) {
                input = br.readLine().split(" ");
                int c1 = Integer.parseInt(input[0]);
                int c2 = Integer.parseInt(input[1]);
                int s = Integer.parseInt(input[2]);
                list.get(c2).add(c1);

                String key = "R" + c2 + "C" + c1;
                map.put(key, s);
            }

            //다익스트라
            //시작점은 C이다.
            int[] dp = new int[N+1];
            for (int i = 0; i <= N; i++) {
                dp[i] = MAX;
            }

            PriorityQueue<Node> qu = new PriorityQueue<>();
            qu.add(new Node(C, 0));
            dp[C] = 0;

            while (!qu.isEmpty()) {
                Node curNode = qu.poll();
                int curNum = curNode.getNum();

                ArrayList<Integer> curList = list.get(curNum);
                for (int num : curList) {
                    String curKey = "R"+curNum+"C"+num;
                    if (dp[num] > dp[curNum] + map.get(curKey)) {
                        dp[num] = dp[curNum] + map.get(curKey);
                        qu.add(new Node(num, dp[num]));
                    }
                }
            }

            //output
            int maxDist = 0;
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (dp[i] != MAX) {
                    cnt++;
                    maxDist = Math.max(dp[i], maxDist);
                }
            }

            System.out.println(cnt + " " + maxDist);
        }
    }
}

class Node implements Comparable<Node> {
    private int num;
    private int dist;

    Node (int num, int dist) {
        this.num = num;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return this.dist - o.dist;
    }

    public int getNum() {
        return this.num;
    }

    public int getDist() {
        return this.dist;
    }
}
