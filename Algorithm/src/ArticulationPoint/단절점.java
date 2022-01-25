package ArticulationPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/11266 (플레5) (단절점)
public class 단절점 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static boolean[] answerCheck;
    static int answerSize = 0;
    static int V, E;
    private static ArrayList<Integer>[] adjList;
    private static int[] visitOrder;
    private static int order = 0;

    public static void main(String[] args) throws IOException {
        getInfo();

        /*
        단절점 구하기
        준비물
        순서를 나타낼 배열, 방문순서, 정답 리스트
        === 노드를 하나씩 순서를 매기면서 탐색 (DFS) ===
        1. RootNode 가 아닌데 단절점인 경우
          - 해당 노드의 순서가
               vs
          - 주변 노드의 방문가능한 노드 중
            이 노드들이 끝까지 탐색했을 때 가져올 수 있는 최소 순서

          2개를 비교해서 2번째가 값이 크거나 같으면 단절점
        2. RootNode 인데 단절점인 경우
          - 루트노드와 연결된 자식 노드가 2개 이상이면 단절된 노드이다.
         */

        // 문제 조건 중 끊어진 곳이 있을 수 있다 했으니 전체 탐색
        for (int i = 1; i <= V; i++) {
            if (visitOrder[i] == 0) {
                searchGraph(i, true);
            }
        }
        printAnswer();
    }

    private static void printAnswer() {
        sb.append(answerSize).append("\n");
        int i = 0;
        for (boolean check : answerCheck) {
            if (check) {
                sb.append(i).append(" ");
            }
            i++;
        }
        System.out.println(sb);
    }

    private static int searchGraph(int now, boolean isRoot) {
        // 순서 설정
        order++;
        visitOrder[now] = order;
        int minVisitOrder = order;
        int childCnt = 0;

        // 자식 노드 탐색
        for (Integer next : adjList[now]) {
            // 방문 안했으면
            if (visitOrder[next] == 0) {
                childCnt++;
                int childLow = searchGraph(next, false);
                if (!isRoot && childLow >= visitOrder[now] && !answerCheck[now]) {
                    answerSize++;
                    answerCheck[now] = true;
                }
                minVisitOrder = Math.min(minVisitOrder, childLow);
            }
            // 방문 했으면
            else {
                minVisitOrder = Math.min(minVisitOrder, visitOrder[next]);
            }
        }

        // 부모노드인 경우 예외 케이스
        if (isRoot && childCnt >= 2) {
            answerSize++;
            answerCheck[now] = true;
        }
        return minVisitOrder;
    }

    public static void getInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visitOrder = new int[V + 1];
        answerCheck = new boolean[V + 1];

        // 인접리스트 만들기
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) { // 초기화
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) { // 값 삽입
            st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adjList[num1].add(num2);
            adjList[num2].add(num1);
        }
    }
}
