package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Food implements Comparable<Food> {
	int index;
	int food_time;

	public Food(int index, int food_time) {
		super();
		this.index = index;
		this.food_time = food_time;
	}

	// food_time에 따른 오름차순 정렬
	@Override
	public int compareTo(Food o) {
		return Integer.compare(this.food_time, o.food_time);
	}
}

public class 무지의먹방라이브Success {

	public int solution(int[] food_times, long k) {
		PriorityQueue<Food> queue = new PriorityQueue<>();

		// 입력
		int tmp = 0;
		for (int i = 0; i < food_times.length; i++) {
			queue.offer(new Food(i + 1, food_times[i]));
			tmp += food_times[i];
		}
		// 총 합보다 k가 더 크면 -1 리턴
		if (tmp <= k) {
			return -1;
		}

		// 알고리즘 시작
		// 정렬된 배열의 앞 순서의 food_time 만큼 뺄건데, 단, k가 더 클 때만 반복

		long total = 0; // 뺀 총합
		long previous = 0;
		long length = food_times.length;
		while (k >= total + (queue.peek().food_time - previous) * length) {
			int now = queue.poll().food_time;
			total += (now - previous) * length;
			previous = now;
			length--;
		}

		ArrayList<Food> arr = new ArrayList<Food>();
		while (!queue.isEmpty()) {
			arr.add(queue.poll());
		}

		Collections.sort(arr, new Comparator<Food>() {
			@Override
			public int compare(Food food1, Food food2) {
				return Integer.compare(food1.index, food2.index);
			}
		});

		return arr.get((int) ((k - total) % length)).index;

	}

	public static void main(String[] args) {
		무지의먹방라이브Success s = new 무지의먹방라이브Success();
		int[] food_times = { 3, 1, 2, 3, 5 };
		int answer = s.solution(food_times, 11);
		System.out.println(answer);
	}
}
