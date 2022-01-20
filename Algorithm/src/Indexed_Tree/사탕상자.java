package Indexed_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/2243 (플레5) (Indexed Tree)
public class 사탕상자 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int SIZE;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        getInfo();

        // 명령어 실행
        String[] strArr;
        for (int i = 0; i < N; i++) {
            strArr = br.readLine().split(" ");
            int a = Integer.parseInt(strArr[0]);
            int b = Integer.parseInt(strArr[1]);
            //A: 1 순위가 B인 사탕꺼내기
            if (a == 1) {
                int candy = popBCandy(1, b);
                update(candy, -1);
                sb.append(candy).append("\n");
            }
            //A: 2 B넣을 사탕의 맛, C 넣는 사탕의 수
            else {
                int c = Integer.parseInt(strArr[2]);
                update(b, c);
            }
        }
        System.out.println(sb);
    }

    // Bottom Up
    private static void update(int index, int diff) {
        int now = SIZE + index - 1;
        tree[now] += diff;
        int parent = now / 2;

        while (parent >= 1) {
            tree[parent] += diff;
            parent /= 2;
        }
    }

    // Top Down
    private static int popBCandy(int now, int findIndex) { // now: 현재 도착한 노드번호, index : 몇번째 사탕을 가져올것
        int leftIndex = now * 2;
        int rightIndex = now * 2 + 1;

        if (leftIndex >= tree.length) { // 리프 노드 일때
            return now - SIZE + 1;
        } else { // 이너 노드 일때
            if (tree[leftIndex] >= findIndex) {
                return popBCandy(leftIndex, findIndex);
            } else {
                return popBCandy(rightIndex, findIndex - tree[leftIndex]);
            }
        }
    }

    private static void getInfo() throws IOException {
        SIZE = (int) Math.pow(2, 20);
        N = Integer.parseInt(br.readLine());
        tree = new int[SIZE * 2]; // 1부터 ~ 1,048,576 * 2까지
    }
}
