package programmers;

import java.util.Arrays;
import java.util.Comparator;

// https://programmers.co.kr/learn/courses/30/lessons/43164?language=java
// dfs, BackTracking

public class 여행경로 {
	static String[] answer;
	static int index = 0;

	public String[] solution(String[][] tickets) {

		Boolean[] visited = new Boolean[tickets.length];
		Arrays.fill(visited, false);
		answer = new String[tickets.length + 1];
		answer[index++] = "ICN";

		// 정렬
		Arrays.sort(tickets, Comparator.comparing(item -> item[1]));
		findRoot(tickets, visited, "ICN");
		return answer;
	}

	public boolean findRoot(String[][] tickets, Boolean[] visited, String now) {
		for (int i = 0; i < tickets.length; i++) {
			if (visited[i]) {
				continue;
			}
			if (tickets[i][0].equals(now)) {
				visited[i] = true;
				answer[index++] = tickets[i][1];
				if (findRoot(tickets, visited, tickets[i][1])) {
					break;
				} else {
					visited[i] = false;
					answer[--index] = "";
					continue;
				}

			}
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		여행경로 s = new 여행경로();
		String[][] tickets = { { "ICN", "BOO" }, { "ICN", "COO" }, { "COO", "DOO" }, { "DOO", "COO" }, { "BOO", "DOO" },
				{ "DOO", "BOO" }, { "BOO", "ICN" }, { "COO", "BOO" } };
		String[] answer = s.solution(tickets);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}

	}
}
