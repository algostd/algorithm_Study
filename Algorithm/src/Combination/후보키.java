package Algorithm.src.Combination;

import java.util.ArrayList;
import java.util.HashMap;

public class �ĺ�Ű {
	public int solution(String[][] relation) {
		int answer = 0;
		HashMap<Integer, ArrayList<Integer>> exceptLine = new HashMap<Integer, ArrayList<Integer>>();
		// �Ӽ� 1���� �˻�
		// hashmap�� �� String�� key�� �ǰ� value���� �ߺ��� row index�� ���
		HashMap<String, ArrayList<Integer>> hashmap = new HashMap<String, ArrayList<Integer>>();
		
		// oneCol���� �Ӽ� �� �������� �ĺ�Ű�� �� �� �ִ� colNum�� �����
		ArrayList<Integer> oneCol = new ArrayList<Integer>();
		for (int col = 0; col < relation[0].length; col++) {
			String previous = relation[0][col];
			int check = col;
			for (int row = 1; row < relation.length; row++) {
				String now = relation[row][col];
				if(!hashmap.containsKey(now)) { // �� String�� key�� �ǰ� value���� �ߺ��� row index�� ���
					hashmap.put(now, new ArrayList<Integer>());
					hashmap.get(now).add(row);
				} else {
					hashmap.get(now).add(row);
				}
				if (previous.equals(now)) {
					check = -1;
					break;
				}
			}
			if(check != -1) {
				oneCol.add(check);
			}
		}
		
		// �Ӽ� 2�� �����ؼ� �˻� 
		ArrayList<Integer> tempArr = new ArrayList<Integer>();
		for (int i = 1; i <= relation[0].length; i++) {
			tempArr.add(i);
		}
		tempArr.removeAll(oneCol);
		int[] arr = new int[tempArr.size()];
		int i = 0;
		for(Integer value : tempArr) {
			arr[i++] = value;
		}
		
		comb1(arr, new boolean[arr.length], 0, 2);
		
		return answer;
	}
	
	static void comb1(int[] arr, boolean[] visited, int start, int r) {
        if(r == 0) {
            // print(arr, visited);
            return;
        } else {
            for(int i = start; i < arr.length; i++) {
                visited[i] = true;
                comb1(arr, visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }
	

	public static void main(String[] args) {
		�ĺ�Ű s = new �ĺ�Ű();
		String[][] computers = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		int answer = s.solution(computers);
		System.out.println(answer);
	}
}
