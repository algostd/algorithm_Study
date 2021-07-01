import java.util.*;

class Solution {
    public static int solution(int[] A, int[] B) {
        List<Integer> list = new ArrayList<Integer>();
        for (int num : B) {
            list.add(num);
        }
        //오름차순으로 정렬
        Collections.sort(list);

        int cnt = 0;
        for (int num : A) {
            int max = list.get(list.size() - 1);

            if (num >= max) {
                list.remove(0);
                continue;
            }

            boolean check = false;
            for (int i = 0; i < list.size(); i++) {
                int cur = list.get(i);
                if (cur > num) {
                    list.remove(i);
                    cnt++;
                    check = true;
                    break;
                }
            }
            if (check == false) {
                list.remove(0);
            }
        }

        return cnt;
    }
}