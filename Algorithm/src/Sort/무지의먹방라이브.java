package Algorithm.src.Sort;

import java.util.LinkedList;

public class 무지의먹방라이브 {
	long next_food_time;

	class Food {
		int index;
		int food_time;

		public Food(int index, int food_time) {
			super();
			this.index = index;
			this.food_time = food_time;
		}
	}

	public int solution(int[] food_times, long k) {
		LinkedList<Food> list = new LinkedList<Food>();

		// 입력
		long tmp = 0;
		for (int i = 0; i < food_times.length; i++) {
			list.add(new Food(i + 1, food_times[i]));
			tmp += food_times[i];
		}
		// 총 합보다 k가 더 크면 -1 리턴
		if (tmp <= k) {
			return -1;
		}
		// k와 list.size을 비교
		next_food_time = 1;
		while (true) {
			long listSize = list.size();
			if (k >= listSize) {
				k -= listSize;
				list.removeIf(food -> (food.food_time == next_food_time));
				next_food_time++;
			} else {
				break;
			}
		}
		return list.get((int) (k)).index;

	}

	public static void main(String[] args) {
		무지의먹방라이브 s = new 무지의먹방라이브();
		int[] food_times = { 3, 1, 2, 3, 5 };
		int answer = s.solution(food_times, 11);
		System.out.println(answer);
	}

}
