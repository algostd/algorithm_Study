import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/2696
// 힙 2개를 가지고 최대한 활용할 줄 알아야 하는 문제
public class 중앙값구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answerBuilder = new StringBuilder();

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            // 정답 개수 출력
            answerBuilder.append(N / 2 + 1).append("\n");

            // 알고리즘 시작
            PriorityQueue<Integer> leftQueue = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
            int count = 1;
            String[] strArr;

            while (N > 0) {
                strArr = br.readLine().split(" ");
                for (int i = 0; i < strArr.length; i++) {
                    int next = Integer.parseInt(strArr[i]);
                    if (count % 2 == 1) { // 홀수 일때
                        leftQueue.add(next);
                        if (!rightQueue.isEmpty()) {
                            if (leftQueue.peek() > rightQueue.peek()) {
                                int leftOne = leftQueue.poll();
                                int rightOne = rightQueue.poll();
                                rightQueue.add(leftOne);
                                leftQueue.add(rightOne);
                            }
                        }
                        answerBuilder.append(leftQueue.peek()).append(" ");
                    } else { // 짝 수 일 때
                        rightQueue.add(next);
                    }
                    count++;
                }
                if (count > 20) {
                    answerBuilder.append("\n");
                    count -= 20;
                }
                N -= 10;
            }
            answerBuilder.append("\n");
        }
        System.out.println(answerBuilder);
    }
}
