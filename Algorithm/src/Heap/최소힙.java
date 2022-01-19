package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

//https://www.acmicpc.net/problem/1927 (실버1)
public class 최소힙 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int heapSize = 0;
    private static int[] heap;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        heap = new int[N + 1]; // 1~N
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                int poll = poll();
                sb.append(poll).append("\n");
            } else {
                insert(num);
            }
        }
        System.out.println(sb);
    }

    private static int poll() {
        if (heapSize == 0) {
            return 0;
        }

        int returnValue = heap[1];
        int value = heap[heapSize];
        heap[1] = value;
        heap[heapSize--] = 0;

        int now = 1;
        int child = getChild(now);
        while (child != -1 && heap[child] < value) {
            int childValue = heap[child];
            heap[child] = value;
            heap[now] = childValue;

            now = child;
            child = getChild(now);
        }

        return returnValue;
    }

    private static int getChild(int now) {
        int left = now * 2;
        int right = now * 2 + 1;
        if (left > heapSize) {
            return -1;
        }
        if (right > heapSize) {
            return left;
        }
        if (heap[left] < heap[right]) {
            return left;
        } else {
            return right;
        }
    }

    private static void insert(int value) {
        heapSize++;
        int now = heapSize;
        int parent = now / 2;
        heap[now] = value;
        while (heap[parent] > value) {
            int parentValue = heap[parent];
            heap[parent] = value;
            heap[now] = parentValue;

            now = parent;
            parent = now / 2;
        }
    }

}
