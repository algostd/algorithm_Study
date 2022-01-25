package Topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1516 (골드3) (위상정렬)
public class 게임개발 {

    static class Node{
        int num;
        int price;

        public Node(int num, int price) {
            this.num = num;
            this.price = price;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int[] answerArr;
    static int N;

    static ArrayList<Integer>[] childAdjList;
    static ArrayList<Integer>[] parentAdjList;
    static boolean[] visited;
    static Queue<Node> queue;
    static int[] priceArr;

    public static void main(String[] args) throws IOException {
        getInfo();
        topologicalSort();
        printAnswer();
    }

    private static void topologicalSort() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.num] = true;
            for (Integer next : childAdjList[node.num]) {
                if (visited[next]) {
                    continue;
                }
                // 걸리는 시간이 더 긴것으로 초기화
                answerArr[next] = Math.max(answerArr[next], node.price + priceArr[next]);
                if (!checkVisitParent(next)) {
                    continue;
                }
                queue.add(new Node(next, answerArr[next]));
            }
        }
    }

    private static void printAnswer() {
        for (int i = 1; i <= N; i++) {
            sb.append(answerArr[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean checkVisitParent(Integer next) {
        for (Integer parent : parentAdjList[next]) {
            if (!visited[parent]) {
                return false;
            }
        }
        return true;
    }

    private static void getInfo() throws IOException {
        N = Integer.parseInt(br.readLine());

        // 입접리스트 초기화
        queue = new LinkedList<>();
        answerArr = new int[N + 1];
        priceArr = new int[N + 1];
        visited = new boolean[N + 1];
        childAdjList = new ArrayList[N + 1];
        parentAdjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            childAdjList[i] = new ArrayList<>();
            parentAdjList[i] = new ArrayList<>();
        }

        // 인접리스트 값 담기, Root노드 찾기
        int parent = 0;
        int price = 0;
        for (int i = 1; i <= N; i++) {
            String[] strArr = br.readLine().split(" ");
            price = Integer.parseInt(strArr[0]);
            for (int j = 1; j < strArr.length - 1; j++) {
                parent = Integer.parseInt(strArr[j]);
                childAdjList[parent].add(i);
                parentAdjList[i].add(parent);
            }
            priceArr[i] = price;

            // Root Node
            if (strArr.length == 2) {
                queue.add(new Node(i, price));
                answerArr[i] = price;
            }
        }
    }
}
