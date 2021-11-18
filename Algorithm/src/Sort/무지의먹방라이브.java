package Algorithm.src.Sort;

import java.util.LinkedList;

public class �����ǸԹ���̺� {
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

		// �Է�
		long tmp = 0;
		for (int i = 0; i < food_times.length; i++) {
			list.add(new Food(i + 1, food_times[i]));
			tmp += food_times[i];
		}
		// �� �պ��� k�� �� ũ�� -1 ����
		if (tmp <= k) {
			return -1;
		}
		// k�� list.size�� ��
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
		�����ǸԹ���̺� s = new �����ǸԹ���̺�();
		int[] food_times = { 3, 1, 2, 3, 5 };
		int answer = s.solution(food_times, 11);
		System.out.println(answer);
	}

}
