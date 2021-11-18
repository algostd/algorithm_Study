package Algorithm.src.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// bfs ���� ��忡�� ���� �ֺ����� �Ѵܰ辿 Ž���ؿ� �ߺ��� Ž���� �پ�� �� ���Ƽ� bfs ����
// https://programmers.co.kr/learn/courses/30/lessons/49189

public class ����ճ�� {

	public int solution(int n, int[][] edge) {
		int answer = 0;
		HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer, ArrayList<Integer>>();

		// ��������Ʈ �����
		for (int i = 1; i <= n; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			hashmap.put(i, arr);
		}
		for (int i = 0; i < edge.length; i++) {
			hashmap.get(edge[i][0]).add(edge[i][1]);
			hashmap.get(edge[i][1]).add(edge[i][0]);
		}

		// �ٽ� �˰���
		int[] count = new int[n + 1]; // visited �������� �ּҰŸ��� ����ϱ� ���� �迭
		Arrays.fill(count, n);
		count[0] = 0;
		count[1] = 0;
		
		Queue<int[]> queue = new LinkedList<int[]>(); // [0] => ��� [1] => stack
		
		queue.add(new int[] {1,0});
		while(!queue.isEmpty()) {
			int[] pair = queue.poll();
			for(Integer node : hashmap.get(pair[0])) { // ����� ����Ʈ
				if(count[node] > pair[1] + 1 ) { // count[node]�ش� ����� ������ �Ÿ�, pair[1]����
					count[node] = pair[1] + 1; // ����
					queue.add(new int[] {node, count[node]}); // ��� �־���
				} 
			}
		}
		int max = 0;
		
		for(int c : count) {
			if(max < c) {
				max = c;
				answer = 1;
			} else if(max == c) {
				answer++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		 ����ճ�� s = new ����ճ��();
		 int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		 int answer = s.solution(6, edge);
		 System.out.println(answer);
		 
	}
}
