import java.util.*;

class Solution {
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String s : gems) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
        int cnt = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < gems.length; i++) {
            cnt++;
            String s = gems[i];
            if (tmpMap.containsKey(s)) {
                tmpMap.put(s, tmpMap.get(s) + 1);
            } else {
                tmpMap.put(s, 1);
            }
            if (tmpMap.size() == map.size()) {
                end = i;
                break;
            }
        }
        int x = start;
        int y = end;

        int max = cnt;
        while(end < gems.length) {
            String s1 = gems[start];
            String s2 = gems[end];

            if (tmpMap.get(s1) > 1) {
                cnt--;
                tmpMap.put(s1, tmpMap.get(s1) - 1);
                start++;
                if (tmpMap.get(s1) == 0) {
                    tmpMap.remove(s1);
                }

                if (cnt < max) {
                    max = cnt;
                    x = start;
                    y = end;
                }
            } else {
                if (end == gems.length - 1) {
                    break;
                }

                cnt++;
                end++;
                s2 = gems[end];
                if (tmpMap.containsKey(s2)) {
                    tmpMap.put(s2, tmpMap.get(s2) + 1);
                } else {
                    tmpMap.put(s2, 1);
                }
            }
        }

        //마지막
        for (int i = start; i < gems.length; i++) {
            String s1 = gems[start];

            if (tmpMap.get(s1) > 1) {
                cnt--;
                tmpMap.put(s1, tmpMap.get(s1) - 1);
                start++;
                if (cnt < max) {
                    max = cnt;
                    x = start;
                    y = end;
                }
            } else {
                break;
            }
        }

        answer[0] = x + 1;
        answer[1] = y + 1;

        return answer;
    }
}