import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int M, P;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            String[] input = br.readLine().split(" ");
            int K = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]); //정점개수
            P = Integer.parseInt(input[2]); //간선개수

            int[] indegree1 = new int[M+1]; //받은 간선의 개수 저장
            int[] indegree2 = new int[M+1]; //받은 간선의 개수 저장
            List<List<Integer>> array = new ArrayList<List<Integer>>();
            List<List<Integer>> take = new ArrayList<List<Integer>>();
            for (int i = 0; i < M+1; i++) {
                array.add(new ArrayList<Integer>());
                take.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < P; i++) {
                input = br.readLine().split(" ");
                int num1 = Integer.parseInt(input[0]);
                int num2 = Integer.parseInt(input[1]);

                array.get(num1).add(num2);
                take.get(num2).add(num1);

                indegree1[num2]++;
                indegree2[num2]++;
            }

            //위상정렬 실행
            Main m = new Main();
            Queue<Integer> qu = m.topologicalSort(indegree1, array);

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int last = -1;
            for (int num : qu) {
                last = num;
                List<Integer> list = take.get(num);
                //1 2 6
                //시작점일 경우
                if (list.size() == 0) {
                    map.put(num, 1);
                    continue;
                }

                //3의 경우
                //1 2를 받았다.
                List<Integer> tmpList = new ArrayList<Integer>();
                for (int tmp : list) {
                    tmpList.add(map.get(tmp));
                }

                Collections.sort(tmpList, Collections.reverseOrder());
                if (tmpList.size() == 1) {
                    map.put(num, tmpList.get(0));
                } else {
                    if (tmpList.get(0) == tmpList.get(1)) {
                        map.put(num, tmpList.get(0)+1);
                    } else {
                        map.put(num, tmpList.get(0));
                    }
                }
            }

            System.out.println(K + " " + map.get(last));
        }

    }

    private Queue<Integer> topologicalSort(int[] indegree, List<List<Integer>> array) {
        Queue<Integer> qu = new LinkedList<Integer>();
        Queue<Integer> result = new LinkedList<Integer>();

        //먼저 큐에 indegree가 0인 노드를 모두 담는다.
        //즉, 받은 간선의 개수가 0인 노드를 시작점으로 한다.
        for (int i = 1; i < M+1; i++) {
            if (indegree[i] == 0) {
                qu.add(i);
            }
        }


        while(!qu.isEmpty()) {
            int num = qu.poll();
            result.add(num);

            for (int i = 0; i < array.get(num).size(); i++) {
                int tmp = array.get(num).get(i);
                indegree[tmp]--; //연결된 간선 모두 제거

                //간선의 개수가 0이 되었다면 큐에 넣는다.
                if (indegree[tmp] == 0) {
                    qu.add(tmp);
                }
            }
        }

        return result;
    }
}