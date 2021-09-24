import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        int[][] jobs = {{0,5,1,1},
                {2,4,3,3},
                {3,4,4,5},
                {5,2,3,2}};

        Solution s = new Solution();
        s.solution(jobs);
    }

    public int[] solution(int[][] jobs) {
        int[] answer = {};
        List<Integer> answerList = new ArrayList<Integer>();

        int n = jobs.length; //작업의 개수

        int curTime = 0; //현재시각
        int curNum = 0; //현재 분류번호
        int curIdx = 0; //다음에 넣을 작업인덱스

        //리스트를 2개 만든다.
        //하나는 현재 분류번호와 다른 작업들을 모아두는 리스트
        //또하나는 현재 분류번호와 같은 작업들을 모아두는 리스트
        List<Integer> dList = new ArrayList<Integer>(); //different
        List<Integer> sList = new ArrayList<Integer>(); //same


        //항상 요청번호 1번이 먼저 처리된다.
        curTime = jobs[0][0]; //현재시각
        curNum = jobs[0][2]; //현재분류번호
        answerList.add(curNum);
        curIdx++;
        curTime += jobs[0][1]; //1번의 작업시간을 더한다.

        while (true) {
            if (curIdx >= n && sList.isEmpty() && dList.isEmpty()) {
                break;
            }

            //현재시각까지 들어온 모든 요청을 담는다.
            while (true) {
                if (curIdx >= n) {
                    break;
                }

                if (jobs[curIdx][0] <= curTime) {
                    int num = jobs[curIdx][2]; //작업번호
                    if (num == curNum) {
                        sList.add(curIdx);
                    } else if (num != curNum) {
                        //현재 분류번호와 다를 경우
                        dList.add(curIdx);
                    }
                    curIdx++;
                } else {
                    break;
                }
            }

            //현재 분류번호와 같은 작업들을 먼저 처리한다.
            if (!sList.isEmpty()) {
                for (int i = 0; i < sList.size(); i++) {
                    int idx = sList.get(i);
                    curTime += jobs[idx][1];
                }

                //sList를 모두 처리했으면 sList 초기화
                sList = new ArrayList<Integer>();
            } else if (!dList.isEmpty()) {

                //같은 분류의 작업이 없다면
                //리스트에 담긴 작업들 중 비교를 해야한다.
                //분류번호 : 중요도의 맵을 만들어본다.
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int i = 0; i < dList.size(); i++) {
                    int idx = dList.get(i);
                    int num = jobs[idx][2];
                    int importance = jobs[idx][3];

                    if (!map.containsKey(num)) {
                        map.put(num, importance);
                    } else {
                        map.put(num, map.get(num)+importance); //분류번호가 같다면 중요도를 합친다.
                    }
                }

                //중요도 중 최대값을 찾아낸다.
                int max = 0;
                for (int num : map.keySet()) {
                    max = Math.max(map.get(num), max);
                }

                //중요도가 최대인 분류번호가 여러개있다면 그중 가장 낮은 분류번호를 찾아낸다.
                List<Integer> tmp = new ArrayList<Integer>();
                for (int num : map.keySet()) {
                    if (map.get(num) == max) {
                        tmp.add(num);
                    }
                }
                Collections.sort(tmp);

                //찾아낸 분류번호를 갱신한다.
                curNum = tmp.get(0);
                answerList.add(curNum);

                //dList에 담긴 작업 중 curNum과 같은 분류번호를 가진 작업을 모두 처리한다.
                tmp = new ArrayList<Integer>();
                for (int i = 0; i < dList.size(); i++) {
                    int idx = dList.get(i);
                    int num = jobs[idx][2];

                    //분류번호가 같다면
                    if (num == curNum) {
                        curTime += jobs[idx][1];
                    } else {
                        //분류번호가 다르다면
                        tmp.add(idx);
                    }
                }

                //dList에서 처리한 작업 제외
                dList = new ArrayList<Integer>();
                for (int num : tmp) {
                    dList.add(num);
                }
            } else if (curIdx < n && sList.isEmpty() && dList.isEmpty()) {
                //대기중인 작업이 없을땐
                //다음 작업을 진행한다.

                int num = jobs[curIdx][2];
                //분류번호가 같다면
                if (num == curNum) {
                    curTime += jobs[curIdx][1];
                } else {
                    //분류번호가 다르다면
                    curNum = num; //분류번호를 바꿔주고
                    answerList.add(curNum);
                    curTime += jobs[curIdx][1];
                }
                curIdx++;
            }
        }

        for (int num : answerList) {
            System.out.print(num + " ");
        }
        System.out.println();

        return answer;
    }
}
