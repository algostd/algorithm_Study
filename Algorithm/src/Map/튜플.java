package Algorithm.src.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
// https://programmers.co.kr/learn/courses/30/lessons/64065?language=java

public class 튜플 {

    public int[] solution(String s) {

        // 양옆 { } 제거
        s = s.substring(1, s.length() - 1);

        System.out.println(s);

        // {} 벗겨서 숫자 정보 추출
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i++) == '{') { // 시작

                while (s.charAt(i) != '}') {
                    if (s.charAt(i) == ',') {
                        i++;
                        continue;
                    }
                    // 숫자는 변환 후 추가
                    int numCount = 1;
                    ArrayList<Integer> arr = new ArrayList<Integer>();
                    while (true) {
                        if (s.charAt(i) == '}' || s.charAt(i) == ',') {
                            int num = 0;
                            numCount /= 10;
                            for (int j = 0; j < arr.size(); j++) {
                                num += arr.get(j) * numCount;
                                numCount /= 10;
                            }
                            if (hashmap.containsKey(num)) {
                                hashmap.replace(num, hashmap.get(num) + 1);
                            } else {
                                hashmap.put(num, 1);
                            }
                            break;
                        } else {
                            int num = s.charAt(i) - 48;
                            arr.add(num);
                            numCount *= 10;
                            i++;

                        }
                    }


                }
                i--;
            }
        }

        int[] answer = new int[hashmap.size()];
        List<Integer> keySetList = new ArrayList<>(hashmap.keySet());
        Collections.sort(keySetList, (o1, o2) -> (hashmap.get(o2).compareTo(hashmap.get(o1))));
        for (Integer key : keySetList) {
            System.out.println("key : " + key + " / " + "value : " + hashmap.get(key));
        }
        int i = 0;
        for (Integer key : keySetList) {
            answer[i++] = key;
        }
        return answer;
    }

    public static void main(String[] args) {
        튜플 s = new 튜플();
        int[] answer = s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

}
