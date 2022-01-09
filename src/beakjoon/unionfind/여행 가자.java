import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static int N, M;
	private static int[] parent;
	private static int[] travelCourse;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		//입력
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = i + 1; j < N; j++) {
				int num = Integer.parseInt(input[j]);
				if (num == 1) {
					unionParent(i, j);
				}
			}
		}

		travelCourse = new int[M];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < M; i++) {
			travelCourse[i] = Integer.parseInt(input[i]) - 1;
		}

		//알고리즘
		for (int i = 0; i < M - 1; i++) {
			if (findParent(travelCourse[i], travelCourse[i + 1]) == 0) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	public static int getParent(int idx) {
		if (parent[idx] == idx) {
			return idx;
		}
		return parent[idx] = getParent(parent[idx]);
	}

	public static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	public static int findParent(int a, int b) {
		if (getParent(a) == getParent(b)) {
			return 1;
		} else {
			return 0;
		}
	}

}