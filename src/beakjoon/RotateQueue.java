import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            out.add(num);
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        int answer = 0;

        //Algorithm
        //list.get(0)만 출력할 수 있다.
        for (int num : out) {
            List<Integer> listL = new ArrayList<Integer>();
            List<Integer> listR = new ArrayList<Integer>();
            for (int tmp : list) {
                listL.add(tmp);
                listR.add(tmp);
            }

            int cntL = 0;
            int cntR = 0;

            while (true) {
                int cur = listL.get(0);
                if (cur == num) {
                    break;
                }
                listL.remove(0);
                listL.add(cur);
                cntL++;
            }

            while (true) {
                int cur = listR.get(0);
                if (cur == num) {
                    break;
                }
                int last = listR.get(listR.size() - 1);
                listR.remove(listR.size() - 1);
                listR.add(0, last);
                cntR++;
            }

            if (cntL <= cntR) {
                answer += cntL;
                listL.remove(0);
                list = listL;
            } else {
                answer += cntR;
                listR.remove(0);
                list = listR;
            }
        }

        System.out.println(answer);
    }

}