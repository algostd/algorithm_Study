package Algorithm.src.Topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Strahler {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String[] strArr = br.readLine().split(" ");
            int N = Integer.parseInt(strArr[0]); // 테스트 케이스 번호
            int M = Integer.parseInt(strArr[1]); // 노드의 수
            int P = Integer.parseInt(strArr[2]); // 간선의 수

            // 인접행렬 만들기
            HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>(); // 인접행렬
            HashMap<Integer, ArrayList<Integer>> orders = new HashMap<>(); // 인접행렬 들어온 순서를 관리하는
            for (int i = 1; i <= M; i++) {
                adjList.put(i, new ArrayList<Integer>());
                orders.put(i, new ArrayList<Integer>());

            }
            boolean[] checks = new boolean[M + 1];
            for (int i = 0; i < P; i++) {
                strArr = br.readLine().split(" ");
                int A = Integer.parseInt(strArr[0]);
                int B = Integer.parseInt(strArr[1]);
                adjList.get(A).add(B);
                checks[B] = true;
            }

            // 루트노드 담기
            HashMap<Integer, Integer> nodes = new HashMap<>(); // 키: 번호, 값: 순서
            Queue<Integer> queue = new LinkedList<>(); // bfs를 위한 큐
            boolean[] visited = new boolean[M + 1];
            for (int i = 1; i <= M; i++) {
                if (!checks[i]) {
                    nodes.put(i, 1);
                    queue.add(i);
                } else {
                    nodes.put(i, 0);
                }
            }

            // bfs 시작

            while(!queue.isEmpty()){
                int start = queue.poll();
                int startOrder = nodes.get(start);
                for(Integer next :adjList.get(start)){
                    orders.get(next).add(startOrder);
                    if(nodes.get(next) == 0){ // 방문 안햇으면
                        nodes.replace(next, startOrder); // 더 큰걸로 교체
                    } else { // 방문했었으면
                        // 방문한 노드의 순서중 가장 큰 값과 큰값의 개수 세기
                        int max = 0;
                        int maxCount = 0;
                        for(Integer order : orders.get(next)){
                            if(max < order){
                                max = order;
                                maxCount = 1;
                                continue;
                            }
                            if(max == order){
                                maxCount++;
                            }
                        }
                        if(maxCount >= 2){
                            nodes.replace(next, max + 1);
                        } else {
                            nodes.replace(next, max);
                        }
                    }
                    if (!queue.contains(next)){
                        queue.add(next);
                    }
                }
            }

            // 바다로 가는 노드 찾기
            int lastNode = 0;
            for (Map.Entry<Integer, ArrayList<Integer>> entry : adjList.entrySet()) {
                if (entry.getValue().size() == 0) {
                    lastNode = entry.getKey();
                }
            }

            sb.append(N + " " + nodes.get(lastNode) + "\n");
        }
        System.out.println(sb);
    }
}
