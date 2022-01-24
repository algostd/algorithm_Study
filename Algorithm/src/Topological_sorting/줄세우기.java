package Topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/2252 (골드3) (위상정렬)
public class 줄세우기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int M;
    static ArrayList<Integer>[] adjList;
    static ArrayList<Integer> answerList;
    static boolean[] visited;
    private static boolean[] isNotRoot;

    public static void main(String[] args) throws IOException {
        getInfo();

        // Root 노드들로 DFS 위상정렬
        for (int i = 1; i <= N; i++) {
            if (isNotRoot[i]) {
                continue;
            }
            visited[i] = true;
            topologicalSort(i);
        }

        // 출력할 때 뒤지기 (부모부터 출력)
        Collections.reverse(answerList);
        for (Integer node : answerList) {
            sb.append(node).append(" ");
        }
        System.out.println(sb);
    }

    private static void getInfo() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        answerList = new ArrayList<>();
        adjList = new ArrayList[N + 1];

        // 인접리스트 만들기
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        isNotRoot = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            adjList[parent].add(child);
            isNotRoot[child] = true;
        }
    }

    private static void topologicalSort(int start) {
        for (Integer child : adjList[start]) {
            if(!visited[child]){
                visited[child] = true;
                topologicalSort(child);
            }
        }
        answerList.add(start);
    }
}
