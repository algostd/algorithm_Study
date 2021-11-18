package Algorithm.src.Sort;

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

	// food_time�� ���� �������� ����
	@Override
	public int compareTo(Food o) {
		return Integer.compare(this.food_time, o.food_time);
	}
}

public class �����ǸԹ���̺�Success {

	public int solution(int[] food_times, long k) {
		PriorityQueue<Food> queue = new PriorityQueue<>();

		// �Է�
		int tmp = 0;
		for (int i = 0; i < food_times.length; i++) {
			queue.offer(new Food(i + 1, food_times[i]));
			tmp += food_times[i];
		}
		// �� �պ��� k�� �� ũ�� -1 ����
		if (tmp <= k) {
			return -1;
		}

		// �˰��� ����
		// ���ĵ� �迭�� �� ������ food_time ��ŭ ���ǵ�, ��, k�� �� Ŭ ���� �ݺ�

		long total = 0; // �� ����
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
		�����ǸԹ���̺�Success s = new �����ǸԹ���̺�Success();
		int[] food_times = { 3, 1, 2, 3, 5 };
		int answer = s.solution(food_times, 11);
		System.out.println(answer);
	}
}
