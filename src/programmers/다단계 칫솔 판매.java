import java.util.*;

class Solution {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        //맵을 만든다.
        //child : parent 형식으로
        //enroll[i] : referral[i]

        Map<String, String> map = new HashMap<String, String>();
        Map<String, Integer> answerMap = new HashMap<String, Integer>();
        int n = enroll.length;
        for (int i = 0; i < n; i++) {
            map.put(enroll[i], referral[i]);
            answerMap.put(enroll[i], 0);
        }

        for (int i = 0; i < seller.length; i++) {
            double val = amount[i];
            val *= 100.0;

            String person = seller[i];
            while (val > 0) {
                //1. 값의 10%를 구한다.
                //2. 10%를 버림한다.
                //3. val에 remain을 뺀 값

                double remain = val * 10.0 / 100.0;
                remain = Math.floor(remain);

                answerMap.put(person, (int) (answerMap.get(person)+val-remain));
                person = map.get(person);
                if (person.equals("-")) {
                    break;
                }

                val = remain;
            }
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] =  answerMap.get(enroll[i]);
        }

        return answer;
    }
}