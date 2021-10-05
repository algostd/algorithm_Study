import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        //전선을 연결한 뒤 하나하나 끊어본다.
        //이때 2가지 조건을 확인해야 한다.
        //1. 트리가 2개로 나뉘었는가. (단지번호붙이기)
        //2. 1번 조건을 만족할 경우 2개의 트리의 절대값의 차이

        List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int[] Node : wires) {
            int num1 = Node[0]-1;
            int num2 = Node[1]-1;
            list.get(num1).add(num2);
            list.get(num2).add(num1);
        }

        //알고리즘
        //전선을 하나씩 끊어본다.
        int min = Integer.MAX_VALUE;
        for (int[] Node : wires) {
            int num1 = Node[0]-1;
            int num2 = Node[1]-1;

            list.get(num1).remove((Integer) num2);
            list.get(num2).remove((Integer) num1);

            int[] visited = new int[n];
            List<Integer> nodeCount = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                //아직 방문하지 않았다면
                if (visited[i] == 0) {
                    int tmp = 0;

                    //단지번호 붙이기 실행
                    Queue<Integer> qu = new LinkedList<Integer>();
                    qu.add(i);

                    while (!qu.isEmpty()) {
                        int num = qu.poll();
                        visited[num] = 1;
                        tmp++;

                        ArrayList<Integer> tmpList = list.get(num);
                        for (int node :tmpList) {
                            if (visited[node] == 0) {
                                qu.add(node);
                            }
                        }
                    }

                    //단지번호붙이기가 끝났다면 연결된 송전탑의 개수를 리스트에 담는다.
                    nodeCount.add(tmp);
                }
            }

            if (nodeCount.size() == 2) {
                int nodeCnt1 = nodeCount.get(0);
                int nodeCnt2 = nodeCount.get(1);

                if (nodeCnt1 >= nodeCnt2) {
                    min = Math.min(nodeCnt1 - nodeCnt2, min);
                } else {
                    min = Math.min(nodeCnt2 - nodeCnt1, min);
                }
            }

            //끊어진 전선 원복
            list.get(num1).add(num2);
            list.get(num2).add(num1);
        }

        return min;
    }
}