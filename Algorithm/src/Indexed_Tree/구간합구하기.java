package Indexed_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/2042 (골드1)
// Indexed Tree
public class 구간합구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N, M, K; // 수의개수, 수의 변경이 일어나는 횟수, 구간의 합을 구하는 횟수
    private static long[] tree;
    private static int size;


    public static void main(String[] args) throws IOException {
        getInfo();

        // 명령어 수행
        String[] strArr;
        for (int i = 0; i < M + K; i++) {
            // a가 1인경우 b번째 수를 c로 바꾸고, 2인경우 b 번째수부터 c 까지의 합
            strArr = br.readLine().split(" ");
            int a = Integer.parseInt(strArr[0]);
            long b = Long.parseLong(strArr[1]);
            long c = Long.parseLong(strArr[2]);

            if (a == 1) {
                update(b, c);
            } else {
                long sum = getSum(b, c);
                sb.append(sum).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long getSum(long leftQuery, long rightQuery) {
        long sum = 0;

        int left = (int) (leftQuery + size - 1);
        int right = (int) (rightQuery + size - 1);

        while (left <= right) {
            if (left == right) {
                sum += tree[left];
                break;
            }
            if (left % 2 == 1) { // left 가 홀수라면
                sum += tree[left++];
            }
            if (right % 2 == 0) { // right 가 짝수라면
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    private static void update(long index, long value) {
        int now = (int) (index + size - 1);
        long diff = value - tree[now];
        tree[now] = value;

        int parent = now / 2;
        while (parent >= 1) {
            tree[parent] += diff;
            parent /= 2;
        }
    }

    private static void getInfo() throws IOException {
        String[] strArr;
        strArr = br.readLine().split(" ");
        N = Integer.parseInt(strArr[0]);
        M = Integer.parseInt(strArr[1]);
        K = Integer.parseInt(strArr[2]);

        // Indexed Tree 를 위한 배열 만들기
        size = 1;
        while (N > size) {
            size *= 2;
        }
        tree = new long[size * 2]; // 1 ~ (size * 2 -1)
        initTree();
    }

    private static void initTree() throws IOException {
        // leaf 노드 초기화
        for (int i = 0, j = size; i < N; i++, j++) {
            long num = Long.parseLong(br.readLine());
            tree[j] = num;

            // inner 노드 초기화
            int parent = j / 2;
            while (parent >= 1) {
                tree[parent] += num;
                parent /= 2;
            }
        }
    }
}
