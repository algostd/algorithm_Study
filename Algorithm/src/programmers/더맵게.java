package programmers;

import java.util.PriorityQueue;

public class ´õ¸Ê°Ô {
	public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        for (int i = 0; i < scoville.length; i++) {
			pq.add((long) scoville[i]);
		}
        while(pq.peek() < K) {
        	long first = pq.poll();
        	long second = pq.poll();
        	if(second == 0) {
        		return -1;
        	}
        	long newValue = first + 2 * second;
        	pq.add(newValue);
        	answer++;
        	if(pq.size() == 1 && pq.peek() < K) {
        		return -1;
        	}
        }
        return answer;
    }
	
	public static void main(String[] args) {
		´õ¸Ê°Ô s = new ´õ¸Ê°Ô();
		int[] computers = {  1 , 0, 2  };
		int answer = s.solution(computers, 7);
		System.out.println(answer);
	}
	
}
