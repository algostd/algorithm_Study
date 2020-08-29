import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Cook {
    public static int N;
    public static boolean[] visited;
    public static int[][] food;
    public static List<Integer> combiList;
    public static int[] perArr;
    public static int foodSum;
    public static List<Integer> resultList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            //1. nCn/2 조합의 가능한 경우의 수를 모두 찾는다.
            //2. n/2P2 가능한 2개의 경우의 수를 모두 더한다.
            //3. 나머지 n/2 중 가능한 경우의 수를 모두 찾는다.
            //4. n/2P2 가능한 2개의 경우의 수를 모두 더한다.
            //5. 2와 4의 값을 모두 리스트에 저장한다.

            //입력
            N = Integer.parseInt(br.readLine());
            food = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    food[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //알고리즘
            visited = new boolean[N + 1];
            combiList = new ArrayList<Integer>();
            resultList = new ArrayList<Integer>();
            combination(1, 0);

            Collections.sort(resultList);
            System.out.println("#" + test_case + " " + resultList.get(0));
        }

        br.close();
    }

    private static void combination(int idx, int cnt) {
        if (cnt == N / 2) {
            foodSum = 0;
            perArr = new int[2];
            permutation(0, 0, combiList);
            int foodSum1 = foodSum;

            List<Integer> reverseCombiList = new ArrayList<Integer>();
            for (int i = 1; i <= N; i++) {
                if (!combiList.contains(i)) {
                    reverseCombiList.add(i);
                }
            }

            foodSum = 0;
            perArr = new int[2];
            permutation(0, 0, reverseCombiList);
            int foodSum2 = foodSum;

            resultList.add(Math.abs(foodSum1 - foodSum2));
            return;
        }
        for (int i = idx; i <= N; i++) { //i는 1부터 N까지
            visited[i] = true;
            combiList.add(i);
            combination(i + 1, cnt + 1);
            combiList.remove((Integer) i);
            visited[i] = false;
        }
    }

    private static void permutation(int start, int depth, List<Integer> list) {
        if (depth == 2) {
            int num1 = perArr[0];
            int num2 = perArr[1];

            foodSum += food[num1][num2];
            foodSum += food[num2][num1];

            return;
        }

        for (int i = start; i < list.size(); i++) { //123일때 12 13 23
            int num = list.get(i);
            perArr[depth] = num;
            permutation(i + 1, depth + 1, list);
        }
    }
}