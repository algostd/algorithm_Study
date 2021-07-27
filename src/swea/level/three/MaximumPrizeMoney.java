import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {
    private static int number, total, N, curIdx;
    private static int max;
    private static int[] arr;
    private static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            // 입력
            int answer = 0;
            String[] s = br.readLine().split(" ");

            number = Integer.parseInt(s[0]);
            total = Integer.parseInt(s[1]);
            N = s[0].length(); // 자릿수

            // 초기화
            max = Integer.MIN_VALUE;
            curIdx = 0;
            check = false;
            arr = new int[N];

            char[] tmp_arr = s[0].toCharArray();
            for (int i = 0; i < N; i++) {
                int num = tmp_arr[i] - '0';
                arr[i] = num;
            }

            Solution sol = new Solution();
            sol.change(total);

            int tenCnt = 1;
            for (int i = N - 1; i >= 0; i--) {
                int num = arr[i];
                answer += num * tenCnt;
                tenCnt *= 10;
            }

            bw.write("#");
            bw.write(String.valueOf(test_case));
            bw.write(" ");
            bw.write(String.valueOf(answer));
            bw.write("\r\n");
            bw.flush();

            // System.out.println("#" + test_case + " " + answer);
        }

        br.close();
    }

    // 1. i=0부터 i=N-2까지 순회한다.
    //    int cur = arr[i];
    //    max값은 i+1부터 N-1이전까지 최대로 큰 값을 의미한다.
    //    1-1) max값이 단 하나일 경우
    //        1-1-1) cur < max이면 cur과 max값의 위치를 바꾼다.
    //        1-1-2) cur == max이면 i++한다. (pass)
    //        1-1-3) cur > max이면 i++한다. (pass)
    //
    //    1-2) max값이 여러개일 경우
    //        1-2-1) cnt == 1일 경우 max값은 여러 값 중 가장 오른쪽에 있는 값을 택한다.
    //            a) 이때 cur < max 이면 cur과 max의 위치를 바꾼다.
    //            b) 이때 cur == max 이면 i++한다. (pass)
    //            c) 이때 cur > max 이면 i++한다. (pass)
    //        1-2-2) cnt >= 2일 경우
    //            a) cur == min이면 cur과 max값 중 가장 오른쪽에 위치한 값을 바꾼다.
    //           ★b) cur > min이면 i++부터 N-1까지 cur보다 작은 수가 몇개 인지 센다.
    //               그 다음 maxCnt, minCnt, cnt 셋 중 가장 작은 값만큼 max의 값을 왼쪽으로 떙긴다.

    // 2. 만약 순회를 다 마쳤다면 check=true로 바꿔준 뒤 따로 처리한다.
    //    2-1) cnt == 1일 경우 맨 마지막에 있는 두 숫자를 바꿔준다.
    //    2-2) cnt == 2일 경우 cnt를 2로 나눠서 cnt가 짝수일 경우 그대로 return / cnt가 홀수일 경우 맨 마지막에 있는 두 숫자를 바꿔준 뒤 return 한다.


    private void change(int cnt) {

        if (cnt == 0) {
            return;
        }

        if (curIdx >= N - 1) {
            curIdx = 0;
            check = true;
        }

        int cur = arr[curIdx];

        // max값, min값 구하기
        int[] tmp_arr = new int[10];
        List<Integer> list = new ArrayList<Integer>();
        int max = -1;
        int min = 10;
        for (int i = curIdx + 1; i < N; i++) {
            int num = arr[i];
            tmp_arr[num]++;
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        for (int i = curIdx + 1; i < N; i++) {
            if (arr[i] == max) {
                list.add(i);
            }
        }
        int maxCnt = tmp_arr[max];
        int minCnt = tmp_arr[min];

        if (check) {

            if (cnt == 1) {
                int tmp = arr[N - 1];
                arr[N - 1] = arr[N - 2];
                arr[N - 2] = tmp;
                return;
            } else if (cnt >= 2) {
                if (cnt % 2 == 0) {
                    return;
                } else if (cnt % 2 == 1) {
                    int tmp = arr[N - 1];
                    arr[N - 1] = arr[N - 2];
                    arr[N - 2] = tmp;
                    return;
                }
            }
        }

        if (maxCnt == 1) {
            if (cur < max) {
                int maxIdx = list.get(0);
                arr[maxIdx] = cur;
                arr[curIdx] = max;

                curIdx++;
                change(cnt - 1);
            } else if (cur == max) {
                curIdx++;
                change(cnt);
            } else if (cur > max) {
                curIdx++;
                change(cnt);
            }
        } else if (maxCnt >= 2) {
            if (cnt == 1) {
                if (cur < max) {
                    int size = list.size();
                    int maxIdx = list.get(size - 1);
                    arr[maxIdx] = cur;
                    arr[curIdx] = max;
                    curIdx++;
                    change(cnt - 1);
                } else if (cur == max) {
                    curIdx++;
                    change(cnt);
                } else if (cur > max) {
                    curIdx++;
                    change(cnt);
                }
            } else if (cnt >= 2) {
                if (cur == min) {
                    int size = list.size();
                    int maxIdx = list.get(size - 1);
                    arr[maxIdx] = cur;
                    arr[curIdx] = max;
                    curIdx++;
                    change(cnt - 1);
                } else if (cur > min) {
                    int minimal_num = Math.min(maxCnt, Math.min(cnt, minCnt));
                    int size = list.size();
                    int maxIdx = list.get(size - 1 - minimal_num);
                    arr[maxIdx] = cur;
                    arr[curIdx] = max;
                    curIdx++;
                    change(cnt - 1);
                } else if (cur > max) {
                    curIdx++;
                    change(cnt);
                }
            }
        }
    }
}