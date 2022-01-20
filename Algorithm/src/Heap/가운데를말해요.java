package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1655 (골드2) (Heap)
public class 가운데를말해요 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    private static PriorityQueue<Integer> leftQueue;
    private static PriorityQueue<Integer> rightQueue;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        leftQueue = new PriorityQueue<>(Collections.reverseOrder()); // 큰 수가 위에
        rightQueue = new PriorityQueue<>(); // 작은 수가 위에


        for (int i = 1; i <= N; i++) {
            // 입력
            int num = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                rightQueue.add(num);
            } else {
                leftQueue.add(num);
            }

            // Swap
            if (!rightQueue.isEmpty() && leftQueue.peek() > rightQueue.peek()) {
                swap();
            }
            sb.append(leftQueue.peek()).append("\n");
        }
        System.out.println(sb);
    }

    private static void swap() {
        int right = rightQueue.poll();
        int left = leftQueue.poll();
        rightQueue.add(left);
        leftQueue.add(right);
    }
}
