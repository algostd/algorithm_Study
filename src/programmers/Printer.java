import java.util.*;

class Solution {
    public static int solution(int[] priorities, int location) {
        List<Integer> list = new ArrayList<Integer>();
        Queue<Integer> rank = new LinkedList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        int[] arr = new int[10];
        for (int i = 0; i < priorities.length; i++) {
            int num = priorities[i];
            q.add(num);
            rank.add(i);
            arr[num]++;
        }

        while(true) {
            if (q.size() == 0) {
                break;
            }

            int num = q.peek(); // 검사하려는 수

            //큐안에 num보다 더 큰 숫자가 있는지 체크
            boolean check = false;
            for (int i = num + 1; i <= 9; i++) {
                if (arr[i] > 0) {
                    check = true;
                    break;
                }
            }
            //num보다 큰수가 없을때
            if (check == false) {
                q.poll();
                int rankN = rank.poll();
                list.add(rankN);
                arr[num]--;
            } else if (check == true) {
                //num보다 큰수가 존재할때
                //맨뒤로 보낸다.
                q.poll();
                int rankN = rank.poll();
                rank.add(rankN);
                q.add(num);
            }
        }

        int answer = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == location) {
                //가장 먼저 출력되면 1
                answer = i + 1;
                break;
            }
        }

        return answer;
    }
}