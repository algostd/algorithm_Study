import java.util.PriorityQueue;

class Solution {
    public static int solution(int[] scoville, int K) {
        int answer = 0;

        //최소힙 생성
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        //입력
        for (int i : scoville) {
            priorityQueue.add(i);
        }

        int cnt = 0;
        while(true) {
            int len = priorityQueue.size();
            if (len < 2) {
                return -1;
            }

            cnt++;
            int num1 = priorityQueue.poll();
            int num2 = priorityQueue.poll();

            int sum = num1 + (num2 * 2);
            priorityQueue.add(sum);
            if (priorityQueue.peek() >= K) {
                answer = cnt;
                break;
            }
        }

        return answer;
    }
}