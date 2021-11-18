package Algorithm.src.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// bfs 시작 노드에서 부터 주변노드로 한단계씩 탐색해여 중복된 탐색이 줄어들 것 같아서 bfs 선택
// https://programmers.co.kr/learn/courses/30/lessons/49189

public class 가장먼노드 {

	public int solution(int n, int[][] edge) {
		int answer = 0;
		HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer, ArrayList<Integer>>();

		// 인접리스트 만들기
		for (int i = 1; i <= n; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			hashmap.put(i, arr);
		}
		for (int i = 0; i < edge.length; i++) {
			hashmap.get(edge[i][0]).add(edge[i][1]);
			hashmap.get(edge[i][1]).add(edge[i][0]);
		}

		// 핵심 알고리즘
		int[] count = new int[n + 1]; // visited 역할이자 최소거리를 기억하기 위한 배열
		Arrays.fill(count, n);
		count[0] = 0;
		count[1] = 0;
		
		Queue<int[]> queue = new LinkedList<int[]>(); // [0] => 노드 [1] => stack
		
		queue.add(new int[] {1,0});
		while(!queue.isEmpty()) {
			int[] pair = queue.poll();
			for(Integer node : hashmap.get(pair[0])) { // 연결된 리스트
				if(count[node] > pair[1] + 1 ) { // count[node]해당 노드의 측정된 거리, pair[1]스택
					count[node] = pair[1] + 1; // 갱신
					queue.add(new int[] {node, count[node]}); // 노드 넣어줌
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
		 가장먼노드 s = new 가장먼노드();
		 int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		 int answer = s.solution(6, edge);
		 System.out.println(answer);
		 
	}
}
