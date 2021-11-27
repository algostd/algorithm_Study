import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import static java.lang.System.in;

// 50%에서 시간 초과
// mid 기점으로 값을 넣을 때 탐색하는 과정이 시간 많이 잡아먹음
public class 중앙값구하기Fail {

    private static LinkedList<Integer> linkedList;
    private static int mid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answerBuilder = new StringBuilder();

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            linkedList = new LinkedList<>();

            // 알고리즘 시작
            if (N == 1) { // 1개일 때 예외처리
                answerBuilder.append(1).append("\n");
                answerBuilder.append(Integer.parseInt(br.readLine())).append("\n");
            } else {
                answerBuilder.append(N / 2 + 1).append("\n");
                mid = 0;
                int count = 1;
                String[] strArr;
                while (N > 0) {
                    strArr = br.readLine().split(" ");
                    for (int i = 0; i < strArr.length; i++) {
                        int next = Integer.parseInt(strArr[i]);
                        // 1일때 예외처리
                        if (count == 1) {
                            linkedList.add(next);
                            answerBuilder.append(next).append(" ");
                            count++;
                            continue;
                        } else if (count == 2){
                            if (linkedList.get(0) < next) {
                                linkedList.add(next);
                            } else{
                                linkedList.addFirst(next);
                            }
                            count++;
                            continue;
                        }

                        whereIGoIn(next);
                        if (count % 2 == 1) { // 홀수 일때
                            mid++;
                            answerBuilder.append(linkedList.get(mid)).append(" ");
                        }
                        count++;
                    }
                    if (count > 20) {
                        answerBuilder.append("\n");
                    }
                    N -= 10;
                }
                answerBuilder.append("\n");
            }
            linkedList.clear();
        }
        System.out.println(answerBuilder);
    }

    private static void whereIGoIn(int next) {
        if (linkedList.get(mid + 1) <= next) { // 오른쪽으로 들어가
            int index = mid + 1;
            while (index < linkedList.size() && linkedList.get(index) < next) {
                index++;
            }
            linkedList.add(index, next);
        } else if (linkedList.get(mid) >= next) { // 왼쪽으로 들어가
            int index = mid - 1;
            while (index >= 0 && linkedList.get(index) > next) {
                index--;
            }
            linkedList.add(index + 1, next);
        } else{ // 가운데
            linkedList.add(mid + 1, next);
        }
    }
}
